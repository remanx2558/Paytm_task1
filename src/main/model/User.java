package src.main.model;

public class User {
    private int id;
    private int age;
    private char gender;
    private String occupation;
    private String zipCode;

    public User(int id, int age, char gender, String occupation, String zipCode) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getZipCode() {
        return zipCode;
    }
}

