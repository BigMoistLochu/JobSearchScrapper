package application.scrappers.websites;

import application.model.Job;

import java.util.List;

public interface ScrapperTask {
    List<Job> parseJobOffers();
}
