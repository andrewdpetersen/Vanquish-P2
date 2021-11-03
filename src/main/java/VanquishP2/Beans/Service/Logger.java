package VanquishP2.Beans.Service;

import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Logger {
    private static Logger logger;
    private static int threshold; //would like to enumerate thresholds and devise something that checks inequality
    private static boolean printToConsole;
    private static boolean printToConsoleTemp;

    private Logger() {
        printToConsole = false;
        printToConsoleTemp = false;
        threshold = 3;
    }

    public static Logger getFileLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void writeLog(String message, int level) {
        try (FileWriter fileWriter = new FileWriter(getLogFileName(), true)) {
            String logEntry = formatLogEntry(message);

            if (level >= threshold) {
                fileWriter.write(logEntry);
            }

            if (printToConsole || printToConsoleTemp) {
                System.out.println(logEntry);
                printToConsoleTemp = false;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getLogFileName() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return today + ".log";
    }

    private String formatLogEntry(String message) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String stackInfo = stackTraceElements[3].toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("%s  [%s]  %s%n", timestamp, stackInfo, message);
    }

    public Logger console() {
        printToConsoleTemp = true;
        return logger;
    }

    public Logger threshold(int th) {
        threshold = th;
        return logger;
    }

    public static boolean isPrintToConsole() {
        return printToConsole;
    }

    public static void setPrintToConsole(boolean printToConsole) {
        Logger.printToConsole = printToConsole;
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        Logger.threshold = threshold;
    }
}
