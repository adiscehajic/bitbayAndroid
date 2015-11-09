package ba.bitcamp.android.bitbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * This model represents model of image from database, but it doesnt contain all its atributes.
 */
public class Image implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("image_url")
    public String url;

    /**
     * Empty constructor
     */
    public Image() {
    }


    /**
     * This method returns id of image that we got from database
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets value to id of image
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns url of image that we got from database
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method sets value to url of image
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}

