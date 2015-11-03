package ba.bitcamp.android.bitbay.activity;


import android.content.Context;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.DragListView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.Product;
import ba.bitcamp.android.bitbay.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductListActivity extends AppCompatActivity {

    private ProductAdapter productAdapter;
    private DragListView mProductsList;

    private RestAdapter restAdapter;
    private BitBayApi api;

    private MenuItem mSearchAction;
    private EditText editSearch;
    private boolean isSearchOpened = false;
    private Toolbar mToolbar;

    public static List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Products");

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);

        api.getProducts(new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products, Response response2) {

                productAdapter = new ProductAdapter(products, R.layout.product_fragment, R.id.product_layout, true);
                mProductsList = (DragListView) findViewById(R.id.product_list);
                mProductsList.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                mProductsList.setAdapter(productAdapter, true);

                productList = products;
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO
            }
        });
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
                Intent profileIntent = new Intent(ProductListActivity.this, UserProfileActivity.class);
                startActivity(profileIntent);
                Toast.makeText(ProductListActivity.this, "User profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
                Toast.makeText(ProductListActivity.this, "Successful logout", Toast.LENGTH_SHORT).show();
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
            editSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    List<Product> list = new ArrayList<Product>();

                    for (int i = 0; i < productList.size(); i++) {
                        list.add(productList.get(i));
                    }
                    productAdapter.notifyDataSetChanged();
                    if (s.length() != 0) {
                        int size = list.size();
                        int i = 0;
                        while (i < size) {
                            if (!(list.get(i).getProductName().toLowerCase().contains(s.toString().toLowerCase()))) {
                                list.remove(i);
                                size--;
                            } else {
                                i++;
                            }
                        }
                        productAdapter = new ProductAdapter(list, R.layout.product_fragment, R.id.product_layout, true);
                        mProductsList = (DragListView) findViewById(R.id.product_list);
                        mProductsList.setAdapter(productAdapter, true);
                        mProductsList.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                    } else {
                        productAdapter = new ProductAdapter(productList, R.layout.product_fragment, R.id.product_layout, true);
                        mProductsList = (DragListView) findViewById(R.id.product_list);
                        mProductsList.setAdapter(productAdapter, true);
                        mProductsList.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                    }
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

    public class ProductAdapter extends DragItemAdapter<Product, ProductAdapter.ViewHolder> {

        private int mLayoutId;
        private int mGrabHandleId;

        public ProductAdapter(List<Product> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
            super(dragOnLongPress);
            mLayoutId = layoutId;
            mGrabHandleId = grabHandleId;
            setHasStableIds(true);
            setItemList(list);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            super.onBindViewHolder(holder, position);
            holder.productName.setText(mItemList.get(position).getProductName());
            holder.productPrice.setText(mItemList.get(position).getProductPrice());
            holder.itemView.setTag(mItemList.get(position).getId());
        }

        @Override
        public long getItemId(int position) {
            return mItemList.get(position).getId();
        }

        public class ViewHolder extends DragItemAdapter<Product, ViewHolder>.ViewHolder {

            public TextView productPrice;
            public TextView productName;

            public ViewHolder(final View itemView) {
                super(itemView, mGrabHandleId);
                itemView.setLongClickable(false);
                productPrice = (TextView) itemView.findViewById(R.id.product_price);
                productName = (TextView) itemView.findViewById(R.id.product_name);
            }

            @Override
            public void onItemClicked(View view) {
                Intent i = new Intent("android.intent.action.PRODUCT");
                i.putExtra("id", (int) view.getTag());
                startActivity(i);
            }
        }
    }
}
