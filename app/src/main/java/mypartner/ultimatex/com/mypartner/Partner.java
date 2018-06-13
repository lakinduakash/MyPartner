package mypartner.ultimatex.com.mypartner;

public class Partner {

    public static final String MALE = "M";
    public static final String FEMALE = "F";

    private int id;

    private String username;
    private String name;
    private String city;
    private int age;
    private String gender;
    private String cast;
    private String religion;
    private String other;
    private String contact;
    private String height;
    private String password;

    public Partner(String username, String name, String homeCity, int age, String gender, String cast, String religion, String other, String contact, String height, String password) {
        this.username = username;
        this.name = name;
        this.city = homeCity;
        this.age = age;
        this.gender = gender;
        this.cast = cast;
        this.religion = religion;
        this.other = other;
        this.contact = contact;
        this.height = height;
        this.password = password;
    }

    public Partner(String username) {
        this.username = username;
    }


    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
