package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.Random;
import java.util.Set;

public class Helper {
    private static Random random;
    private static final long seed = System.currentTimeMillis();

    static Random getRandom() {
        if (random == null) {
            random = new Random(seed);
        }

        return random;
    }

    static boolean verifyOverlap(final Set<Particle> newParticles, final Vector newPosition, final double newR) {
        for (Particle each : newParticles) {
            if (each.getPosition().distanceTo(newPosition) < each.getR() + newR) {
                return true;
            }
        }

        if (newPosition.y + newR >= SystemConfiguration.W) {
            return true;
        }

        return (newPosition.y - newR <= 0);
    }
}
