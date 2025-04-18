package application.model;

public record Job(String website,String title,String link) {

    public Job {
        if(!link.startsWith("https://")) throw new IllegalArgumentException("link should contain protocol");
    }

}
