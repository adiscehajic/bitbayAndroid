package ba.bitcamp.android.bitbay.model;


import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * This is model class, its used for users that we get from data base. It doesnt contain all data
 * of user from database, but only few of its atributes that we need for android.
 */
@Parcel
public class User {

    @SerializedName("id")
    private Integer id;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("confirmPassword")
    public String confirmPassword;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("image")
    public Image userImage;

    /**
     * Empty constructor
     */
    public User(){
    }

    /**
     * Constructor that accepts few atributes
     */
    public User(String firstName, String lastName, String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;

        this.lastName = lastName;
    }

    /**
     * This method returns image of this user
     * @return
     */
    public Image getUserImage() {
        return userImage;
    }

    /**
     * This method is used to set image to this user
     * @param userImage
     */
    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }

    /**
     * This method returns id of user that we've read from database
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method is used to get first name of user that we've read from database
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method is used to set value to first name of user
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method returns last name of user that we've read from database
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method sets value to users last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method returns value of users email that we've read from database
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets value to users email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns hashed password of user that we've read from database
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets value to users password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns confirmed password of user
     * @return confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * This method sets value to users confirmed password
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;
    }

    /**
     * This method returns phone number of user that we've read from database
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * this method sets value to users phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
