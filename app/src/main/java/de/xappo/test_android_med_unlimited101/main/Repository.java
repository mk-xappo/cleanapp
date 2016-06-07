package de.xappo.test_android_med_unlimited101.main;

/**
 * Created by knoppik on 07.06.16.
 */
public class Repository {
    private String name;
    private String description;
    private String login;

    public Repository(String name, String description, String login) {
        this.name = name;
        this.description = description;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
