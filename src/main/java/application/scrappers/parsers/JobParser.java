package application.scrappers.parsers;

import application.model.Job;
import java.io.IOException;
import java.util.List;

public interface JobParser {
    List<Job> parse(String website, String url) throws IOException;
}
