package ba.bitcamp.android.bitbay;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ba.bitcamp.android.bitbay.model.ProductModel;

/**
 * Created by senadin.botic on 27/10/15.
 */
public class ProductList {

    private static ProductList sProducts;
    private List<ProductModel> mProducts;

    public static ProductList get() {
        if (sProducts == null) {
            sProducts = new ProductList();
        }
        return sProducts;
    }

    public ProductList() {
        mProducts = new ArrayList<>();
    }

    public void add(ProductModel product) {
        mProducts.add(product);
    }

    public List<ProductModel> getProducts() {
        return mProducts;
    }

    public ProductModel getProduct(UUID id) {
        for (ProductModel product : mProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
