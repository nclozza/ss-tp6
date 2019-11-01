package ar.edu.itba.ss.model;

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

    public void setId(final int id) {
        this.id = id;
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
