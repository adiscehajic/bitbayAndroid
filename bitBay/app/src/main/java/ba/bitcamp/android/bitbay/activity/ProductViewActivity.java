package ba.bitcamp.android.bitbay.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.LoadImage;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.Image;
import ba.bitcamp.android.bitbay.model.Product;
import ba.bitcamp.android.bitbay.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductViewActivity extends AppCompatActivity {

    private ImageView mProductImage;
    private TextView mProductNameView;
    private TextView mProductPriceView;
    private TextView mProductDescriptionView;
    private Button mBuyButton;

    private RestAdapter restAdapter;
    private BitBayApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_profile);

        mProductNameView = (TextView) findViewById(R.id.nameView);
        mProductPriceView = (TextView) findViewById(R.id.priceView);
        mProductDescriptionView = (TextView) findViewById(R.id.descriptionView);
        mProductImage = (ImageView) findViewById(R.id.product_image);

        int id = getIntent().getExtras().getInt("id");

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);


        api.getProductById(id, new Callback<Product>() {
            @Override
            public void success(Product product, Response response2) {
                if(product.getmProductImage() != null) {
                    new LoadImage(mProductImage).execute(product.getmProductImage().url);
                }
                mProductNameView.setText(product.getProductName());
                mProductDescriptionView.setText(product.getmProductDescription());
                mProductPriceView.setText(product.getProductPrice() + " KM");
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

        mBuyButton = (Button) findViewById(R.id.buyButton);
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductViewActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

}
