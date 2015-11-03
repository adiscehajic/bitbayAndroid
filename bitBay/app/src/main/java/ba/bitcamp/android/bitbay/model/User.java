package ba.bitcamp.android.bitbay.model;

import android.content.Intent;

/**
 * Created by senadin.botic on 21/10/15.
 */
public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;
   // public UserType userType;

    public User(){

    }

    public User(String firstName, String lastName, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
      //  this.userType = userType;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private class UserType {
        public static final int ADMIN = 1;
        public static final int BUYER = 2;
        public static final int SELLER = 3;

        public Integer id;
        public String name;


    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;
    }
}
