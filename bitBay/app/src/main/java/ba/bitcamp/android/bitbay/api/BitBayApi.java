package ba.bitcamp.android.bitbay.api;

import java.util.List;

import ba.bitcamp.android.bitbay.model.Product;
import ba.bitcamp.android.bitbay.model.User;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * This interface is used to connect with rest service. It contains anotations - @GET or @POST, or
 * any other http request and it contains route where we connect to collect data. In methods
 * we use @Query to get or send data using form ids, or @Path for dynamic routes.
 */
public interface BitBayApi {

    @GET("/api/signin")
    void signIn(@Query("email") String email, @Query("password") String password,
                Callback<User> callback);

    @GET("/api/signup")
    public void signUp(@Query("email") String email, @Query("firstName") String firstName,
                       @Query("lastName") String lastName, @Query("password") String password,
                       @Query("confirmPassword") String confirmPassword,
                       Callback<Response> callback);

    @GET("/api/products")
    public void getProducts(Callback<List<Product>> callback);

    @GET("/api/product/{id}")
    public void getProductById(@Path("id") int id, Callback<Product> callback);

    @GET("/api/purchaseprocessing/{productId}")
    public void paypal(@Path("productId") Integer productId, @Query("userId") Integer id, Callback<Response> callback);

}

