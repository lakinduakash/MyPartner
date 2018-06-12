package mypartner.ultimatex.com.mypartner.model;

public class LoginResponse {

    private String email;
    private boolean loggedIn;

    private int id;

    public LoginResponse(String email, boolean loggedIn) {
        this.email = email;
        this.loggedIn = loggedIn;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
