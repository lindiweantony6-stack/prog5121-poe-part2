import java.util.regex.Pattern;

/**
 * Part 1: registration and login.
 * NOTE: replace this file with your own submitted Part 1 Login class if it differs.
 */
public class Login {

    // Reference for the cell-number regex: add the source you researched here (Part 1 attribution).
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean upper = false, digit = false, special = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper = true;
            } else if (Character.isDigit(c)) {
                digit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                special = true;
            }
        }
        return upper && digit && special;
    }

    public boolean checkCellPhoneNumber(String cell) {
        return cell != null && CELL_PATTERN.matcher(cell).matches();
    }

    public String registerUser(String username, String password, String cell) {
        StringBuilder out = new StringBuilder();
        boolean ok = true;
        if (checkUserName(username)) {
            out.append("Username successfully captured.\n");
        } else {
            out.append("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.\n");
            ok = false;
        }
        if (checkPasswordComplexity(password)) {
            out.append("Password successfully captured.\n");
        } else {
            out.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
            ok = false;
        }
        if (checkCellPhoneNumber(cell)) {
            out.append("Cell phone number successfully added.\n");
        } else {
            out.append("Cell phone number incorrectly formatted or does not contain international code.\n");
            ok = false;
        }
        if (ok) {
            this.username = username;
            this.password = password;
            this.cellPhone = cell;
            out.append("User has been registered successfully.");
        }
        return out.toString().trim();
    }

    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
