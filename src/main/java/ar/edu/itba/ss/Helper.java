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

    public static boolean isOverlapping(final Particle p1, final Particle p2) {
        return p1.getPosition().distanceTo(p2.getPosition()) < p1.getR() + p2.getR();
    }

    // Si no se tocan, entonces avanza siempre en dirección de X, por eso la velocidad en Y es 0
    public static Vector getVelocityNoOverlap(final Particle particle) {
        return getVelocityNoOverlap(particle.getR(), SystemConfiguration.MIN_R, SystemConfiguration.MAX_R);
    }

    static Vector getVelocityNoOverlap(final double r, final double minR, final double maxR) {
        double x = SystemConfiguration.MAX_V * Math.pow((r - minR) / (maxR - minR), SystemConfiguration.BETA);
        return new Vector(x, 0);
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

        if (newPosition.y - newR <= 0) {
            return true;
        }

        return false;
    }

    static boolean upperWallOverlapping(final Particle particle) {
        return particle.getPosition().y + particle.getR() >= SystemConfiguration.W;
    }

    static boolean bottomWallOverlapping(final Particle particle) {
        return particle.getPosition().y - particle.getR() <= 0;
    }
}
