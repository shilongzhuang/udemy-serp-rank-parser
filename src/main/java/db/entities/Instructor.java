package db.entities;

import com.google.gson.annotations.SerializedName;

public class Instructor {

    @SerializedName("id")
    private String id;

    private String name;

    private String jobTitle;

    private String url;

    private String fullName;

    private int students;

    private int courses;

    private int reviews;


}
