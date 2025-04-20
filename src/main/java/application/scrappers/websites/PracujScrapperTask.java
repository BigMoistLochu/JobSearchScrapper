package application.scrappers.websites;

import application.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class PracujScrapperTask implements ScrapperTask, Runnable{

    private final String WEBSITE = "PracujPl";
    private final String URL = "https://it.pracuj.pl/praca/trojmiasto;wp?rd=50&itth=38";

    public PracujScrapperTask() {}

    @Override
    public List<Job> parseJobOffers() {
        try {

            Document doc = Jsoup.connect(URL).get();
            Element jobLink = doc.selectFirst("#offers-list > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(3) > a:nth-child(1)");
            if (jobLink != null) {
                System.out.println("Tytuł oferty: " + jobLink.text());
                System.out.println("Link do oferty: " + jobLink.attr("href"));
                Job job = new Job(WEBSITE,"title","https://...");

            } else {
                System.out.println("Nie znaleziono oferty. Struktura mogła się zmienić.");
            }
        } catch (IOException e) {
            System.out.println("Blad przy pobieraniu strony z: " + this.getClass().getName());
        }

        return List.of();
    }


    @Override
    public void run() {
        //to sie bedzie uruchamiac -> co x sekund -> czyli parsowane oferty beda w liscie
        System.out.println("Zadanie wykonane o " + java.time.LocalTime.now() +
                " w wątku: " + Thread.currentThread().getName());
    }
}
