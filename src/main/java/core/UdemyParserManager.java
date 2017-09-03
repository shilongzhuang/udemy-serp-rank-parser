package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.entities.Course;
import db.entities.CourseDetail;
import db.entities.SearchResult;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MyDateUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UdemyParserManager {


    private ClassLoader classLoader = getClass().getClassLoader();

    private static final String data_dir = "data";
    private static final String data_dir_courses = "data/courses";

    private static final String data_dir_serp = "data/serp";

    public UdemyParserManager() {

    }

    public JSONObject getJsonContent(String dir, String fileName) {
        fileName = new StringBuilder(dir).append("/").append(fileName).toString();
        File file = new File(classLoader.getResource(fileName).getFile());
        JSONObject object = null;
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            object = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONArray getJsonResults(String fileName) {
        return getJsonResults(data_dir, fileName, "results");
    }

    public JSONArray getJsonResults(String dir, String fileName, String key) {
        fileName = new StringBuilder(dir).append("/").append(fileName).toString();
        File file = new File(classLoader.getResource(fileName).getFile());
        JSONArray results = null;
        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            JSONObject object = new JSONObject(content);
            results = object.getJSONArray(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }


    public SearchResult getSearchResult(JSONObject obj) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        SearchResult searchResult = gson.fromJson(obj.toString(), SearchResult.class);

        return searchResult;
    }

    public Course getCourse(JSONObject obj) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Course course = gson.fromJson(obj.toString(), Course.class);
        String priceString = course.getPriceString();

        if ("Free".equals(priceString.trim())) {
            course.setCoursePrice(0);
        } else {
            course.setCoursePrice(Integer.parseInt(course.getPriceString().replace("$", "")));
        }

        String contentInfo = course.getContentInfo();
        double timeLength = Double.parseDouble(contentInfo.trim().split(" ")[0]);

        // hour or hours
        if (contentInfo.contains("hour")) {
            course.setHours(timeLength);
        } else {
            course.setHours(timeLength / 60);
        }

        course.setCaptions(course.getCaption().length);
        course.setBestseller(!obj.isNull("bestseller_badge_content"));
        course.setInstructors(obj.getJSONArray("visible_instructors").length());

        course.setPublishedDate(MyDateUtil.convert(course.getPublishedTime()));

        return course;
    }


    public CourseDetail getCourseDetail(JSONObject obj) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        CourseDetail courseDetail = gson.fromJson(obj.toString(), CourseDetail.class);
        String priceString = courseDetail.getPriceString();

        if ("Free".equals(priceString.trim())) {
            courseDetail.setCoursePrice(0);
        } else {
            courseDetail.setCoursePrice(Integer.parseInt(courseDetail.getPriceString().replace("$", "")));
        }

        String contentInfo = courseDetail.getContentInfo();
        double timeLength = Double.parseDouble(contentInfo.trim().split(" ")[0]);

        // hour or hours
        if (contentInfo.contains("hour")) {
            courseDetail.setHours(timeLength);
        } else {
            courseDetail.setHours(timeLength / 60);
        }

        courseDetail.setCaptions(courseDetail.getCaption().length);
        courseDetail.setBestseller(!obj.isNull("bestseller_badge_content"));

        JSONArray instructors = obj.getJSONArray("visible_instructors");
        JSONObject primaryInstructor = instructors.getJSONObject(0);
        courseDetail.setInstructors(instructors.length());
        courseDetail.setInstructorFirstName(primaryInstructor.getString("name"));
        courseDetail.setInstructorFullName(primaryInstructor.getString("title"));
        courseDetail.setInstructorId(primaryInstructor.getInt("id"));
        courseDetail.setInstructorJobTitle(primaryInstructor.getString("job_title"));
        courseDetail.setInstructorUrl(primaryInstructor.getString("url"));

        if(obj.isNull("primary_category")) {
            courseDetail.setCategory(null);
            courseDetail.setCategoryId(-1);
        } else {
            JSONObject category = obj.getJSONObject("primary_category");
            courseDetail.setCategory(category.getString("title"));
            courseDetail.setCategoryId(category.getInt("id"));
        }

        if(obj.isNull("primary_subcategory")) {
            courseDetail.setCategory(null);
            courseDetail.setCategoryId(-1);
        } else {
            JSONObject subCategory = obj.getJSONObject("primary_subcategory");
            courseDetail.setSubCategory(subCategory.getString("title"));
            courseDetail.setSubCategoryId(subCategory.getInt("id"));
        }

        courseDetail.setPublishedDate(MyDateUtil.convert(courseDetail.getPublishedTime()));

        return courseDetail;
    }


    public List<Course> getCourses() {
        List<Course> courseList = new ArrayList<Course>();
        File[] files = new File(classLoader.getResource(data_dir).getFile()).listFiles();
        //  assert files != null;
        if (files != null) {
            for (File file : files) {
                JSONArray jsonArray = this.getJsonResults(file.getName());

                String fileName = file.getName().split(".json")[0];
                String[] tmp = fileName.split("_");
                int channel_id = Integer.parseInt(tmp[0]);
                int base = Integer.parseInt(tmp[1]);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Course course = this.getCourse(jsonObject);
                    course.setRank((base - 1) * 12 + 1 + i);
                    course.setChannelId(channel_id);
                    System.out.println(course.toString());
                    courseList.add(course);
                }
            }
        }
        return courseList;
    }

    public List<SearchResult> getSearchResults() {
        List<SearchResult> searchResultList = new ArrayList<SearchResult>();
        File[] files = new File(classLoader.getResource(data_dir_serp).getFile()).listFiles();
        if (files != null) {
            for (File file : files) {
                // the object key is courses
                JSONArray jsonArray = this.getJsonResults(data_dir_serp, file.getName(), "courses");

                String fileName = file.getName().split(".json")[0];
                String[] tmp = fileName.split("_");
                String keyword = tmp[0];
                int base = Integer.parseInt(tmp[1]);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SearchResult searchResult = this.getSearchResult(jsonObject);
                    searchResult.setRank((base - 1) * 12 + 1 + i);
                    searchResult.setKeyword(keyword);
                    System.out.println(searchResult.toString());
                    searchResultList.add(searchResult);
                }
            }
        }
        return searchResultList;
    }

    public Set<Integer> getCourseIds() {
        List<SearchResult> searchResultList = this.getSearchResults();
        Set<Integer> courseIdList = new HashSet<Integer>();
        //duplication
        for(SearchResult searchResult: searchResultList) {
            courseIdList.add(searchResult.getCourseId());
        }
        return courseIdList;
    }

    public List<CourseDetail> getCourseDetails() {
        List<CourseDetail> courseDetailList = new ArrayList<CourseDetail>();
        File[] files = new File(classLoader.getResource(data_dir_courses).getFile()).listFiles();
        if (files != null) {
            for (File file : files) {
                JSONObject jsonObject = this.getJsonContent(data_dir_courses, file.getName());
                System.out.println(file.getName());
                CourseDetail courseDetail = this.getCourseDetail(jsonObject);
                courseDetailList.add(courseDetail);
            }
        }
        return courseDetailList;
    }

}