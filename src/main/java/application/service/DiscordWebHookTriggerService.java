package application.service;

import application.Main;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordWebHookTriggerService {
    /**
     * Funkcja udostepniana publicznie dla kazdego watku ktora ma za zadania triggerowac webhooka na twoim dc
     * @param message Tresc wiadomosci jaka masz wyslac na discord
     */

    public static void send(String message){
        try {
            var request = createHttpRequest(message);

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 204 ) System.out.println("wiadomosc zostala wyslana pomyslnie");

        }catch (URISyntaxException e){
            System.out.println("Blad podczas wysylania wiadomosci:::");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest createHttpRequest(String message) throws URISyntaxException {
        String json = "{ \"content\": \"" + message + "\" }";

        return HttpRequest.newBuilder()
                .uri(new URI(getDiscordWebHook()))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private static String getDiscordWebHook(){
        String value = Main.config.getProperty("discord_webhook");
        if(value == null || value.isBlank()) throw new IllegalStateException("Brakuje wymaganej zmiennej konfiguracyjnej: discord_webhook");
        return value;
    }






}
