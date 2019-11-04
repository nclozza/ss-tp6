package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Pair;
import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ar.edu.itba.ss.SystemConfiguration.L;
import static ar.edu.itba.ss.SystemConfiguration.W;

public class Writer {
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(SystemConfiguration.XYZ_WRITER_PATH, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static PrintWriter fundamentalDataWriter;
    private static PrintWriter averageVelocityWriter;

    static {
        try {
            fundamentalDataWriter = new PrintWriter("fundamentalData.csv", "UTF-8");
            averageVelocityWriter = new PrintWriter("averageVelocity.csv", "UTF-8");
//            fundamentalDataWriter.write("Density,Average Velocity\n");
//            fundamentalDataWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static NumberFormat formatter = new DecimalFormat("#0.000");

    private static List<Particle> wallParticles;


    public static void writeParticles(Set<Particle> particles) {
        if (!SystemConfiguration.debugPrintXYZ) {
            return;
        }

        List<Particle> wallParticles = getWallParticles();
        writeString(particles.size() + wallParticles.size() + "\n\n");

        for (Particle each : particles) {
            writeString(each.xyzPrint() + "\n");
        }
        for (Particle wallParticle : wallParticles) {
            writeString(wallParticle.xyzPrint() + "\n");
        }
    }


    public static void printFundamentalData(Pair<Double, Double> fundamentalData) {
        fundamentalDataWriter.write(formatter.format(fundamentalData.fst) + "," + formatter.format(fundamentalData.snd) + "\n");
        fundamentalDataWriter.flush();
    }

    public static void printAverageVelocity(final double time, final double averageVelocity) {
        averageVelocityWriter.write(formatter.format(time) + "," + formatter.format(averageVelocity) + "\n");
        averageVelocityWriter.flush();
    }

    private static void writeString(final String string) {
        writer.print(string);
        writer.flush();
    }

    private static List<Particle> getWallParticles() {
        if (wallParticles != null) {
            return wallParticles;
        }

        wallParticles = new ArrayList<>();

        double wallParticleCount = SystemConfiguration.debugManyWallParticles ? 200 : 2;
        double wallParticleRadius = SystemConfiguration.MIN_R / 30;

        for (double x = 0; x <= L; x += L / wallParticleCount) {
            Particle topWallParticle = new Particle(-1, new Vector(x, 0), new Vector(0, 0), wallParticleRadius);
            Particle bottomWallParticle = new Particle(-1, new Vector(x, W), new Vector(0, 0), wallParticleRadius);

            wallParticles.add(topWallParticle);
            wallParticles.add(bottomWallParticle);
        }

        return wallParticles;
    }
}
