package storage;

import application.model.Job;
import application.storage.JobsCache;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class JobsCacheTest {

    private final JobsCache cache = new JobsCache();

    public JobsCacheTest(){}

    @BeforeEach
    public void initCache(){
        System.out.println("Wywolam sie potem przy inicjacji");
    }


    @Test
    public void getJobsByWebsite_shouldReturnAllAddedJobsForWebsite(){
        Job job = new Job("Pracuj.pl","Junior Dev","asdhttps://...");
        Job anotherjob = new Job("Pracuj.pl","Junior Dev","sdhttps://...");

        cache.addJob("Pracuj.pl",job);
        cache.addJob("Pracuj.pl",anotherjob);

        List<Job> jobs = cache.getJobsByWebsite("Pracuj.pl");

        assertEquals(2,jobs.size());
        assertTrue(jobs.contains(job));
        assertTrue(jobs.contains(anotherjob));
    }


}
