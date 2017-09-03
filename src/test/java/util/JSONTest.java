package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.UdemyCrawlerManager;
import core.UdemyParserManager;
import db.entities.Course;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class JSONTest {


    private String jsonFile = "examples/courses.json";

    private String dir_examples = "examples";

    private String dir_data = "data";

    private String content;


    @Before
    public void init() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(jsonFile).getFile());
          //  System.out.println(file.getPath());
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printJson() {
        System.out.println(content);
    }

    @Test
    public void getCountsOfResults() {
        JSONObject object = new JSONObject(content);
        int count = object.getInt("count");
        System.out.println(count / 12);
    }

    @Test
    public void parserObjects() {
        JSONObject object = new JSONObject(content);
        JSONArray results = object.getJSONArray("results");
        System.out.println(results.length());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(results);
        System.out.println(results);
        System.out.println(jsonArray);
        System.out.println(results.length());
    }

    @Test
    public void readAllFiles() {
        ClassLoader classLoader = getClass().getClassLoader();
        File[] files = new File(classLoader.getResource(dir_examples).getFile()).listFiles();

        for (File file: files) {
            System.out.println(file.getName());
        }

        System.out.println(files[2].getName());
    }

    @Test
    public void testGetPagesNum() throws URISyntaxException {
        int num = new UdemyCrawlerManager().getMaxPageNum(content);
        System.out.println(num);
    }

    @Test
    public void readAllJsonFiles() {
        ClassLoader classLoader = getClass().getClassLoader();
        File[] files = new File(classLoader.getResource(dir_data).getFile()).listFiles();
        for (File file : files) {
            String fileName =  file.getName().split(".json")[0];
            System.out.println(file.getName());
            String[] tmp = fileName.split("_");
            int category_id = Integer.parseInt(tmp[0]);
            int rank = Integer.parseInt(tmp[1]);
            System.out.println(rank + " " + category_id);
          // System.out.println(file.getPath());
        }
    }

    @Test
    public void returnJsonArray() {
        JSONArray jsonArray = new UdemyParserManager().getJsonResults("1626_1.json");
        System.out.println(jsonArray.getJSONObject(0));

        for(int i=0; i<jsonArray.length(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }
    }

    @Test
    public void returnSERPJsonArray() {
        UdemyParserManager udemyParserManager = new UdemyParserManager();
        JSONArray jsonArray = udemyParserManager.getJsonResults(dir_examples, "serp.json", "courses");
        System.out.println(jsonArray.getJSONObject(0));

        for(int i=0; i<jsonArray.length(); i++) {
          //  System.out.println(jsonArray.getJSONObject(i));
            System.out.println(udemyParserManager.getSearchResult(jsonArray.getJSONObject(i)));
        }
    }

    @Test
    public void returnCourseDetailJsonArray() {
        UdemyParserManager udemyParserManager = new UdemyParserManager();
        JSONObject jsonObject = udemyParserManager.getJsonContent(dir_examples,"coursedetail.json");
        System.out.println(udemyParserManager.getCourseDetail(jsonObject));

    }


    @Test
    public void createCourseFromGson() {
        JSONArray jsonArray = new UdemyParserManager().getJsonResults("1626_1.json");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
      //  System.out.println(jsonObject.toString());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
       // Gson gson = new Gson();
        Course course = gson.fromJson(jsonObject.toString(), Course.class);
        course.setHours(12);
        System.out.println(Integer.parseInt("$175".replace("$","")));
   //     System.out.println(course.getCoursePrice());

        System.out.println(course.getContentInfo());

        course.setCoursePrice(Integer.parseInt(course.getPriceString().replace("$", "")));

        System.out.println(course.getCaption().length);

        System.out.println(!jsonObject.isNull("bestseller_badge_content"));

        System.out.println(jsonObject.getJSONArray("visible_instructors").length());

        System.out.println(course.toString());
        String json = gson.toJson(course);
        System.out.println(json);

    }

    @Test
    public void getCourseTest() {
        UdemyParserManager upm = new UdemyParserManager();
      //JSONArray jsonArray = upm.getJsonResults("1626_1.json");

        ClassLoader classLoader = getClass().getClassLoader();
        File[] files = new File(classLoader.getResource(dir_data).getFile()).listFiles();
        for (File file : files) {
            JSONArray jsonArray = upm.getJsonResults(file.getName());

            String fileName =  file.getName().split(".json")[0];

            String[] tmp = fileName.split("_");
            int category_id = Integer.parseInt(tmp[0]);
            int base = Integer.parseInt(tmp[1]);

            for(int i=0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Course course = upm.getCourse(jsonObject);
                course.setRank((base-1)*12+1+i);
                course.setChannelId(category_id);
                System.out.println(course.toString());
            }
        }

    }


    @Test
    public void parserAll() {
        UdemyParserManager upm = new UdemyParserManager();
        List<Course> courseList = upm.getCourses();
        int count = 0;
        for(Course course: courseList) {
            count+=1;
            System.out.println(course.toString());
        }
        System.out.println(count);
    }
}
