package db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class CourseDetail {

    @Expose
    private int id;

    @Expose
    private String title;

    @Expose
    private String url;

    @Expose
    private String headline;

    @Expose
    @SerializedName("avg_rating")
    private double rating;

    @Expose
    @SerializedName("is_paid")
    private boolean isPaid;

    @Expose
    @SerializedName("num_reviews")
    private int reviews;

    @Expose
    @SerializedName("num_subscribers")
    private int subscribers;

    @Expose
    @SerializedName("num_published_lectures")
    private int lectures;

    @Expose
    @SerializedName("num_published_quizzes")
    private int quizzes;

    @Expose
    @SerializedName("instructional_level")
    private String level;

    @Expose
    private double hours;

    @Expose
    private boolean isBestseller;

    @Expose
    @SerializedName("has_autogenerated_captions")
    private boolean isAutoCaption;

    @Expose
    @SerializedName("has_certificate")
    private boolean isCertificate;

    @Expose(serialize = false, deserialize = true)
    @SerializedName("published_time")
    private String publishedTime;

    @Expose
    private String publishedDate;

    @Expose
    @SerializedName("last_update_date")
    private String lastUpdateDate;

    @Expose
    private int instructors;

    @Expose
    private int captions;

    @Expose(serialize = false, deserialize = true)
    @SerializedName("content_info")
    private String contentInfo;

    @Expose(serialize = false, deserialize = true)
    @SerializedName("caption_languages")
    private String[] caption;

    @Expose(serialize = false, deserialize = true)
    @SerializedName("price")
    private String priceString;

    @Expose
    private int coursePrice;

    @Expose
    private String instructorFirstName;

    @Expose
    private String instructorFullName;

    @Expose
    private int instructorId;

    @Expose
    private String instructorJobTitle;

    @Expose
    private String instructorUrl;

    @Expose
    private String category;

    @Expose
    private int categoryId;

    @Expose
    private String subCategory;

    @Expose
    private int subCategoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getLectures() {
        return lectures;
    }

    public void setLectures(int lectures) {
        this.lectures = lectures;
    }

    public int getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(int quizzes) {
        this.quizzes = quizzes;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    public boolean isAutoCaption() {
        return isAutoCaption;
    }

    public void setAutoCaption(boolean autoCaption) {
        isAutoCaption = autoCaption;
    }

    public boolean isCertificate() {
        return isCertificate;
    }

    public void setCertificate(boolean certificate) {
        isCertificate = certificate;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getInstructors() {
        return instructors;
    }

    public void setInstructors(int instructors) {
        this.instructors = instructors;
    }

    public int getCaptions() {
        return captions;
    }

    public void setCaptions(int captions) {
        this.captions = captions;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public String[] getCaption() {
        return caption;
    }

    public void setCaption(String[] caption) {
        this.caption = caption;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getInstructorFirstName() {
        return instructorFirstName;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        this.instructorFirstName = instructorFirstName;
    }

    public String getInstructorFullName() {
        return instructorFullName;
    }

    public void setInstructorFullName(String instructorFullName) {
        this.instructorFullName = instructorFullName;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorJobTitle() {
        return instructorJobTitle;
    }

    public void setInstructorJobTitle(String instructorJobTitle) {
        this.instructorJobTitle = instructorJobTitle;
    }

    public String getInstructorUrl() {
        return instructorUrl;
    }

    public void setInstructorUrl(String instructorUrl) {
        this.instructorUrl = instructorUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", headline='" + headline + '\'' +
                ", rating=" + rating +
                ", isPaid=" + isPaid +
                ", reviews=" + reviews +
                ", subscribers=" + subscribers +
                ", lectures=" + lectures +
                ", quizzes=" + quizzes +
                ", level='" + level + '\'' +
                ", hours=" + hours +
                ", isBestseller=" + isBestseller +
                ", isAutoCaption=" + isAutoCaption +
                ", isCertificate=" + isCertificate +
                ", publishedTime='" + publishedTime + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                ", instructors=" + instructors +
                ", captions=" + captions +
                ", contentInfo='" + contentInfo + '\'' +
                ", caption=" + Arrays.toString(caption) +
                ", priceString='" + priceString + '\'' +
                ", coursePrice=" + coursePrice +
                ", instructorFirstName='" + instructorFirstName + '\'' +
                ", instructorFullName='" + instructorFullName + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", instructorJobTitle='" + instructorJobTitle + '\'' +
                ", instructorUrl='" + instructorUrl + '\'' +
                ", category='" + category + '\'' +
                ", categoryId=" + categoryId +
                ", subCategory='" + subCategory + '\'' +
                ", subCategoryId=" + subCategoryId +
                '}';
    }
}
