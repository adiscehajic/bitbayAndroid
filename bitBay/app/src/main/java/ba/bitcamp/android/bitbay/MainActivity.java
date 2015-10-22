package ba.bitcamp.android.bitbay;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button mLogin;
    Button mRegister;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogin = (Button) findViewById(R.id.mLogin);
        mRegister = (Button) findViewById(R.id.mRegister);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLogin:
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
                break;
            case R.id.mRegister:
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);
                break;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");

    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

    }


}
