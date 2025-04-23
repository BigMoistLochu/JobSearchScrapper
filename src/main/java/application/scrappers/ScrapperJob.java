package application.scrappers;


import application.model.Job;
import application.scrappers.parsers.JobParser;
import application.service.ScrapperTaskService;
import java.io.IOException;
import java.util.List;

public class ScrapperJob {

    private final String website;
    private final String url;
    private final long intervalSeconds;
    private final ScrapperTaskService service;
    private final JobParser parser;

    public ScrapperJob(String website,String url, long intervalSeconds, ScrapperTaskService service, JobParser parser) {
        this.website = website;
        this.url = url;
        this.intervalSeconds = intervalSeconds;
        this.service = service;
        this.parser = parser;
    }

    public Runnable getTask(){
        return () -> {
            try {
                System.out.println("Scrappuje strone: " + website);
                List<Job> parsedJobs = parser.parse(website, url);
                System.out.println("Skonczylem scrappowac strone: " + website);
            } catch (IOException e) {
                System.out.println("Błąd parsowania dla " + website + ": " + e.getMessage());
            }
        };
    }

    public long getIntervalSeconds(){
        return intervalSeconds;
    }



}
