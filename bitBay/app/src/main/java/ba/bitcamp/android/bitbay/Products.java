package ba.bitcamp.android.bitbay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Products extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);
        //productAdapter.notifyDataSetChanged();

        Toast.makeText(Products.this, "Radi", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Products.this));
        updateUI();


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

            mProductNameView = (TextView) productView.findViewById(R.id.title);
            mProductPriceView = (TextView) productView.findViewById(R.id.price);
            //dugme
            // dugme on click

        }


        @Override
        public void onClick(View v) {
            Toast.makeText(Products.this, "radi", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Products.this, ProductView.class);
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
            LayoutInflater layoutInflater = LayoutInflater.from(Products.this);
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
