package service;

import application.model.Job;
import application.service.ScrapperTaskService;
import application.storage.JobsCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ScrapperTaskServiceTest {

    private final JobsCache jobsCache = JobsCache.getINSTANCE();

    @BeforeEach
    public void cleanCache(){
        jobsCache.clearCacheForTest();
    }

    @Test
    public void givenJobs_whenAddJobToCache_thenCacheShouldContainThem(){
        //given
        ScrapperTaskService service = new ScrapperTaskService(jobsCache);
        Job job = new Job("niepracujepl","Java junior","https://aadas.pl");
        List<Job> jobs = new ArrayList<>();
        jobs.add(job);

        //when
        service.addJobToCache(jobs);
        List<Job> cachedJobs = jobsCache.getJobsByWebsite("niepracujepl");
        //then
        Assertions.assertEquals(jobs,cachedJobs);
        Assertions.assertEquals(1,cachedJobs.size());
        Assertions.assertEquals(jobs.get(0),cachedJobs.get(0));
    }

    @Test
    public void givenNullListJobs_whenAddJobToCacheInTaskService_thenMethodShouldThrowIllegalArgumentException(){
        //given
        ScrapperTaskService service = new ScrapperTaskService(jobsCache);
        List<Job> jobs = null;
        //when + then
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,()-> service.addJobToCache(jobs));
        Assertions.assertEquals("List cannot be null",exception.getMessage());
    }

    @Test
    public void givenEmptyListJobs_whenAddJobToCacheInTaskService_thenCacheShouldSizeZero(){
        //given
        ScrapperTaskService service = new ScrapperTaskService(jobsCache);
        List<Job> jobs = new ArrayList<>();
        //when
        service.addJobToCache(jobs);
        Assertions.assertEquals(0,jobsCache.getSize());
    }






}
