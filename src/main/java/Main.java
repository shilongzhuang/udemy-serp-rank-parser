import core.UdemyRunService;

import java.util.Set;

public class Main {

    private String[] keywords = {"seo", "python", "data", "design", "java", "sql"};


    public static void main(String[] args) {

        UdemyRunService service = UdemyRunService.build();

        service.downloadSearchResults(new String[]{"seo"});

        Set<Integer> seoCourseIds = service.getUpm().getCourseIds("seo");
        System.out.println(seoCourseIds.size());
        service.downloadCourseDetails(seoCourseIds);

        // service.downloadCourseDetails();

        service.insertSearchResults();

        service.insertCourseDetails();

        service.shutdown();
    }
}
