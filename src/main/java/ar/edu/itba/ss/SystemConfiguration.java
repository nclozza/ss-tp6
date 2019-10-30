package ar.edu.itba.ss;

public class SystemConfiguration {

    public static final double MIN_R_CREATION = 0.15; // m
    public static final double MAX_R_CREATION = 0.32; // m

    // R_MIN_MODIFICATION_PERCENTAGE < R_MAX_MODIFICATION_PERCENTAGE
    public static final double R_MIN_MODIFICATION_PERCENTAGE = 0.3;
    public static final double R_MAX_MODIFICATION_PERCENTAGE = 0.5;
    public static final double BETA = 1;

    private static final double R = 4; // m
    public static final double L = R * 2 * Math.PI; // m
    public static final double W = 5 * (MAX_R_CREATION - MIN_R_CREATION); // m

    public static final double MAX_V = 1.55; // m/s
    public static final int PARTICLES_QUANTITY = 10;

}
