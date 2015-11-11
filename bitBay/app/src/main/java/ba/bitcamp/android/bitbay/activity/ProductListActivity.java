package ba.bitcamp.android.bitbay.activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.List;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.LoadImage;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.Product;
import ba.bitcamp.android.bitbay.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This class is used to load layout where we show all our products that we read from database.
 * This layout contains drag list view where we load each product on new layout (product fragment).
 */
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

        //after we loaded layout, get toolar view and set title on it
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Products");

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);

        //Here we load all products on our view
        api.getProducts(new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products, Response response2) {
                //set each of those products on fragment on our layout
                productAdapter = new ProductAdapter(products, R.layout.product_fragment, R.id.product_layout, true);
                //find layout and set grid layout on it, so we have 1 item per column.
                mProductsList = (DragListView) findViewById(R.id.product_list);
                mProductsList.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                mProductsList.setAdapter(productAdapter, true);

                //save those products so we can use them later
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

    /**
     * This method handles if we clicked on any menu item.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:  //if we clicked on "profile" load layot that shows users profile
                Intent profileIntent = new Intent(ProductListActivity.this, UserProfileActivity.class);
                startActivity(profileIntent);
                return true;
            case R.id.logout: //if we clicked "logout" load login view with toast u "successful logout"
                Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
                Toast.makeText(ProductListActivity.this, "Successful logout", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return true;
            case R.id.search: //if we clicked on search icon, call method that handles search
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method handles search on our menu.
     */
    private void handleMenuSearch() {
        ActionBar action = getSupportActionBar();

        //if we clicked on search icon, check if its already been clicked. If it was focus out
        // and disable inputs, if it wasn't set focus on it, and enable inputs
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
                //do before inputs in search (text change)
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                //do while text is being changed
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }
                //do after inputs (text changed)
                @Override
                public void afterTextChanged(Editable s) {
                    List<Product> list = new ArrayList<Product>();

                    for (int i = 0; i < productList.size(); i++) {
                        list.add(productList.get(i));
                    }
                    productAdapter.notifyDataSetChanged();
                    //with every character inserted into search check if current string
                    //is not null and if its contained
                    if (s.length() != 0) {
                        int size = list.size();
                        int i = 0;
                        while (i < size) {
                            //go trough all products and remove those who doesnt contain inserted string
                            if (!(list.get(i).getProductName().toLowerCase().contains(s.toString().toLowerCase()))) {
                                list.remove(i);
                                size--;
                            } else {
                                i++;
                            }
                        }
                        //reload that view with all products that contains inserted string in name
                        productAdapter = new ProductAdapter(list, R.layout.product_fragment, R.id.product_layout, true);
                        mProductsList = (DragListView) findViewById(R.id.product_list);
                        mProductsList.setAdapter(productAdapter, true);
                        mProductsList.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                    } else {
                        //if inserted string equals to null just load all products
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

    /**
     * This method handles if we were focused on search and then pressed back
     */
    @Override
    public void onBackPressed() {
        if (isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    /**
     * This class controls how many items will be loaded on screen.
     */
    public class ProductAdapter extends DragItemAdapter<Product, ProductAdapter.ViewHolder> {

        private int mLayoutId;
        private int mGrabHandleId;

        //constructor
        public ProductAdapter(List<Product> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
            super(dragOnLongPress);
            mLayoutId = layoutId;
            mGrabHandleId = grabHandleId;
            setHasStableIds(true);
            setItemList(list);

        }

        /**
         * This method using inflate creates view and adds it to parent.
         * @param parent
         * @param viewType
         * @return view
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method creates each fragment on parent view and inserts data on it.
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            super.onBindViewHolder(holder, position);
            //set values on views that we collected above for each item from list
            holder.productName.setText(mItemList.get(position).getProductName());
            holder.productPrice.setText(mItemList.get(position).getProductPrice() + " KM");
            //if product has any images, set it
            if(mItemList.get(position).getmProductImage() !=  null) {
                new LoadImage(holder.productImage).execute(mItemList.get(position).getmProductImage().url);
            }
            holder.itemView.setTag(mItemList.get(position).getId());
        }

        @Override
        public long getItemId(int position) {
            return mItemList.get(position).getId();
        }

        /**
         * This class is used to set content on each holder. Which contains product price, name
         * and image.
         */
        public class ViewHolder extends DragItemAdapter<Product, ViewHolder>.ViewHolder {

            public TextView productPrice;
            public TextView productName;
            public ImageView productImage;

            public ViewHolder(final View itemView) {
                super(itemView, mGrabHandleId);
                itemView.setLongClickable(false);

                //getting views by its ids, on this views we gonna set data about each product
                productPrice = (TextView) itemView.findViewById(R.id.product_price);
                productName = (TextView) itemView.findViewById(R.id.product_name);
                productImage = (ImageView) itemView.findViewById(R.id.fragment_image);
            }

            /**
             * This method handles click on each item.
             * @param view
             */
            @Override
            public void onItemClicked(View view) {
                //it opens new layout with data of item that we clicked on
                Intent i = new Intent("android.intent.action.PRODUCT");
                //and passing id of product that we clicked on to new layout
                i.putExtra("id", (int) view.getTag());
                startActivity(i);
            }
        }
    }
}
