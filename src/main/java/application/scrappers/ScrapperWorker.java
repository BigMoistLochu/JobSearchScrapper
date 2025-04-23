package application.scrappers;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScrapperWorker {


    private final Set<ScrapperJob> scrapperWorker;

    public ScrapperWorker(Set<ScrapperJob> scrapperWorkers){
        this.scrapperWorker = scrapperWorkers;
    }

    public void startWork(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        scrapperWorker.forEach(worker -> {
            scheduler.scheduleAtFixedRate(
                    worker.getTask(),              // co ma być wykonane
                    0,                            // ile poczekać przed pierwszym wykonaniem (0 = od razu)
                    worker.getIntervalSeconds(), // co ile sekund powtarzać
                    TimeUnit.SECONDS
            );
        });
    }
}






