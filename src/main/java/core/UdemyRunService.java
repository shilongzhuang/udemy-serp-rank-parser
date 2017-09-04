package core;

import db.MySQLConnector;
import db.entities.CourseDetail;
import db.entities.SearchResult;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public class UdemyRunService {


    private static final String FIELDS = "@all";

    private UdemyParserManager upm = new UdemyParserManager();

    private UdemyCrawlerManager ucm = new UdemyCrawlerManager();

    private MySQLConnector connector = new MySQLConnector();

    private static UdemyRunService service = null;

    private UdemyRunService() {

    }

    public static UdemyRunService build() {
        if (service == null) {
            service = new UdemyRunService();
            service.connector.createConnection();
        }
        return service;
    }

    public void downloadSearchResults(String[] keywords) {

        for (String keyword : keywords) {

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

    public void downloadCourseDetails(Set<Integer> courseIds) {

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

                Thread.sleep(1000);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void downloadCourseDetails() {

        // obtain all course id from local files
        Set<Integer> courseIds = upm.getCourseIds();

        downloadCourseDetails(courseIds);

    }


    public void download() {
        // 1. Given a channel ID design category
        int channelId = 1626;

        try {

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


    public void insertSearchResults() {

        List<SearchResult> searchResultList = upm.getSearchResults();
        for (SearchResult searchResult : searchResultList) {
            connector.insert(searchResult);
        }
    }

    public void insertCourseDetails() {

        List<CourseDetail> courseDetailList = upm.getCourseDetails();
        for (CourseDetail courseDetail : courseDetailList) {
            connector.insert(courseDetail);
        }
    }


    public UdemyParserManager getUpm() {
        return upm;
    }

    public UdemyCrawlerManager getUcm() {
        return ucm;
    }


    public void shutdown() {
        if (connector!=null) {
            connector.disconnect();
        }
    }
}
