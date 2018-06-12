package mypartner.ultimatex.com.mypartner.model;


public class PartnerId {

    private int id;
    private String gender;
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
