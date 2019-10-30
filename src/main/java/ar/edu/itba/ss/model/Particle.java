package ar.edu.itba.ss.model;

public class Particle {

    private int id;
    private Vector position;
    private Vector velocity;
    private double minR;
    private double maxR;
    private double r;

    public Particle(final int id, final Vector position, final Vector velocity, final double minR, final double maxR,
                    final double r) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.minR = minR;
        this.maxR = maxR;
        this.r = r;
    }

    public int getId() {
        return id;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public double getMinR() {
        return minR;
    }

    public double getMaxR() {
        return maxR;
    }

    public double getR() {
        return r;
    }
}
