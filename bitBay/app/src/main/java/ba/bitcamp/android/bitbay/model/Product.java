package ba.bitcamp.android.bitbay.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;
import java.util.UUID;

/**
 * This class represents model of products from database, it doesnt contains all atributes that we
 * get from database for each product.
 */
@Parcel
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

    /**
     * Empty constructor
     */
    public Product() {
    }

    /**
     * constructor that accepts
     */
    public Product(String productName, String productPrice) {
        mId = UUID.randomUUID();
        mProductName = productName;
        mProductPrice = productPrice;
    }

    /**
     * This method get only first picture in array of product pictures that we get from database.
     * But only if product has pictures
     * @return
     */
    public Image getmProductImage() {
        if(mProductImages.size() > 0) {
            return mProductImages.get(0);
        }
        return null;
    }

    /**
     * This method sets array of images as array of images of this product
     * @param mProductImages
     */
    public void setmProductImages(List<Image> mProductImages) {
        this.mProductImages = mProductImages;
    }

    /**
     * This method returns description of pruduct that we've previously read from database
     * @return product description
     */
    public String getmProductDescription() {
        return mProductDescription;
    }

    /**
     * This method sets description to product
     * @param mProductDescription
     */
    public void setmProductDescription(String mProductDescription) {
        this.mProductDescription = mProductDescription;
    }

    /**
     * This method returns UUID of product
     * @return UUID
     */
    public UUID getUUID() {
        return mId;
    }

    /**
     * This method is used to set UUID value
     * @param mId
     */
    public void setId(UUID mId) {
        this.mId = mId;
    }

    /**
     * This method returns id of pruduct, that we've read from database
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets value to id of product
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns name of product that we've read from database
     * @return product name
     */
    public String getProductName() {
        return mProductName;
    }

    /**
     * This method sets value to name of product
     * @param mProductName
     */
    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    /**
     * This method returns price of product that we've read from database
     * @return
     */
    public String getProductPrice() {
        return mProductPrice;
    }

    /**
     * This is method used to set value to products price
     * @param mProductPrice
     */
    public void setProductPrice(String mProductPrice) {
        this.mProductPrice = mProductPrice;
    }

}