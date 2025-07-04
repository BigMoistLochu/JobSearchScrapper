package storage;

import application.model.Job;
import application.storage.JobsCache;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class JobsCacheTest {

    private final JobsCache cache = JobsCache.getINSTANCE();

    public JobsCacheTest(){}

    @BeforeEach
    public void initCache(){

    }


    @Test
    public void getJobsByWebsite_shouldReturnAllAddedJobsForWebsite(){
        Job job = new Job("Pracuj.pl","Junior Dev","https://...");
        Job anotherjob = new Job("Pracuj.pl","Junior Dev","https://...");

        cache.addJob(job);
        cache.addJob(anotherjob);

        List<Job> jobs = cache.getJobsByWebsite("Pracuj.pl");

        assertEquals(2,jobs.size());
        assertTrue(jobs.contains(job));
        assertTrue(jobs.contains(anotherjob));
    }


}
