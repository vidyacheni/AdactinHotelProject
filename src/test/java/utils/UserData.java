package utils;

public class UserData {

    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;

    // Set all values
    public static void setUserData(String fName, String lName, String userEmail, String userPassword) {
        firstName = fName;
        lastName = lName;
        email = userEmail;
        password = userPassword;
    }

    // Getters
    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    // Clear user data
    public static void clearUserData() {
        firstName = null;
        lastName = null;
        email = null;
        password = null;
    }
}
