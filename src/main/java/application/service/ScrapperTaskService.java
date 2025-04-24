package application.service;

import application.model.Job;
import application.storage.JobsCache;

import java.util.List;

public class ScrapperTaskService {

    private final JobsCache cacheInstance;

    public ScrapperTaskService(JobsCache jobsCache){
        this.cacheInstance = jobsCache;
    }

    public void filterJobs(final List<Job> scrappedJobs,final String website){
        if(scrappedJobs == null) throw new IllegalArgumentException("List cannot be null");
        if(scrappedJobs.isEmpty()) return;

        List<Job> cachedJobs = cacheInstance.getJobsByWebsite(website);
        if(cachedJobs == null) return;

        scrappedJobs.forEach(scrappedJob -> {
            if(!cachedJobs.contains(scrappedJob)){
                cacheInstance.addJob(scrappedJob);
                DiscordWebHookTriggerService.send(scrappedJob.toString());
            }
        });
    }

}
