package ba.bitcamp.android.bitbay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ba.bitcamp.android.bitbay.R;

/**
 * This class is used to load view that is shown when we start application, and handles
 * activities on it.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button mLogin;
    Button mRegister;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up);

        //getting all views from this main layout that we need.
        mLogin = (Button) findViewById(R.id.mLogin);
        mRegister = (Button) findViewById(R.id.mRegister);

        //set two listeners on buttons
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //on this view two buttons could be clicked
        switch (v.getId()) {
            case R.id.mLogin: //if its login, then load login layout
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.mRegister: //if its register, then load register layout
                Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    /**
     * This method is called when app get visible to user.
     */
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    /**
     * This method is called when device goes to sleep, or when dialog appears
     */
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    /**
     * This method is called when app comes to foreground
     */
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    /**
     * This method is called when app becomes hidden
     */
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    /**
     * This method sets backbutton boolean to false when application started
     */
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    /**
     * This method is called when we wanna destroy application, all it proccesses
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * This mathod handles button back, if we click back it will return us to previous layout,
     * but if we press it twice on first page (start up/main page) we will leave application.
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
        //if boolean below is true (we already clicked once), leave app
            super.onBackPressed();
            return;
        }
        //if we pressed first time, just set boolean to true and show toast
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
    }


}
