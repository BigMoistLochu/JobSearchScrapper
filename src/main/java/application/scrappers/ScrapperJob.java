package application.scrappers;


import application.model.Job;
import application.scrappers.parsers.JobParser;
import application.service.ScrapperTaskService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScrapperJob {

    private final String website;
    private final String url;
    private final long intervalSeconds;
    private final ScrapperTaskService service;
    private final JobParser parser;
    private static final Logger logger = Logger.getLogger(ScrapperJob.class.getName());

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
                logger.info("Zaczalem scrappowac strone: " + website);
                List<Job> parsedJobs = parser.parse(website, url);
                service.filterJobs(parsedJobs,website);
                logger.info("Skonczylem scrappowac strone: " + website);
            } catch (IOException e) {
                logger.log(Level.WARNING,"Blad parsowania dla: " + website, e);
            }
        };
    }

    public long getIntervalSeconds(){
        return intervalSeconds;
    }



}
