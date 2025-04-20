package model;

import application.model.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    //test cases:
    //one of the fields have null value
    //one of the fields have blank/empty value
    //link doesn't have https protocol

    @Test
    public void shouldCreateJobWhenDataIsValid(){
        //given
        String validWebsite = "Pracujpl";
        String validTitle = "SoftwareDev";
        String validLink = "https://";
        //when
        Job job = new Job(validWebsite,validTitle,validLink);
        //then
        assertEquals(validWebsite,job.website());
        assertEquals(validTitle,job.title());
        assertEquals(validLink,job.link());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenWebsiteIsNull(){
        //given
        String website = null;
        String title = "SoftwareDev";
        String link = "https://";
        //then+when
        assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenWebsiteIsBlank(){
        //given
        String website = " ";
        String title = "SoftwareDev";
        String link = "https://";
        //then+when
        assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTitleIsNull(){
        //given
        String website = "praacuj";
        String title = null;
        String link = "https://";
        //then+when
        assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTitleIsBlank(){
        //given
        String website = "pracujpl";
        String title = "";
        String link = "https://";
        //then+when
        assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenLinkIsNull(){
        //given
        String website = "praacuj";
        String title = "sooft";
        String link = null;
        //then+when
        assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenLinkNotStartWithValidProtocol(){
        //given
        String website = "praacuj";
        String title = "sooft";
        String link = "http://";
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
        //then
        assertEquals("Link should contain protocol",exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenLinkIsBlank(){
        //given
        String website = "pracujpl";
        String title = "Soft";
        String link = "";
        //then+when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Job(website,title,link));
        assertEquals("Fields cannot be blank",exception.getMessage());
    }
}
