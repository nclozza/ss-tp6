package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Pair;
import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.*;
import java.util.stream.Collectors;


public class Engine {
    public static void runWithSystemParameters() {
        runSimulation(SystemConfiguration.PARTICLES_QUANTITY, SystemConfiguration.MIN_R, SystemConfiguration.MAX_R, SystemConfiguration.L, SystemConfiguration.W, SystemConfiguration.TOTAL_TIME);
    }

    public static void runSimulation(int particleCount, double minRadius, double maxRadius, double length, double width, double totalTime) {
        double time = 0;

        Set<Particle> particles = initializeParticles(particleCount, minRadius, maxRadius, length, width);

        Logger.info("Particles initialized: " + particles.size() + " particles.");

        Writer.writeParticles(particles);

        double auxTime = 0;

        while (time < totalTime) {

            findContactsAndCalculateVe(particles, width);
            adjustAllRadius(particles, minRadius, maxRadius);
            computeVd(particles);
            calculateNewPositions(particles, length);
            clearParticles(particles);

            time += SystemConfiguration.DELTA_T;
            auxTime += SystemConfiguration.DELTA_T;

            if (auxTime >= SystemConfiguration.DELTA_T2) {
                Writer.writeParticles(particles);
                auxTime -= SystemConfiguration.DELTA_T2;
                Logger.debug("Printing at time: " + time + "s");
            }
        }

        Writer.printFundamentalData(fundamentalData(particles, length, width));

        Logger.info("Simulation run successfully: " + time + "s\n");
    }

    private static Set<Particle> initializeParticles(int particleCount, double minRadius, double maxRadius, double length, double width) {
        Set<Particle> newParticles = new HashSet<>();
        Random random = Helper.getRandom();


        for (int i = 0; i < particleCount; i++) {
            final double r = random.nextDouble() * (maxRadius - minRadius) + minRadius;
            final double maxX = length - r;
            final double minX = r;
            final double maxY = width - r;
            final double minY = r;

            double x;
            double y;
            Vector position;

            do {
                x = random.nextDouble() * (maxX - minX) + minX;
                y = random.nextDouble() * (maxY - minY) + minY;
                position = new Vector(x, y);
            } while (false); // while (Helper.verifyOverlap(newParticles, position, r));

            newParticles.add(new Particle(i, position, Particle.getVelocityNoOverlap(r), r));
        }

        return newParticles;
    }

    private static void findContactsAndCalculateVe(Set<Particle> particles, double width) {
        for (Particle p1 : particles) {

            Vector escapeVector = new Vector(0, 0);
            boolean overlapping = false;

            // Equation number 6
            for (Particle p2 : particles) {
                if (!p1.equals(p2) && p1.isOverlapping(p2)) {
                    escapeVector = escapeVector.plusVector(p2.getPosition().calculatePerpendicularUnitVector(p1.getPosition()));
                    overlapping = true;
                }
            }

            // Upper wall
            if (p1.upperWallOverlapping()) {
                Vector upperWallVirtualPosition = new Vector(p1.getPosition().x, width);
                Vector wallEscapeVector1 = upperWallVirtualPosition.calculatePerpendicularUnitVector(p1.getPosition());

                Vector wallEscapeVector = new Vector(wallEscapeVector1.x, -Math.abs(wallEscapeVector1.y));

                escapeVector = escapeVector.plusVector(wallEscapeVector);
                overlapping = true;
            }

            // Bottom wall
            if (p1.bottomWallOverlapping()) {
                Vector bottomWallVirtualPosition = new Vector(p1.getPosition().x, 0);
                Vector wallEscapeVector1 = bottomWallVirtualPosition.calculatePerpendicularUnitVector(p1.getPosition());

                Vector wallEscapeVector = new Vector(wallEscapeVector1.x, Math.abs(wallEscapeVector1.y));

                escapeVector = escapeVector.plusVector(wallEscapeVector);
                overlapping = true;
            }

            escapeVector = escapeVector.normalize().timesScalar(SystemConfiguration.MAX_V);

            if (overlapping) {
                p1.setVelocity(escapeVector);
                p1.setOverlapping(true);
            }
        }
    }

    private static void adjustAllRadius(Set<Particle> particles, double minRadius, double maxRadius) {
        for (Particle particle : particles) {
            if (particle.isOverlapping()) {
                particle.setR(minRadius);
            } else if (particle.getR() < maxRadius) {
                double newR = particle.getR() + (minRadius / (SystemConfiguration.TAU / SystemConfiguration.DELTA_T));
                particle.setR(Math.min(newR, maxRadius));
            }
        }
    }

    private static void computeVd(Set<Particle> particles) {
        for (Particle particle : particles) {
            if (!particle.isOverlapping()) {
                particle.setVelocity(Particle.getVelocityNoOverlap(particle.getR()));
            }
        }
    }

    private static void calculateNewPositions(Set<Particle> particles, double length) {
        for (Particle particle : particles) {
            // x(t + dt) = x(t) + v(t) * dt
            Vector newPosition = particle.getPosition().plusVector(particle.getVelocity().timesScalar(SystemConfiguration.DELTA_T));

            if (newPosition.x > length) {
                newPosition = new Vector(newPosition.x - length, newPosition.y);
            }

            if (newPosition.x < 0) {
                newPosition = new Vector(newPosition.x + length, newPosition.y);
            }

            particle.setPosition(newPosition);
        }
    }

    private static void clearParticles(Set<Particle> particles) {
        for (Particle particle : particles) {
            particle.setOverlapping(false);
        }
    }

    private static Pair<Double, Double> fundamentalData(Set<Particle> particles, double l, double w) {
        // [area] = m^2
        double area = l * w;

        // [density] = people / m^2;
        double density = particles.size() / area;

        // [averageVelocity] = m/s;
        double averageVelocity = particles.stream().collect(Collectors.averagingDouble(particle -> particle.getVelocity().norm()));

        return new Pair<>(density, averageVelocity);
    }
}
