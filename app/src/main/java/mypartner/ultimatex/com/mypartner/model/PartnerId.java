package mypartner.ultimatex.com.mypartner.model;


public class PartnerId {

    private int id;
    private String gender;
    private String home_town;

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

    public String getHome_town() {
        return home_town;
    }

    public void setHome_town(String home_town) {
        this.home_town = home_town;
    }

}
