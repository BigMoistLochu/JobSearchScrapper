package application.scrappers.parsers;

import application.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.List;

public class PracujPlParser implements JobParser {


    @Override
    public List<Job> parse(String website, String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Element jobLink = doc.selectFirst("#offers-list > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(3) > a:nth-child(1)");
        if (jobLink != null) {
            System.out.println("Tytuł oferty: " + jobLink.text());
            System.out.println("Link do oferty: " + jobLink.attr("href"));
            Job job = new Job(website,"title","https://...");
        } else {
            System.out.println("Nie znaleziono oferty. Struktura mogła się zmienić.");
        }
        return List.of();
    }

}
