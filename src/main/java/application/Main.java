package application;

import application.scrappers.ScrapperWorker;
import application.scrappers.websites.PracujScrapperTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Properties config = new Properties();

    public static void main(String[] args) {
        initConfig();
        ScrapperWorker scrapperWorker = new ScrapperWorker();
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