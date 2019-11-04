package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.util.Random;
import java.util.Set;

import static ar.edu.itba.ss.SystemConfiguration.MIN_R;

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
            if (each.getPosition().distanceTo(newPosition) < MIN_R * 2) {
                return true;
            }
        }

        if (newPosition.y + newR >= SystemConfiguration.W) {
            return true;
        }

        return (newPosition.y - newR <= 0);
    }
}
