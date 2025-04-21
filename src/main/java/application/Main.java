package application;

import application.scrappers.ScrapperWorker;
import application.service.ScrapperTaskService;
import application.storage.JobsCache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    public static Properties config = new Properties();

    public static void main(String[] args) {
        initConfig();

        JobsCache cache = JobsCache.getINSTANCE();
        ScrapperTaskService service = new ScrapperTaskService(cache);
        ScrapperWorker scrapperWorker = new ScrapperWorker(service);
        scrapperWorker.startWork();
    }


    private static void initConfig() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Nie znaleziono pliku config.properties w folderze resources!");
            }
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas wczytywania konfiguracji", e);
        }
    }
}