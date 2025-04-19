package application.service;

import application.model.Job;
import application.storage.JobsCache;

public class ScrapperService {

    private final JobsCache CACHE;

    public ScrapperService(JobsCache jobsCache){
        this.CACHE = jobsCache;
    }

    public void addJobToCache(Job job){
        if(CACHE.isWebsiteAvailable(job.website())) CACHE.addJob(job);
    }


}
