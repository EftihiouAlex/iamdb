package gr.aueb.cf.imdbapp;

import gr.aueb.cf.imdbapp.models.User;

public class Parameters {

    private static Parameters instance = null;
    public User user = null;

    public static Parameters getInstance() {
        if (instance == null) {
            instance = new Parameters();
        }

        return instance;
    }

}
