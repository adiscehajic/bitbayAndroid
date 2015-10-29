package ba.bitcamp.android.bitbay.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ba.bitcamp.android.bitbay.ProductList;
import ba.bitcamp.android.bitbay.model.ProductModel;
import ba.bitcamp.android.bitbay.R;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);

        //productAdapter.notifyDataSetChanged();

        Toast.makeText(ProductsActivity.this, "Radi", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));
        updateUI();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent profileIntent = new Intent(ProductsActivity.this, Profile.class);
                startActivity(profileIntent);
                Toast.makeText(ProductsActivity.this, "User profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Intent intent = new Intent(ProductsActivity.this, LoginActivity.class);
                Toast.makeText(ProductsActivity.this, "Successful logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.search:
                //TODO
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI() {
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
    }


    private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProductModel mProduct;
        private TextView mProductNameView;
        private TextView mProductPriceView;

        public ProductHolder(View productView) {
            super(productView);
            productView.setOnClickListener(this);

            mProductNameView = (TextView) productView.findViewById(R.id.nameView);
            mProductPriceView = (TextView) productView.findViewById(R.id.priceView);

            //dugme
            // dugme on click

        }


        @Override
        public void onClick(View v) {
            Toast.makeText(ProductsActivity.this, "radi", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductsActivity.this, ProductViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("product_id", mProduct.getId());
            intent.putExtras(bundle);
            startActivity(intent);
        }

        public void bindProduct(ProductModel product) {
            mProduct = product;
            //slika
            mProductNameView.setText(product.getProductName());
            mProductPriceView.setText(product.getProductPrice());


        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {


        @Override
        public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ProductsActivity.this);
            View view = layoutInflater.inflate(R.layout.product_layout, parent, false);
            return new ProductHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductHolder holder, int position) {
            ProductModel product = ProductList.get().getProducts().get(position);
            holder.bindProduct(product);
        }

        @Override
        public int getItemCount() {
            return ProductList.get().getProducts().size();
        }
    }

}
