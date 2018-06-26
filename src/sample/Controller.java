package sample;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static String searchString;
    public static String[] searchStrings;
    public static String answer;
    public static List<Path> listFiles = new ArrayList<>();
    public static final List<Path> result = new ArrayList<>();
    public static final List<Byte> counterAllFiles = new ArrayList<>();
    public static Thread searchThread;
    public static ParallelSearch parallelSearch;
    public static Finalizer finalizer;


    public static void search() {
        searchStrings = searchString.split(" ");
        parallelSearch = new ParallelSearch();
        searchThread = new Thread(parallelSearch);
        searchThread.setDaemon(true);
        searchThread.start();
    }

    public static void finalizeSearch() {
        finalizer = new Finalizer();
        Thread threadFinalize = new Thread(finalizer);
        threadFinalize.setDaemon(true);
        threadFinalize.start();
    }

    public static void errorLog() {
        ErrorLogger errorLogger = new ErrorLogger();
        Thread threadFinalize = new Thread(errorLogger);
        threadFinalize.setDaemon(true);
        threadFinalize.start();
    }

}
