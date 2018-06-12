package mypartner.ultimatex.com.mypartner.model;

public class SignUpResponse {

    private String email;
    private boolean loggedIn;
    private boolean success;
    private int id;

    public SignUpResponse(String email, boolean loggedIn, boolean success) {
        this.email = email;
        this.loggedIn = loggedIn;
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
