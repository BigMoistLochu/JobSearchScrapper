package application.scrappers.parsers;

import application.model.Job;
import java.io.IOException;
import java.util.List;

public class NoFluffJobParser implements JobParser {
    @Override
    public List<Job> parse(String website, String url) throws IOException {
        return List.of();
    }
}
