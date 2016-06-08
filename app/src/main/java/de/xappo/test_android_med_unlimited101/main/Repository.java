package de.xappo.test_android_med_unlimited101.main;

/**
 * Created by knoppik on 07.06.16.
 */
public class Repository {
    private String name;
    private String description;
    private String login;
    private String html_url;
    private String owner_html_url;
    private boolean fork;

    public Repository(String name, String description, String login, String html_url, String owner_html_url, boolean fork) {
        this.name = name;
        this.description = description;
        this.login = login;
        this.html_url = html_url;
        this.owner_html_url = owner_html_url;
        this.fork = fork;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getOwner_html_url() {
        return owner_html_url;
    }

    public void setOwner_html_url(String owner_html_url) {
        this.owner_html_url = owner_html_url;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", login='" + login + '\'' +
                ", html_url='" + html_url + '\'' +
                ", owner_html_url='" + owner_html_url + '\'' +
                ", fork=" + fork +
                '}';
    }
}
