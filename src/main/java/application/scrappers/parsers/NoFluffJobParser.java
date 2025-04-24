package application.scrappers.parsers;

import application.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class NoFluffJobParser implements JobParser {
    @Override
    public List<Job> parse(String website, String url) throws IOException {
        Job job = new Job(website,"Tytulek","https://ape.pl");
        return List.of(job);
    }
}
