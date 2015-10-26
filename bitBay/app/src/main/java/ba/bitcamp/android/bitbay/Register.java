package ba.bitcamp.android.bitbay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText rFirstName;
    EditText rLastName;
    EditText rEmail;
    EditText rPassword;
    EditText rConfirmPassword;
    Button rRegister;
    RadioButton rBuyer;
    RadioButton rSeller;

    public static List<User> users = new LinkedList<User>();

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers() {
        Register.users = users;
    }

    public static void addUser(User user) {Register.users.add(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFirstName = (EditText) findViewById(R.id.rFirstName);
        rLastName = (EditText) findViewById(R.id.rLastName);
        rEmail = (EditText) findViewById(R.id.rEmail);
        rPassword = (EditText) findViewById(R.id.rPassword);
        rConfirmPassword = (EditText) findViewById(R.id.rConfirmPassword);
        rRegister = (Button) findViewById(R.id.rRegister);
        rSeller = (RadioButton) findViewById(R.id.rSeller);
        rBuyer = (RadioButton) findViewById(R.id.rBuyer);

        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = rFirstName.getText().toString();
                String lastName = rLastName.getText().toString();
                String email = rEmail.getText().toString();
                String password = rPassword.getText().toString();
                String confirmPassword = rConfirmPassword.getText().toString();



                if (validateEmail(email) & passwordMatch(password, confirmPassword) & onlyLetters(lastName) &
                        onlyLetters(firstName)) {

                    User user = new User(firstName, lastName, email, password, confirmPassword);
                    users.add(user);
                    Toast.makeText(getApplicationContext(), "Successfull create account.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            }
        });

    }


    private boolean validateEmail(String email) {
        if (email.equals("")) {
            Toast.makeText(Register.this, "This field is required.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean onlyLetters(String letter) {
        if (letter.equals("")) {
            Toast.makeText(Register.this, "This field is required.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!letter.matches("[a-zA-Z]+") && letter.length() > 2) {
            Toast.makeText(Register.this, "This field can only contain letters.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean passwordMatch(String password, String cofirmPassword) {
        int letter = 0;
        int number = 0;
        if (!password.equals(cofirmPassword)){
            Toast.makeText(Register.this, "Password doesn't match.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText(Register.this, "Password must contain at least 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (letter == 0) {
//            Toast.makeText(Register.this, "Password must contain at least one letter.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (number == 0) {
//            Toast.makeText(Register.this, "Password must contain at least one number.", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

}
