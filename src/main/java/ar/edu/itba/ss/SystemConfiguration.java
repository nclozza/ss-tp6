package ar.edu.itba.ss;

public class SystemConfiguration {

    // Set of parameters 1
    public static final double MIN_R = 0.15; // m
    public static final double MAX_R = 0.32; // m
    public static final double BETA = 0.9;
    public static final double MAX_V = 1.55; // m/s

    // Set of parameters 2
//    public static final double MIN_R = 0.10; // m
//    public static final double MAX_R = 0.37; // m
//    public static final double BETA = 0.9;
//    public static final double MAX_V = 0.95; // m/s

    // [R] = m
    private static final double R = 4;

    // [L] = m
    public static final double L = R * 2 * Math.PI;

    // [W] = m
    public static final double W = 5 * (MAX_R + MIN_R);

    public static final int PARTICLES_QUANTITY = 400;

    // [DELTA_T] = s
//    public static final double DELTA_T = MIN_R / 2 * MAX_V;
    public static final double DELTA_T = 0.02;

    // [DELTA_T2] = s
    public static final double DELTA_T2 = DELTA_T; // s

    // [TAU] = s
    public static final double TAU = 0.5; // s

    // [TOTAL_TIME] = s
    public static final double TOTAL_TIME = 20; // s

    // OUTPUT
    public static final String XYZ_WRITER_PATH = "output.xyz";

    // DEBUG
    public static final boolean debugPrintXYZ = true;
    public static final boolean debugManyWallParticles = true;
    public static final boolean debugLogEverything = true;
}
