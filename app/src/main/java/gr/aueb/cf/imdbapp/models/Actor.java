package gr.aueb.cf.imdbapp.models;

public class Actor {
    private long id;
    private String firstname;
    private String lastname;
    private String biography;

    public Actor() {}

    public Actor(long id, String firstname, String lastname, String biography) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.biography = biography;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
