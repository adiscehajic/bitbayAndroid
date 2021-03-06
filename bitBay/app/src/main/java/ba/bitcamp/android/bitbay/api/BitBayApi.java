package ba.bitcamp.android.bitbay.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Kerim on 25.10.2015.
 */
public interface BitBayApi {

    @GET("/api/signin")
    void signIn(@Query("email") String email, @Query("password") String password,
                Callback<Response> callback);

    @GET("/api/signup")
    public void signUp(@Query("email") String email, @Query("firstName") String firstName,
                       @Query("lastName") String lastName, @Query("password") String password,
                       @Query("confirmPassword") String confirmPassword,
                       Callback<Response> callback);
}

