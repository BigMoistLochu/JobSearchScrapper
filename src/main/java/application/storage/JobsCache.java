package application.storage;


import application.model.Job;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobsCache {

    private final Map<String, List<Job>> CACHE = new ConcurrentHashMap<>();
    private static JobsCache INSTANCE = null;
    /**
     * Singleton Pattern
     */
    private JobsCache(){}

    public synchronized static JobsCache getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new JobsCache();
        }
        return INSTANCE;
    }

    public void addJob(Job job){
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

    public int getSize(){
        return CACHE.size();
    }

    public void clearCacheForTest(){
        CACHE.clear();
    }

}
