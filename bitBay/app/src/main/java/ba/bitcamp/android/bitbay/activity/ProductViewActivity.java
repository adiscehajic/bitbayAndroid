package ba.bitcamp.android.bitbay.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.LoadImage;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.Product;
import ba.bitcamp.android.bitbay.R;
import ba.bitcamp.android.bitbay.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This class is used to load product "profile" layout and handle activities on it.
 */
public class ProductViewActivity extends AppCompatActivity {

    private ImageView mProductImage;
    private TextView mProductNameView;
    private TextView mProductPriceView;
    private TextView mProductDescriptionView;
    private Button mBuyButton;

    private int productId;
    private User user;
    private RestAdapter restAdapter;
    private BitBayApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_profile);

        //Reciving user that we saved when we logged in
        SharedPreferences sh = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("User", "");
        user = gson.fromJson(json, User.class);

        //Get all views from layout that we need
        mProductNameView = (TextView) findViewById(R.id.nameView);
        mProductPriceView = (TextView) findViewById(R.id.priceView);
        mProductDescriptionView = (TextView) findViewById(R.id.descriptionView);
        mProductImage = (ImageView) findViewById(R.id.product_image);

        productId = getIntent().getExtras().getInt("id");

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);

        //try to open product that we clicked on
        api.getProductById(productId, new Callback<Product>() {
            @Override
            public void success(Product product, Response response2) {
                //if we succeeded try to load its image if it has it.
                if(product.getmProductImage() != null) {
                    new LoadImage(mProductImage).execute(product.getmProductImage().url);
                }
                //set values of this products to TextViews that we collected from this layout
                mProductNameView.setText(product.getProductName());
                mProductDescriptionView.setText(product.getmProductDescription());
                mProductPriceView.setText(product.getProductPrice() + " KM");
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

        //get button "buy" from this layout
        mBuyButton = (Button) findViewById(R.id.buyButton);
        //set listener on it
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if this button is clicked open new view
                Intent intent = new Intent(ProductViewActivity.this, PaypalActivity.class);
                //building url that we gona pass to new activity
                String url = Helper.IP_ADDRESS + "/api/purchaseprocessing/" + productId + "?userId=" + user.getId() + "&ipAddress=" + Helper.IP_ADDRESS;
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

}
