package ba.bitcamp.android.bitbay.activity;

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
    private Button mBuyButton;
    private UUID mProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_layout);

        mProductId = (UUID) getIntent().getSerializableExtra("product_id");
        Log.d("65", mProductId.toString());
        mProduct = ProductList.get().getProduct(mProductId);

        mProductNameView = (TextView) findViewById(R.id.productNameView);
        mProductPriceView = (TextView) findViewById(R.id.productPriceView);

        mProductNameView.setText(mProduct.getProductName());
        mProductPriceView.setText(mProduct.getProductPrice());

        mBuyButton = (Button) findViewById(R.id.buyButton);
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
