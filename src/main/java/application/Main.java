package application;

import application.scrappers.ScrapperJob;
import application.scrappers.ScrapperWorker;
import application.scrappers.parsers.NoFluffJobParser;
import application.service.ScrapperTaskService;
import application.storage.JobsCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    public static Properties config = new Properties();

    public static void main(String[] args) {
        initConfig();

        JobsCache cache = JobsCache.getINSTANCE();

        ScrapperTaskService service = new ScrapperTaskService(cache);

        Set<ScrapperJob> scrapperWorkers = initWorkers(service);

        ScrapperWorker scrapperWorker = new ScrapperWorker(scrapperWorkers);
        scrapperWorker.startWork();
    }

    private static Set<ScrapperJob> initWorkers(ScrapperTaskService service){
        Set<ScrapperJob> scrappWorkers = new HashSet<>();
        ScrapperJob noFluffJob = new ScrapperJob("NoFluffJobs", "https://cwww", 5, service, new NoFluffJobParser());
        scrappWorkers.add(noFluffJob);

        return scrappWorkers;
    }


    private static void initConfig() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Nie znaleziono pliku config.properties w folderze resources!");
            }
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas wczytywania konfiguracji z pliku config.properties", e);
        }

        try {
            InputStream loggingConfig = Main.class.getClassLoader().getResourceAsStream("logging.properties");
            if (loggingConfig == null) {
                throw new RuntimeException("Nie znaleziono pliku logging.properties w folderze resources!");
            }
            LogManager.getLogManager().readConfiguration(loggingConfig);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas wczytywania konfiguracji z pliku logging.properties", e);
        }
    }
}

