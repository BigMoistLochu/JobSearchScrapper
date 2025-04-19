package application.storage;


import application.model.Job;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobsCache {

    private final Map<String, List<Job>> CACHE = new ConcurrentHashMap<>();

    public void addJob(Job job){
        if(job.website() == null) throw new IllegalArgumentException("Website cannot be null");
        if(job.website().isBlank()) throw new IllegalArgumentException("Website cannot be blank");
        if(job == null) throw new IllegalArgumentException("Job offer cannot be null");

        List<Job> jobs = CACHE.get(job.website());

        if(jobs == null) {
            jobs = new ArrayList<>();
            CACHE.put(job.website(),jobs);
        }

        jobs.add(job);
    }

    public List<Job> getJobsByWebsite(String website){
        if(!isWebsiteAvailable(website)) throw new IllegalArgumentException("Nie ma takiej strony w cachu");
        return CACHE.get(website);
    }


    public boolean isWebsiteAvailable(String website) {
        if (website == null || website.isBlank()) {
            return false;
        }
        return CACHE.containsKey(website);
    }

}
