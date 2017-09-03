package util;

import core.UdemyCrawlerManager;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class URLTest {

    private URIBuilder uriBuilder;

    @Before
    public void create() {
        try {
            uriBuilder = new URIBuilder("https://www.udemy.com/api-2.0/channels/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01() {
        uriBuilder.setPath("/api-2.0/channels/1654/courses");
        uriBuilder.addParameter("is_angular_app", "true");
        uriBuilder.addParameter("is_topic_filters_enabled", "false");
        uriBuilder.addParameter("lang", "en");
        uriBuilder.addParameter("sort", "popularity");
        System.out.println(uriBuilder.toString());
    }

    @Test
    public void test02() {
        int channelId = 1654;
        String path = new StringBuilder()
                .append("/api-2.0/channels/")
                .append(String.valueOf(channelId))
                .append("/courses").toString();
        uriBuilder.setPath(path);
        uriBuilder.addParameter("is_angular_app", "true");
        uriBuilder.addParameter("is_topic_filters_enabled", "false");
        uriBuilder.addParameter("lang", "en");
        uriBuilder.addParameter("sort", "popularity");
        System.out.println(uriBuilder.toString());
    }

    @Test
    public void test03() throws URISyntaxException {
        UdemyCrawlerManager ucm = new UdemyCrawlerManager();
        String url = ucm.buildUrlToFetch(1654);
        System.out.println(url);
        for(int i=1; i<=3; i++) {
            String newUrl = ucm.buildUrlToFetch(1654, i);
            System.out.println(newUrl);
        }

    }

    @Test
    public void test04() {
        String fileName = new UdemyCrawlerManager().buildJsonFileName(1624,2);
        System.out.println(fileName);
    }

    @Test
    public void test05() {
        String[] keywords = { "seo", "python", "data" ,"design", "java", "sql"};
        for(String keyword: keywords) {
            System.out.println(keyword);
        }
    }
}
