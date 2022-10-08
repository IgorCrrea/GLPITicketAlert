package br.com.igorcrrea.glpiticketalert.model;

public class Configurations {

    private String url;
    private String appToken;
    private String userToken;

    public Configurations() {

    }
    public Configurations(String url, String appToken, String userToken) {
        this.url = url;
        this.appToken = appToken;
        this.userToken = userToken;
    }

    public String getUrl() {
        return url;
    }

    public String getAppToken() {
        return appToken;
    }

    public String getUserToken() {
        return userToken;
    }

}
