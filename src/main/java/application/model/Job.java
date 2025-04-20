package application.model;

public record Job(String website,String title,String link) {
    public Job {
        if(website == null || title == null || link == null) throw new IllegalArgumentException("Fields cannot be null");
        if(website.isBlank() || title.isBlank() || link.isBlank()) throw new IllegalArgumentException("Fields cannot be blank");
        if(!link.startsWith("https://")) throw new IllegalArgumentException("Link should contain protocol");
    }
}
