package application.scrappers;


import application.scrappers.websites.PracujScrapperTask;
import application.service.ScrapperTaskService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScrapperWorker {

    private final ScrapperTaskService service;

    private final Set<Runnable> setOfWorkers = new HashSet<>();

    public ScrapperWorker(ScrapperTaskService service){
        this.service = service;
    }



    public void startWork(){

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        setOfWorkers.forEach(job -> {
            scheduler.scheduleAtFixedRate(
                    job,      // co ma być wykonane
                    0,         // ile poczekać przed pierwszym wykonaniem (0 = od razu)
                    5,         // co ile sekund powtarzać
                    TimeUnit.SECONDS
            );
        });
    }

}






