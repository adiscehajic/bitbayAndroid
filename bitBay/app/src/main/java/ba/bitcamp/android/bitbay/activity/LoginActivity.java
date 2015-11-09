package ba.bitcamp.android.bitbay.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.R;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This class is used to lead login view - layout, and handle activities on it.
 */
public class LoginActivity extends Activity {

    private static final String REQUIRED_MSG = "Required";

    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mShowPassword;
    private Button mLogin;
    private TextView mForgotPasswordLink;

    private RestAdapter restAdapter;
    private BitBayApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Load all views from layout that we need
        mEmail = (EditText) findViewById(R.id.lEmail);
        mPassword = (EditText) findViewById(R.id.lPassword);
        mShowPassword = (CheckBox) findViewById(R.id.lShowPassword);
        mLogin = (Button) findViewById(R.id.lLogin);

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);

        mForgotPasswordLink = (TextView) findViewById(R.id.lForgotPasswordLink);
        //setting listener on checkbox, if its checked it will show password,
        // if its not password will be hidden
        mShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        //setting on click listener on login button
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if its clicked, pick valued that are inserted on edit text views
                String email = String.valueOf(mEmail.getText());
                String password = String.valueOf(mPassword.getText());

                //check if they are correct
                api.signIn(String.valueOf(mEmail.getText()), String.valueOf(mPassword.getText()), new Callback<User>() {
                    @Override
                    public void success(User user, Response response2) {
                        //if inputs are correct, pass that user as json
                        SharedPreferences sp = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        editor.putString("User", json);
                        editor.commit();
                        //and load new view
                        Intent i = new Intent("android.intent.action.PRODUCTS");
                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //if inputs are incorrect, show toast with error message "Incorrect email..."
                        Toast.makeText(LoginActivity.this, "Incorrect email or password, try again!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //setting on click listener on forgot password link
        mForgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if its clicked just load new view
                Intent forgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordIntent);
            }
        });
    }


    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }



}
