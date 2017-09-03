package core;

import com.cedarsoftware.util.io.JsonWriter;
import core.UdemyCrawlerManager;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class CrawlTest {

    private String urlToFetch = "https://www.udemy.com/api-2.0/channels/1626/courses" +
            "?is_angular_app=true&is_topic_filters_enabled=false&lang=en&sort=popularity";


    private String path = "src/main/resources/data";

    private String jsonContent = null;

    @Before
    public void init() {
        try {
            jsonContent = Jsoup.connect(urlToFetch).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void writeStringToLocalFile() {

        try {
            String string = "test";
            path = path + "/test.json";
            File file = new File(path);
            System.out.println(file.getPath());
            FileUtils.writeStringToFile(file, string, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeJsonToLocalFile() {

        try {
            path = path + "/test.json";
            File file = new File(path);
            jsonContent = JsonWriter.formatJson(jsonContent);
            FileUtils.writeStringToFile(file, jsonContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void writeJsonToLocal2() {
        new UdemyCrawlerManager().writeJsonFileToLocal("test.json", jsonContent);
    }

    @Test
    public void getCourseContent() {

    }

    @Test
    public void getSERPContent() {
        String keyword = "SEO";
        UdemyCrawlerManager udemyCrawlerManager = new UdemyCrawlerManager();
        try {
            String urlToFetch= udemyCrawlerManager.buildUrlToFetch(keyword,1);
            String content = udemyCrawlerManager.getJsonContent(urlToFetch);
            System.out.println(content);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
