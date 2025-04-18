package application.service;

import application.model.Job;
import application.storage.JobsCache;

public class ScrapperService {

    private final JobsCache CACHE;

    public ScrapperService(JobsCache jobsCache){
        this.CACHE = jobsCache;
    }


    public void addJobToCache(String website,Job job){
        if(CACHE.isWebsiteAvailable(website)) CACHE.addJob(website,job);
    }


}
