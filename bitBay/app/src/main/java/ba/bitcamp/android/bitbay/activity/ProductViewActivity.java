package ba.bitcamp.android.bitbay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import ba.bitcamp.android.bitbay.ProductList;
import ba.bitcamp.android.bitbay.model.ProductModel;
import ba.bitcamp.android.bitbay.R;

public class ProductViewActivity extends AppCompatActivity {

    private ProductModel mProduct;
    private TextView mProductNameView;
    private TextView mProductPriceView;
    private TextView mProductDescriptionView;
    private Button mBuyButton;
    private UUID mProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        mProductId = (UUID) getIntent().getSerializableExtra("product_id");
        mProduct = ProductList.get().getProduct(mProductId);

        mProductNameView = (TextView) findViewById(R.id.nameView);
        mProductPriceView = (TextView) findViewById(R.id.priceView);
        mProductDescriptionView = (TextView) findViewById(R.id.descriptionView);

//        mProductNameView.setText(mProduct.getProductName());
//        mProductPriceView.setText(mProduct.getProductPrice());
//        mProductDescriptionView.setText(mProduct.getmProductDescription());

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
