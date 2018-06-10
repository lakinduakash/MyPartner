package mypartner.ultimatex.com.mypartner.util;

public class PartnerId {

    private int id;
    private String gender;

    public PartnerId(int id, String gender) {
        this.gender = gender;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
