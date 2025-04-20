package application.service;

import application.model.Job;
import application.storage.JobsCache;

import java.util.List;

public class ScrapperService {

    private final JobsCache CACHE;

    public ScrapperService(JobsCache jobsCache){
        this.CACHE = jobsCache;
    }

    public void addJobToCache(List<Job> jobs){
        jobs.forEach(job -> CACHE.addJob(job));
    }


}
