package ba.bitcamp.android.bitbay.activity;

import android.app.Activity;
import android.content.Intent;
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

import ba.bitcamp.android.bitbay.R;
import ba.bitcamp.android.bitbay.api.BitBayApi;
import ba.bitcamp.android.bitbay.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends Activity {

    private static final String REQUIRED_MSG = "Required";

    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mShowPassword;
    private Button mLogin;
    private TextView mForgotPasswordLink;

    private RestAdapter restAdapter;
    private BitBayApi api;

    User user = new User("Senadin", "Botic", "senadin.botic@bitcamp.ba", "12345678");
    User user1 = new User("Adis", "Cehajic", "adis.cehajic@bitcamp.ba", "12345678");
    User user2 = new User("Adnan", "Lapendic", "adnan.lapendic@bitcamp.ba", "12345678");
    User user3 = new User("Kerim", "Dragolj", "kerim.dragolj@bitcamp.ba", "12345678");
    User user4 = new User("Medina", "Banjic", "medina.banjic@bitcamp.ba", "12345678");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//
//        RegisterActivity.users.add(user);
//        RegisterActivity.users.add(user1);
//        RegisterActivity.users.add(user2);
//        RegisterActivity.users.add(user3);
//        RegisterActivity.users.add(user4);

        mEmail = (EditText) findViewById(R.id.lEmail);
        mPassword = (EditText) findViewById(R.id.lPassword);
        mShowPassword = (CheckBox) findViewById(R.id.lShowPassword);
        mLogin = (Button) findViewById(R.id.lLogin);

        restAdapter = new RestAdapter.Builder().setEndpoint("http://10.0.82.92:9000").build();
        api = restAdapter.create(BitBayApi.class);

        mForgotPasswordLink = (TextView) findViewById(R.id.lForgotPasswordLink);
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

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(mEmail.getText());
                String password = String.valueOf(mPassword.getText());

                api.signIn(String.valueOf(mEmail.getText()), String.valueOf(mPassword.getText()), new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        int smthing = 0;
                        Intent i = new Intent("android.intent.action.PRODUCTS");
                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        int smthing = 0;
                        Toast.makeText(LoginActivity.this, "Incorrect email or password, try again!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        mForgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
