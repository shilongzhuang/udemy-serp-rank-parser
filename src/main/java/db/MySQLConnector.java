package db;

import db.entities.Course;
import db.entities.CourseDetail;
import db.entities.SearchResult;
import util.PropertyConfigure;

import java.sql.*;

public class MySQLConnector {


    private String dbUrl = PropertyConfigure.getValue("mysql.url");
    private String dbUserName = PropertyConfigure.getValue("mysql.username");
    private String dbPassWord = PropertyConfigure.getValue("mysql.password");


    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;


    public void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Course course) {
        String insertSql = new StringBuilder()
                .append("INSERT INTO course (ID, TITLE, URL, IS_PAID, PRICE, HEADLINE, SUBSCRIBERS,")
                .append("RATING, REVIEWS, LECTURES, SCORE, HOURS, LEVEL, CAPTIONS,")
                .append("IS_BESTSELLER, INSTRUCTORS, PUBLISHED_DATE, CHANNEL_ID, RANK) VALUES")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .append("ON DUPLICATE KEY UPDATE ")
                .append("ID =VALUES(ID), TITLE=VALUES(TITLE), URL =VALUES(URL), IS_PAID=VALUES(IS_PAID),")
                .append("PRICE=VALUES(PRICE), HEADLINE =VALUES(HEADLINE), SUBSCRIBERS = VALUES(SUBSCRIBERS),")
                .append("RATING=VALUES(RATING), REVIEWS=VALUES(REVIEWS), LECTURES=VALUES(LECTURES),")
                .append("SCORE = VALUES(SCORE), HOURS =VALUES(HOURS), LEVEL=VALUES(LEVEL), CAPTIONS=VALUES(CAPTIONS),")
                .append("IS_BESTSELLER =VALUES(IS_BESTSELLER), INSTRUCTORS =VALUES(INSTRUCTORS),")
                .append("PUBLISHED_DATE=VALUES(PUBLISHED_DATE),CHANNEL_ID=VALUES(CHANNEL_ID), RANK=VALUES(RANK)")
                .toString();
        try {
            ps = connection.prepareStatement(insertSql);
            ps.setInt(1, course.getId());
            ps.setString(2, course.getTitle());
            ps.setString(3, course.getUrl());
            ps.setBoolean(4, course.isPaid());
            ps.setInt(5, course.getCoursePrice());
            ps.setString(6, course.getHeadline());
            ps.setInt(7, course.getSubscribers());
            ps.setDouble(8, course.getRating());
            ps.setInt(9, course.getReviews());
            ps.setInt(10, course.getLectures());
            ps.setDouble(11, course.getScore());
            ps.setDouble(12, course.getHours());
            ps.setString(13, course.getLevel());
            ps.setInt(14, course.getCaptions());
            ps.setBoolean(15, course.isBestseller());
            ps.setInt(16, course.getInstructors());
            ps.setString(17, course.getPublishedDate());
            ps.setInt(18, course.getChannelId());
            ps.setInt(19, course.getRank());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(ps);
        }

    }


    public void insert(SearchResult s) {
        String insertSql = new StringBuilder()
                .append("INSERT INTO search_result (COURSE_ID, KEYWORD, URL, SCORE, PREDICTIVE_SCORE, RANK) VALUES")
                .append("(?,?,?,?,?,?)")
                .append("ON DUPLICATE KEY UPDATE ")
                .append("COURSE_ID =VALUES(COURSE_ID), KEYWORD=VALUES(KEYWORD), URL =VALUES(URL), ")
                .append("SCORE=VALUES(SCORE), PREDICTIVE_SCORE =VALUES(PREDICTIVE_SCORE), RANK=VALUES(RANK)")
                .toString();
        try {
            ps = connection.prepareStatement(insertSql);
            ps.setInt(1, s.getCourseId());
            ps.setString(2, s.getKeyword());
            ps.setString(3, s.getUrl());
            ps.setDouble(4, s.getScore());
            ps.setDouble(5, s.getPredictiveScore());
            ps.setInt(6, s.getRank());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(ps);
        }
    }


    public void insert(CourseDetail cd) {
        String insertSql = new StringBuilder()
                .append("INSERT INTO course_detail (ID, TITLE, URL, IS_PAID, PRICE, HEADLINE, SUBSCRIBERS,")
                .append("RATING, REVIEWS, LECTURES, QUIZZES, HOURS, LEVEL, CAPTIONS, IS_BESTSELLER,")
                .append("INSTRUCTORS, HAS_AUTO_CAPTION, HAS_CERTIFICATE, PUBLISHED_DATE, LAST_UPDATE_DATE,")
                .append("INSTRUCTOR_FULL_NAME, INSTRUCTOR_FIRST_NAME, INSTRUCTOR_JOB_TITLE, INSTRUCTOR_URL,")
                .append("INSTRUCTOR_ID, CATEGORY, CATEGORY_ID, SUB_CATEGORY, SUB_CATEGORY_ID) VALUES")
                .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .append("ON DUPLICATE KEY UPDATE ")
                .append("ID =VALUES(ID), TITLE=VALUES(TITLE), URL =VALUES(URL), IS_PAID=VALUES(IS_PAID),")
                .append("PRICE=VALUES(PRICE), HEADLINE =VALUES(HEADLINE), SUBSCRIBERS = VALUES(SUBSCRIBERS),")
                .append("RATING=VALUES(RATING), REVIEWS=VALUES(REVIEWS), LECTURES=VALUES(LECTURES),")
                .append("QUIZZES = VALUES(QUIZZES), HOURS =VALUES(HOURS), LEVEL=VALUES(LEVEL), CAPTIONS=VALUES(CAPTIONS),")
                .append("IS_BESTSELLER =VALUES(IS_BESTSELLER), INSTRUCTORS =VALUES(INSTRUCTORS),")
                .append("HAS_AUTO_CAPTION =VALUES(HAS_AUTO_CAPTION), HAS_CERTIFICATE =VALUES(HAS_CERTIFICATE),")
                .append("PUBLISHED_DATE =VALUES(PUBLISHED_DATE), LAST_UPDATE_DATE =VALUES(LAST_UPDATE_DATE),")
                .append("INSTRUCTOR_FULL_NAME =VALUES(INSTRUCTOR_FULL_NAME), INSTRUCTOR_FIRST_NAME =VALUES(INSTRUCTOR_FIRST_NAME),")
                .append("INSTRUCTOR_JOB_TITLE =VALUES(INSTRUCTOR_JOB_TITLE), INSTRUCTOR_URL =VALUES(INSTRUCTOR_URL),")
                .append("INSTRUCTOR_ID =VALUES(INSTRUCTOR_ID), CATEGORY =VALUES(CATEGORY), CATEGORY_ID =VALUES(CATEGORY_ID),")
                .append("SUB_CATEGORY=VALUES(SUB_CATEGORY),SUB_CATEGORY_ID=VALUES(SUB_CATEGORY_ID)")
                .toString();
        try {
            ps = connection.prepareStatement(insertSql);
            ps.setInt(1, cd.getId());
            ps.setString(2, cd.getTitle());
            ps.setString(3, cd.getUrl());
            ps.setBoolean(4, cd.isPaid());
            ps.setInt(5, cd.getCoursePrice());
            ps.setString(6, cd.getHeadline());
            ps.setInt(7, cd.getSubscribers());
            ps.setDouble(8, cd.getRating());
            ps.setInt(9, cd.getReviews());
            ps.setInt(10, cd.getLectures());
            ps.setInt(11, cd.getQuizzes());
            ps.setDouble(12, cd.getHours());
            ps.setString(13, cd.getLevel());
            ps.setInt(14, cd.getCaptions());
            ps.setBoolean(15, cd.isBestseller());
            ps.setInt(16, cd.getInstructors());
            ps.setBoolean(17, cd.isAutoCaption());
            ps.setBoolean(18, cd.isCertificate());
            ps.setString(19, cd.getPublishedDate());
            ps.setString(20, cd.getLastUpdateDate());
            ps.setString(21, cd.getInstructorFullName());
            ps.setString(22, cd.getInstructorFirstName());
            ps.setString(23, cd.getInstructorJobTitle());
            ps.setString(24, cd.getInstructorUrl());
            ps.setInt(25, cd.getInstructorId());
            ps.setString(26, cd.getCategory());
            ps.setInt(27, cd.getCategoryId());
            ps.setString(28, cd.getSubCategory());
            ps.setInt(29, cd.getSubCategoryId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(ps);
        }

    }


    public void close(PreparedStatement preparedStatement) {

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps = null;
            connection = null;
            System.out.println("Closed the database");
        }
    }
}
