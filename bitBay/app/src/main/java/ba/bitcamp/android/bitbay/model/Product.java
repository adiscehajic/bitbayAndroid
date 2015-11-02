package ba.bitcamp.android.bitbay.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

/**
 * Created by senadin.botic on 27/10/15.
 */
public class Product {
    public UUID mId;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String mProductName;
    @SerializedName("price")
    public String mProductPrice;
    @SerializedName("description")
    public String mProductDescription;
    @SerializedName("images")
    public List<Image> mProductImages;

    public Product() {
    }

    public Product(String productName, String productPrice) {
        mId = UUID.randomUUID();
        mProductName = productName;
        mProductPrice = productPrice;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public void setmProductDescription(String mProductDescription) {
        this.mProductDescription = mProductDescription;
    }

    public UUID getUUID() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getProductPrice() {
        return mProductPrice;
    }

    public void setProductPrice(String mProductPrice) {
        this.mProductPrice = mProductPrice;
    }

    public List<Image> getmProductImages() {
        return mProductImages;
    }

    public void setmProductImages(List<Image> mProductImages) {
        this.mProductImages = mProductImages;
    }
}