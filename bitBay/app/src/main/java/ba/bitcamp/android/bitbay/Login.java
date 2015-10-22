package ba.bitcamp.android.bitbay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
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

public class Login extends AppCompatActivity {



    EditText mLEmail;
    EditText mLPassword;
    CheckBox mLShowPassword;
    Button mLLogin;
    TextView mLForgotPasswordLink;

    User user = new User("Senadin", "Botic", "senadin.botic@bitcamp.ba", "12345678");
    User user1 = new User("Adis", "Cehajic", "adis.cehajic@bitcamp.ba", "12345678");
    User user2 = new User("Adnan", "Lapendic", "adnan.lapendic@bitcamp.ba", "12345678");
    User user3 = new User("Kerim", "Dragolj", "kerim.dragolj@bitcamp.ba", "12345678");
    User user4 = new User("Medina", "Banjic", "medina.banjic@bitcamp.ba", "12345678");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Register.users.add(user);
        Register.users.add(user1);
        Register.users.add(user2);
        Register.users.add(user3);
        Register.users.add(user4);


        mLEmail = (EditText) findViewById(R.id.lEmail);
        mLPassword = (EditText) findViewById(R.id.lPassword);
        mLShowPassword = (CheckBox) findViewById(R.id.lShowPassword);
        mLLogin = (Button) findViewById(R.id.lLogin);

        mLForgotPasswordLink = (TextView) findViewById(R.id.lForgotPasswordLink);

        mLShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mLPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mLPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });



        mLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mLEmail.getText().toString();
                String password = mLPassword.getText().toString();

                if (!Register.users.isEmpty()) {
                    for (int i = 0; i < Register.users.size(); i++) {
                        if(Register.users.get(i).getEmail().equals(email) & Register.users.get(i).getPassword().equals(password)){
                            Toast.makeText(getApplicationContext(), "Login is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Product.class));
                            break;

                        }
                        else {
                             Toast.makeText(getApplicationContext(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mLForgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(Login.this, ForgotPassword.class);
                startActivity(forgotPasswordIntent);
            }
        });
    }


}
