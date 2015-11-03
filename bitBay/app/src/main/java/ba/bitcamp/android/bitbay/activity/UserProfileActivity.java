package ba.bitcamp.android.bitbay.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import ba.bitcamp.android.bitbay.LoadImage;
import ba.bitcamp.android.bitbay.R;
import ba.bitcamp.android.bitbay.model.User;

/**
 * Created by senadin.botic on 28/10/15.
 */
public class UserProfileActivity extends AppCompatActivity {

    private ImageView userImage;
    private Toolbar mToolbar;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone_number);
        userImage = (ImageView) findViewById(R.id.imageView);

        SharedPreferences sh = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sh.getString("User", "");
        User user = gson.fromJson(json, User.class);

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        if(user.getUserImage() != null) {
            new LoadImage(userImage).execute(user.getUserImage().url);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("User profile");
    }

}
