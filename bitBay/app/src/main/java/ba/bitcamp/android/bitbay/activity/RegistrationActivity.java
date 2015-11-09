package ba.bitcamp.android.bitbay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.R;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This class is used to load registration layout and handle activities on it.
 */
public class RegistrationActivity extends AppCompatActivity {

    private static final String REQUIRED_MSG = "Required";

    private EditText rFirstName;
    private EditText rLastName;
    private EditText rEmail;
    private EditText rPassword;
    private EditText rConfirmPassword;
    private Button rRegister;

    private RestAdapter restAdapter;
    private BitBayApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //get all views from layout that we need to handle
        rFirstName = (EditText) findViewById(R.id.rFirstName);
        rLastName = (EditText) findViewById(R.id.rLastName);
        rEmail = (EditText) findViewById(R.id.rEmail);
        rPassword = (EditText) findViewById(R.id.rPassword);
        rConfirmPassword = (EditText) findViewById(R.id.rConfirmPassword);

        rRegister = (Button) findViewById(R.id.rRegister);

        restAdapter = new RestAdapter.Builder().setEndpoint(Helper.IP_ADDRESS).build();
        api = restAdapter.create(BitBayApi.class);

        //set on click listener on registration button
        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if its clicked collect all data from EditText views
                String firstName = rFirstName.getText().toString();
                String lastName = rLastName.getText().toString();
                String email = rEmail.getText().toString();
                String password = rPassword.getText().toString();
                String confirmPassword = rConfirmPassword.getText().toString();

                //validate all of them
                if (validateEmail(email)) {
                    if (onlyLetters(firstName)) {
                        if (onlyLetters(lastName)) {
                            if (passwordMatch(password, confirmPassword)) {
                                api.signUp(String.valueOf(rEmail.getText()), String.valueOf(rFirstName.getText()),
                                        String.valueOf(rLastName.getText()), String.valueOf(rPassword.getText()),
                                        String.valueOf(rConfirmPassword.getText()), new Callback<retrofit.client.Response>() {

                                            @Override
                                            public void success(Response response, Response response2) {
                                                //if inputs are correct load new layout
                                                Intent i = new Intent("android.intent.action.LOGIN");
                                                startActivity(i);
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                //if only if inputs didnt pass validation, throw this toast
                                                Toast.makeText(RegistrationActivity.this, "Registration failed, try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    }
                }
            }
        });

    }

    /**
     * This method validates if email field is empty or not.
     * @param email
     * @return true or false
     */
    private boolean validateEmail(String email) {
        if (email.equals("")) {
            hasText(rEmail);
            return false;
        }
        return true;
    }

    /**
     * This method validates if firstname and lastname only contains letters, and if its not empty
     * or shorter than 2 chars
     * @param letter
     * @return true or false
     */
    private boolean onlyLetters(String letter) {
        if (letter.equals("") || letter.equals("")) {
            hasText(rFirstName);
            hasText(rLastName);
            return false;
        }

        if (!letter.matches("[a-zA-Z]+") && letter.length() > 2) {
            Toast.makeText(RegistrationActivity.this, "This field can only contain letters.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * This method checks if inserted password and confirmation of password matches, if they are
     * empty and if they contain atleast 8 characters.
     * @param password
     * @param cofirmPassword
     * @return true or false
     */
    private boolean passwordMatch(String password, String cofirmPassword) {
        if (password.equals("") || cofirmPassword.equals("")) {
            hasText(rPassword);
            hasText(rConfirmPassword);
            return false;
        }

        if (!password.equals(cofirmPassword)){
            Toast.makeText(RegistrationActivity.this, "Password doesn't match.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(RegistrationActivity.this, "Password must contain at least 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * This method checks if passed EditText view contains anything
     * @param editText
     * @return true or false
     */
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
