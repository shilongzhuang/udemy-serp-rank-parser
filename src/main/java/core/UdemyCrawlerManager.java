package core;

import com.cedarsoftware.util.io.JsonWriter;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class UdemyCrawlerManager {

    private static final String output_dir = "src/main/resources/data/";

    private static final String output_dir_courses = "src/main/resources/data/courses";

    private static final String output_dir_serp = "src/main/resources/data/serp";

    private static final String BASE_URL = "https://www.udemy.com/api-2.0/";

    private static final String REFERRER = "https://www.udemy.com/courses/search/";

    //https://www.udemy.com/api-2.0/courses/740562?fields[course]=@all

    private static int urlsPerPage = 12;

    private URIBuilder uriBuilder = null;

    public UdemyCrawlerManager() {
    }

    public String buildUrlToFetch(int channelId, int page) throws URISyntaxException {
        uriBuilder = new URIBuilder(BASE_URL);
        String path = new StringBuilder("/api-2.0/channels/").append(String.valueOf(channelId)).append("/courses").toString();
        uriBuilder.setPath(path);
        uriBuilder.addParameter("is_angular_app", "true");
        uriBuilder.addParameter("is_topic_filters_enabled", "false");
        uriBuilder.addParameter("lang", "en");
        uriBuilder.addParameter("sort", "popularity");
        uriBuilder.addParameter("p", String.valueOf(page));
        return uriBuilder.toString();
    }

    public String buildUrlToFetch(int channelId) throws URISyntaxException {
        return buildUrlToFetch(channelId, 1);
    }

    public String buildUrlToFetch(int courseId, String fields) throws URISyntaxException {
        uriBuilder = new URIBuilder(BASE_URL);
        String path = new StringBuilder("/api-2.0/courses/").append(String.valueOf(courseId)).toString();
        uriBuilder.setPath(path);
        uriBuilder.addParameter("fields[course]", fields);
        return uriBuilder.toString();
    }

    public String buildUrlToFetch(String keyword, int page) throws URISyntaxException {
        uriBuilder = new URIBuilder(BASE_URL);
        String path = new StringBuilder("/api-2.0/search-courses/").toString();
        uriBuilder.setPath(path);
        uriBuilder.addParameter("src", "ukw");
        uriBuilder.addParameter("q", keyword);
        uriBuilder.addParameter("lang", "en");
        uriBuilder.addParameter("sort", "relevance");
        uriBuilder.addParameter("p", String.valueOf(page));
        return uriBuilder.toString();
    }

    public String buildUrlToFetch(String keyword) throws URISyntaxException {
        return buildUrlToFetch(keyword, 1);
    }

    public String buildJsonFileName(int courseId) {
        return new StringBuilder(String.valueOf(courseId)).append(".json").toString();
    }

    public String buildJsonFileName(int channelId, int pageNum) {
        return new StringBuilder(String.valueOf(channelId)).append("_").append(String.valueOf(pageNum)).append(".json").toString();
    }

    public String buildJsonFileName(String keyword, int pageNum) {
        return new StringBuilder(keyword).append("_").append(String.valueOf(pageNum)).append(".json").toString();
    }

    public String getJsonContent(String urlToFetch) {
        String jsonContent = null;
        try {
            if (urlToFetch.contains("search-courses")) {

                jsonContent = Jsoup.connect(urlToFetch).timeout(60000).ignoreContentType(true).referrer(REFERRER).execute().body();
            } else {
                jsonContent = Jsoup.connect(urlToFetch).timeout(60000).ignoreContentType(true).execute().body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonContent;
    }


    public void writeJsonFileToLocal(String dir, String fileName, String content) {
        try {

            File file = new File(dir, fileName);
            content = JsonWriter.formatJson(content);
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJsonFileToLocalForSearchResult(String fileName, String content) {
        writeJsonFileToLocal(output_dir_serp, fileName, content);
    }

    public void writeJsonFileToLocalForCourseDetail(String fileName, String content) {
        writeJsonFileToLocal(output_dir_courses, fileName, content);
    }

    public void writeJsonFileToLocal(String fileName, String content) {
        writeJsonFileToLocal(output_dir, fileName, content);
    }

    public int getMaxPageNum(String content) {
        JSONObject object = new JSONObject(content);
        int count = object.getInt("count");
        double pages = ((double) count) / urlsPerPage;
        return (int) Math.ceil(pages);
    }

}