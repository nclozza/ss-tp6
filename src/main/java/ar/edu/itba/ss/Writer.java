package ar.edu.itba.ss;

import ar.edu.itba.ss.model.Particle;
import ar.edu.itba.ss.model.Vector;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ar.edu.itba.ss.SystemConfiguration.L;

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
    private static List<Particle> wallParticles;


    public static void writeParticles(Set<Particle> particles) {
        List<Particle> wallParticles = getWallParticles();
        writeString(particles.size() + wallParticles.size() + "\n\n");

        for (Particle each : particles) {
            writeString(each.xyzPrint() + "\n");
        }
        for (Particle wallParticle : wallParticles) {
            writeString(wallParticle.xyzPrint() + "\n");
        }
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

        for (double x = 0; x < L; x += L / 200) {
            Particle topWallParticle = new Particle(-1, new Vector(x, 0), new Vector(0, 0), 0.1);
            Particle bottomWallParticle = new Particle(-1, new Vector(x, SystemConfiguration.W), new Vector(0, 0), 0.1);

            wallParticles.add(topWallParticle);
            wallParticles.add(bottomWallParticle);
        }

        return wallParticles;
    }
}
