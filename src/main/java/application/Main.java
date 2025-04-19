package application;

import application.service.DiscordWebhookTriggerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static Properties config = new Properties();

    public static void main(String[] args) {
        initConfig();
        DiscordWebhookTriggerService.send("Hey Zlomie");
    }

    private static void initConfig() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Nie znaleziono pliku konfiguracyjnego!");
            }
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas wczytywania konfiguracji", e);
        }
    }
}