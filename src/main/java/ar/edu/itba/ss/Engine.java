package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Engine {

    private double L = SystemConfiguration.L;
    private double W = SystemConfiguration.W;
    private double totalTime = SystemConfiguration.TOTAL_TIME;
    private double time = 0;
    private Writer xyzWriter = new Writer(SystemConfiguration.XYZ_WRITER_PATH);

    private Set<Particle> particles;

    public Engine() {

        particles = initializeParticles(SystemConfiguration.PARTICLES_QUANTITY, SystemConfiguration.MIN_R,
                SystemConfiguration.MAX_R);

        System.out.println("Particles initialized");

        printToXYZ();

        double auxTime = 0;

        while (time < totalTime) {

            findContactsAndCalculateVe();
            adjustAllRadius();
            computeVd();
            calculateNewPositions();
            clearParticles();

            time += SystemConfiguration.DELTA_T;
            auxTime += SystemConfiguration.DELTA_T;

            if (auxTime >= SystemConfiguration.DELTA_T2) {
                printToXYZ();
                auxTime -= SystemConfiguration.DELTA_T2;
            }
        }
    }

    private Set<Particle> initializeParticles(final int quantity, final double minR, final double maxR) {

        Set<Particle> newParticles = new HashSet<>();
        Random random = Helper.getRandom();

        for (int i = 0; i < quantity; i++) {
            double r = random.nextDouble() * (maxR - minR) + minR;
            double x;
            double y;
            Vector position;

            do {
                x = random.nextDouble() * L;
                y = random.nextDouble() * W;
                position = new Vector(x, y);
            } while (Helper.verifyOverlap(newParticles, position, r));

            newParticles.add(new Particle(i, position, Helper.getVelocityNoOverlap(r, minR, maxR), r));
        }

        return newParticles;
    }

    private void printToXYZ() {
        xyzWriter.writeString(particles.size() + 4 + "\n\n");

        for (Particle each : particles) {
            xyzWriter.writeString(each.xyzPrint() + "\n");
        }

        Particle particle1 = new Particle(-1, new Vector(0, 0), new Vector(0, 0), 0.00001);
        Particle particle2 = new Particle(-1, new Vector(SystemConfiguration.L, 0), new Vector(0, 0), 0.00001);
        Particle particle3 = new Particle(-1, new Vector(0, SystemConfiguration.W), new Vector(0, 0), 0.00001);
        Particle particle4 = new Particle(-1, new Vector(SystemConfiguration.L, SystemConfiguration.W), new Vector(0, 0), 0.00001);

        xyzWriter.writeString(particle1.xyzPrint() + "\n");
        xyzWriter.writeString(particle2.xyzPrint() + "\n");
        xyzWriter.writeString(particle3.xyzPrint() + "\n");
        xyzWriter.writeString(particle4.xyzPrint() + "\n");
    }

    private void findContactsAndCalculateVe() {
        for (Particle p1 : particles) {

            Vector escapeVector = new Vector(0, 0);
            boolean overlapping = false;

            // Ecuation number 6
            for (Particle p2 : particles) {
                if (!p1.equals(p2) && Helper.isOverlapping(p1, p2)) {
                    escapeVector = escapeVector.plusVector(p2.getPosition().calculatePerpendicularVersor(p1.getPosition()));
                    overlapping = true;
                }
            }

            // Upper wall
            if (Helper.upperWallOverlapping(p1)) {
                escapeVector = escapeVector.plusVector(new Vector(p1.getPosition().x, W).calculatePerpendicularVersor(p1.getPosition()));
                overlapping = true;
            }

            // Bottom wall
            if (Helper.bottomWallOverlapping(p1)) {
                escapeVector = escapeVector.plusVector(new Vector(p1.getPosition().x, 0).calculatePerpendicularVersor(p1.getPosition()));
                overlapping = true;
            }

            escapeVector = escapeVector.normalize().timesScalar(SystemConfiguration.MAX_V);

            if (overlapping) {
                p1.setVelocity(escapeVector);
                p1.setOverlapping(true);
            }
        }
    }

    private void adjustAllRadius() {
        for (Particle particle : particles) {
            if (particle.isOverlapping()) {
                particle.setR(SystemConfiguration.MIN_R);
            } else if (particle.getR() < SystemConfiguration.MAX_R) {
                double newR = particle.getR() + (SystemConfiguration.MAX_R / (SystemConfiguration.TAU / SystemConfiguration.DELTA_T));
                particle.setR(Math.min(newR, SystemConfiguration.MAX_R));
            }
        }
    }

    private void computeVd() {
        for (Particle particle : particles) {
            if (!particle.isOverlapping()) {
                particle.setVelocity(Helper.getVelocityNoOverlap(particle));
            }
        }
    }

    private void calculateNewPositions() {
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

    private void clearParticles() {
        for (Particle particle : particles) {
            particle.setOverlapping(false);
        }
    }
}
