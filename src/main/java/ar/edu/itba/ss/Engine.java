package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.*;

import static ar.edu.itba.ss.SystemConfiguration.*;

public class Engine {
    public static void runSimulation() {
        double time = 0;
        Set<Particle> particles = initializeParticles();

        System.out.println("Particles initialized");

        Writer.writeParticles(particles);

        double auxTime = 0;

        while (time < TOTAL_TIME) {

            findContactsAndCalculateVe(particles);
            adjustAllRadius(particles);
            computeVd(particles);
            calculateNewPositions(particles);
            clearParticles(particles);

            time += SystemConfiguration.DELTA_T;
            auxTime += SystemConfiguration.DELTA_T;

            if (auxTime >= SystemConfiguration.DELTA_T2) {
                Writer.writeParticles(particles);
                auxTime -= SystemConfiguration.DELTA_T2;
            }
        }
    }

    private static Set<Particle> initializeParticles() {
        Set<Particle> newParticles = new HashSet<>();
        Random random = Helper.getRandom();

        for (int i = 0; i < PARTICLES_QUANTITY; i++) {
            double r = random.nextDouble() * (MAX_R - MIN_R) + MIN_R;
            double x;
            double y;
            Vector position;

            do {
                x = random.nextDouble() * L;
                y = random.nextDouble() * W;
                position = new Vector(x, y);
            } while (Helper.verifyOverlap(newParticles, position, r));

            newParticles.add(new Particle(i, position, Particle.getVelocityNoOverlap(r), r));
        }

        return newParticles;
    }

    private static void findContactsAndCalculateVe(Set<Particle> particles) {
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
                escapeVector = escapeVector.plusVector(new Vector(p1.getPosition().x, W).calculatePerpendicularUnitVector(p1.getPosition()));
                overlapping = true;
            }

            // Bottom wall
            if (p1.bottomWallOverlapping()) {
                escapeVector = escapeVector.plusVector(new Vector(p1.getPosition().x, 0).calculatePerpendicularUnitVector(p1.getPosition()));
                overlapping = true;
            }

            escapeVector = escapeVector.normalize().timesScalar(SystemConfiguration.MAX_V);

            if (overlapping) {
                p1.setVelocity(escapeVector);
                p1.setOverlapping(true);
            }
        }
    }

    private static void adjustAllRadius(Set<Particle> particles) {
        for (Particle particle : particles) {
            if (particle.isOverlapping()) {
                particle.setR(SystemConfiguration.MIN_R);
            } else if (particle.getR() < SystemConfiguration.MAX_R) {
                double newR = particle.getR() + (SystemConfiguration.MAX_R / (SystemConfiguration.TAU / SystemConfiguration.DELTA_T));
                particle.setR(Math.min(newR, SystemConfiguration.MAX_R));
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

    private static void calculateNewPositions(Set<Particle> particles) {
        for (Particle particle : particles) {
            Vector newPosition = particle.getPosition().plusVector(particle.getVelocity().timesScalar(SystemConfiguration.DELTA_T));

            if (newPosition.x > L) {
                newPosition = new Vector(newPosition.x - L, newPosition.y);
            }

            if (newPosition.x < 0) {
                newPosition = new Vector(newPosition.x + L, newPosition.y);
            }

            particle.setPosition(newPosition);
        }
    }

    private static void clearParticles(Set<Particle> particles) {
        for (Particle particle : particles) {
            particle.setOverlapping(false);
        }
    }
}
