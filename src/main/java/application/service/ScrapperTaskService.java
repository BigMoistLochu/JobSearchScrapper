package application.service;

import application.model.Job;
import application.storage.JobsCache;

import java.util.List;

public class ScrapperTaskService {

    private final JobsCache cacheInstance;

    public ScrapperTaskService(JobsCache jobsCache){
        this.cacheInstance = jobsCache;
    }

    public void addJobToCache(final List<Job> jobs) {
        if(jobs == null) throw new IllegalArgumentException("List cannot be null");
        if(!jobs.isEmpty()) jobs.forEach(job -> cacheInstance.addJob(job));
    }



}
