package ba.bitcamp.android.bitbay.model;

import java.util.UUID;

/**
 * Created by senadin.botic on 27/10/15.
 */
public class ProductModel {
    private UUID mId;
    private String mProductName;
    private String mProductPrice;
    private String mProductDescription;

    public ProductModel(String productName, String productPrice) {
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

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
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
}
