package gr.aueb.cf.imdbapp.models;

public class Genre {
    private long id;
    private String type;

    public Genre() {
    }

    public Genre(String type) {
        this.type = type;
    }

    public Genre(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

