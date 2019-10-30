package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Engine {

    private double L = SystemConfiguration.L;
    private double W = SystemConfiguration.W;

    private Set<Particle> particles;

    public Engine() {

        particles = initializeParticles(SystemConfiguration.PARTICLES_QUANTITY, SystemConfiguration.MIN_R_CREATION,
                SystemConfiguration.MAX_R_CREATION, SystemConfiguration.R_MIN_MODIFICATION_PERCENTAGE,
                SystemConfiguration.R_MAX_MODIFICATION_PERCENTAGE);

        System.out.println();

        for (Particle each : particles) {
            System.out.println(each.getR());
            System.out.println(each.getVelocity());
        }

    }

    private Set<Particle> initializeParticles(final int quantity, final double minR, final double maxR,
                                              final double rMinModificationPercentage, final double rMaxModificationPercentage) {

        Set<Particle> newParticles = new HashSet<>();
        Random random = Helper.getRandom();

        for (int i = 0; i < quantity; i++) {
            double r = random.nextDouble() * (maxR - minR) + minR;
            double minRParticle = r * (1 - random.nextDouble() * (rMaxModificationPercentage - rMinModificationPercentage) + rMinModificationPercentage);
            double maxRParticle = r * (1 + random.nextDouble() * (rMaxModificationPercentage - rMinModificationPercentage) + rMinModificationPercentage);
            double x;
            double y;
            Vector position;

            do {
                x = random.nextDouble() * L;
                y = random.nextDouble() * W;
                position = new Vector(x, y);
            } while (Helper.verifyOverlap(newParticles, position, r));

            newParticles.add(new Particle(i, position, Helper.getVelocityNoOverlap(r, minRParticle, maxRParticle), minRParticle, maxRParticle, r));
        }

        return newParticles;
    }
}
