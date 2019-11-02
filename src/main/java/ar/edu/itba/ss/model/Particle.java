package ar.edu.itba.ss.model;

import ar.edu.itba.ss.SystemConfiguration;

import java.util.Objects;

public class Particle {

    private int id;
    private Vector position;
    private Vector velocity;
    private double r;
    private boolean overlapping;

    public Particle(final int id, final Vector position, final Vector velocity, final double r) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.r = r;
        this.overlapping = false;
    }

    public int getId() {
        return id;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(final Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(final Vector velocity) {
        this.velocity = velocity;
    }

    public double getR() {
        return r;
    }

    public void setR(final double r) {
        this.r = r;
    }

    public boolean isOverlapping() {
        return overlapping;
    }

    public void setOverlapping(final boolean overlapping) {
        this.overlapping = overlapping;
    }

    public boolean isOverlapping(final Particle particle) {
        return this.getPosition().distanceTo(particle.getPosition()) < this.getR() + particle.getR();
    }

    public boolean upperWallOverlapping() {
        return this.getPosition().y + this.getR() >= SystemConfiguration.W;
    }

    public boolean bottomWallOverlapping() {
        return this.getPosition().y - this.getR() <= 0;
    }

    /**
     * Returns the velocity for a particle that's not overlapping another.
     * In this case, the `y` coordinate is always `0` as the particle just moves towards the exit point
     * which is located at the right of the corridor.
     *
     * @param radius
     *
     * @return Velocity assuming no overlap with other particles
     */
    public static Vector getVelocityNoOverlap(double radius) {
        double minR = SystemConfiguration.MIN_R;
        double maxR = SystemConfiguration.MAX_R;
        double x = SystemConfiguration.MAX_V * Math.pow((radius - minR) / (maxR - minR), SystemConfiguration.BETA);

        return new Vector(x, 0);
    }

    public String xyzPrint() {
        return id + "\t" + r + "\t" + position.x + "\t" + position.y + "\t" + velocity.x + "\t" + velocity.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Particle)) {
            return false;
        }

        Particle particle = (Particle) o;

        return getId() == particle.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
