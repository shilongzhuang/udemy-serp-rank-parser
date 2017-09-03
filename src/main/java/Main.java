import core.UdemyCrawlerManager;
import core.UdemyParserManager;
import db.MySQLConnector;
import db.entities.CourseDetail;
import db.entities.SearchResult;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public class Main {

    private String[] keywords = {"seo", "python", "data", "design", "java", "sql"};

    private static final String FIELDS = "@all";

    private void downloadSearchResults() {

        UdemyCrawlerManager ucm = null;
        for (String keyword : keywords) {
            ucm = new UdemyCrawlerManager();

            try {
                // create the url for a given keyword on search result page 1
                String initialUrlToFetch = ucm.buildUrlToFetch(keyword);

                // obtain the page 1 json content of 12 courses
                String initialJsonContent = ucm.getJsonContent(initialUrlToFetch);

                // create the file name
                String initialFileName = ucm.buildJsonFileName(keyword, 1);

                // figure out how many search results
                int num = ucm.getMaxPageNum(initialJsonContent);

                // download the first page to local
                ucm.writeJsonFileToLocalForSearchResult(initialFileName, initialJsonContent);

                if (num >= 2) {
                    for (int i = 1; i <= num; i++) {
                        String fileName = ucm.buildJsonFileName(keyword, i);
                        String urlToFetch = ucm.buildUrlToFetch(keyword, i);
                        String jsonContent = ucm.getJsonContent(urlToFetch);
                        ucm.writeJsonFileToLocalForSearchResult(fileName, jsonContent);
                        Thread.sleep(10000);
                    }
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void downloadCourseDetails() {

        UdemyParserManager upm = new UdemyParserManager();
        UdemyCrawlerManager ucm = ucm = new UdemyCrawlerManager();

        // obtain all course id from local files
        Set<Integer> courseIds = upm.getCourseIds();
        System.out.println(courseIds.size());

        for (int courseId : courseIds) {

            try {
                // create course id file name
                String fileName = ucm.buildJsonFileName(courseId);

                // create fetch url
                String urlToFetch = ucm.buildUrlToFetch(courseId, FIELDS);

                // get the json content
                String jsonContent = ucm.getJsonContent(urlToFetch);

                // write the json to local
                ucm.writeJsonFileToLocalForCourseDetail(fileName, jsonContent);

                Thread.sleep(10000);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public void download() {
        // 1. Given a channel ID design category
        int channelId = 1626;

        UdemyCrawlerManager ucm = null;
        try {
            ucm = new UdemyCrawlerManager();
            // 2. create the url with page 1
            String initialUrlToFetch = ucm.buildUrlToFetch(channelId);

            // 3. obtain the page 1 json content
            String initialUrlJsonContent = ucm.getJsonContent(initialUrlToFetch);

            // 4. concat the file name 1626_1.json
            String initialFileName = ucm.buildJsonFileName(channelId, 1);

            // 5. get the page numbers
            int num = ucm.getMaxPageNum(initialUrlJsonContent);

            // 6. download the page 1 content to local file src/main/resources/data/1626_1.json
            ucm.writeJsonFileToLocal(initialFileName, initialUrlJsonContent);

            if (num >= 2) {
                for (int i = 1; i <= num; i++) {
                    String fileName = ucm.buildJsonFileName(channelId, i);
                    String urlToFetch = ucm.buildUrlToFetch(channelId, i);
                    String jsonContent = ucm.getJsonContent(urlToFetch);
                    ucm.writeJsonFileToLocal(fileName, jsonContent);
                    // sleep
                    Thread.sleep(10000);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void insertSearchResults() {
        MySQLConnector connector = new MySQLConnector();
        connector.createConnection();

        UdemyParserManager udemyParserManager = new UdemyParserManager();
        List<SearchResult> searchResultList = udemyParserManager.getSearchResults();
        for (SearchResult searchResult : searchResultList) {
            connector.insert(searchResult);
        }
        connector.disconnect();
    }

    private void insertCourseDetails() {
        MySQLConnector connector = new MySQLConnector();
        connector.createConnection();

        UdemyParserManager udemyParserManager = new UdemyParserManager();
        List<CourseDetail> courseDetailList = udemyParserManager.getCourseDetails();
        for (CourseDetail courseDetail: courseDetailList) {
            connector.insert(courseDetail);
        }
        connector.disconnect();
    }

    public static void main(String[] args) {
        //   new Main().download();
        Main main = new Main();
        //   main.downloadSearchResults();
        // main.insertSearchResults();
        //main.downloadCourseDetails();
        main.insertCourseDetails();

    }
}
