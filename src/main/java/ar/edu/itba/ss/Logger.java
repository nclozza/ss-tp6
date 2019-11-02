package ar.edu.itba.ss;

public class Logger {
    private static Level currentLevel = SystemConfiguration.debugLogEverything ? Level.Debug : Level.Info;

    enum Level {
        Info,
        Debug;

        public boolean greaterEq(Level level) {
            if (this.equals(Debug)) {
                return true;
            }

            if (this.equals(Info) && level.equals(Info)) {
                return true;
            }

            return false;
        }
    }

    private static void log(Level level, String message) {
        if (currentLevel.greaterEq(level)) {
            System.out.println(message);
        }
    }

    public static void info(String message) {
        log(Level.Info, message);
    }

    public static void debug(String message) {
        log(Level.Debug, message);
    }
}
