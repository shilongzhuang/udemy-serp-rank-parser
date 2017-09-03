package db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @Expose
    @SerializedName("id")
    private int courseId;

    @Expose
    private int rank;

    @Expose
    private String keyword;

    @Expose
    @SerializedName("relevancy_score")
    private double score;

    @Expose
    @SerializedName("predictive_score")
    private double predictiveScore;

    @Expose
    private String url;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPredictiveScore() {
        return predictiveScore;
    }

    public void setPredictiveScore(double predictiveScore) {
        this.predictiveScore = predictiveScore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "courseId=" + courseId +
                ", rank=" + rank +
                ", keyword='" + keyword + '\'' +
                ", score=" + score +
                ", predictiveScore=" + predictiveScore +
                ", url='" + url + '\'' +
                '}';
    }
}
