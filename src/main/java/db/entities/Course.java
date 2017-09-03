package db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


public class Course {

    @Expose(serialize = false, deserialize = true)
    @SerializedName("price")
    private String priceString;

    @Expose
    private int coursePrice;

    @Expose
    private String title;

    @Expose
    private String headline;

    @Expose
    private String url;

    @Expose
    private int id;

    private int channelId;

    @Expose
    @SerializedName("avg_rating_recent")
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
    @SerializedName("instructional_level")
    private String level;

    @Expose
    private double hours;

    @Expose
    private boolean isBestseller;

    @Expose
    private int rank;

    @Expose
    @SerializedName("relevancy_score")
    private double score;

    @Expose(serialize = false, deserialize = true)
    @SerializedName("published_time")
    private String publishedTime;

    @Expose
    private String publishedDate;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "priceString='" + priceString + '\'' +
                ", coursePrice=" + coursePrice +
                ", title='" + title + '\'' +
                ", headline='" + headline + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                ", channelId=" + channelId +
                ", rating=" + rating +
                ", isPaid=" + isPaid +
                ", reviews=" + reviews +
                ", subscribers=" + subscribers +
                ", lectures=" + lectures +
                ", level='" + level + '\'' +
                ", hours=" + hours +
                ", isBestseller=" + isBestseller +
                ", rank=" + rank +
                ", score=" + score +
                ", publishedTime='" + publishedTime + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", instructors=" + instructors +
                ", captions=" + captions +
                ", contentInfo='" + contentInfo + '\'' +
                ", caption=" + Arrays.toString(caption) +
                '}';
    }
}
