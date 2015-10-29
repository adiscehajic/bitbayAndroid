package ba.bitcamp.android.bitbay.activity;


import android.content.Context;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import ba.bitcamp.android.bitbay.ProductList;
import ba.bitcamp.android.bitbay.model.ProductModel;
import ba.bitcamp.android.bitbay.R;

public class ProductsActivity extends AppCompatActivity {

    private MenuItem mSearchAction;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private EditText editSearch;
    private boolean isSearchOpened = false;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Products");

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.search);
        return super.onPrepareOptionsMenu(menu);
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
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleMenuSearch() {
        ActionBar action = getSupportActionBar();

        if (isSearchOpened) {
            action.setDisplayShowCustomEnabled(false);
            action.setDisplayShowTitleEnabled(true);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);

            mSearchAction.setIcon(getResources().getDrawable(R.drawable.search));

            isSearchOpened = false;
        } else {
            action.setDisplayShowCustomEnabled(true);
            action.setCustomView(R.layout.search_bar);
            action.setDisplayShowTitleEnabled(false);



            editSearch = (EditText)action.getCustomView().findViewById(R.id.editSearch);
            editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });

            editSearch.requestFocus();

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);

            mSearchAction.setIcon(getResources().getDrawable(R.drawable.search));

            isSearchOpened = true;

        }
    }

    @Override
    public void onBackPressed() {
        if (isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    private void doSearch(){
        //TODO
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
