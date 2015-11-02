package ba.bitcamp.android.bitbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kerim on 30.10.2015.
 */
public class Image implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("image_url")
    public String url;

    public Image() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

