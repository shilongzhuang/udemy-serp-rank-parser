package db;

import core.UdemyParserManager;
import db.entities.Course;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import util.PropertyConfigure;

import java.sql.*;
import java.util.List;

public class MySQLTest {

    private String dbUrl = PropertyConfigure.getValue("mysql.url");
    private String dbUserName = PropertyConfigure.getValue("mysql.username");
    private String dbPassWord = PropertyConfigure.getValue("mysql.password");


    private Connection connection = null;


    @Before
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connected to mysql database");
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        System.out.println("test");
    }

    @Test
    public void insert() {
        String insertSql = new StringBuilder()
                .append("INSERT INTO course (ID, TITLE, URL, IS_PAID, PRICE, HEADLINE, SUBSCRIBERS,")
                .append("RATING, REVIEWS, LECTURES, SCORE, HOURS, LEVEL, CAPTIONS,")
                .append("IS_BESTSELLER, INSTRUCTORS, PUBLISHED_DATE, CHANNEL_ID, RANK) VALUES")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .toString();

        UdemyParserManager upm = new UdemyParserManager();
        JSONArray jsonArray = new UdemyParserManager().getJsonResults("1626_1.json");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Course course = upm.getCourse(jsonObject);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setString(3, course.getUrl());
            preparedStatement.setBoolean(4, course.isPaid());
            preparedStatement.setInt(5, course.getCoursePrice());
            preparedStatement.setString(6, course.getHeadline());
            preparedStatement.setInt(7, course.getSubscribers());
            preparedStatement.setDouble(8, course.getRating());
            preparedStatement.setInt(9, course.getReviews());
            preparedStatement.setInt(10, course.getLectures());
            preparedStatement.setDouble(11, course.getScore());
            preparedStatement.setDouble(12, course.getHours());
            preparedStatement.setString(13, course.getLevel());
            preparedStatement.setInt(14, course.getCaptions());
            preparedStatement.setBoolean(15, course.isBestseller());
            preparedStatement.setInt(16, course.getInstructors());
            preparedStatement.setString(17, "2015-01-05 21:19:57");

            //       preparedStatement.setString(17,course.getPublishedTime());
            preparedStatement.setInt(18, course.getChannelId());
            preparedStatement.setInt(19, course.getRank());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("done");
        }

    }


    @Test
    public void insert02() {
        MySQLConnector mc = new MySQLConnector();
        mc.createConnection();
        UdemyParserManager upm = new UdemyParserManager();
        JSONArray jsonArray = new UdemyParserManager().getJsonResults("1626_45.json");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Course course = upm.getCourse(jsonObject);
        mc.insert(course);
        mc.disconnect();
    }

    @Test
    public void insertList() {
        MySQLConnector mc = new MySQLConnector();
        mc.createConnection();
        UdemyParserManager ump = new UdemyParserManager();
        List<Course> courseList = ump.getCourses();
        for(Course course: courseList) {
            mc.insert(course);
        }
        mc.disconnect();

    }

    @Test
    public void insertSearchResults() {


    }


    @After
    public void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closed the database");
        }
    }
}
