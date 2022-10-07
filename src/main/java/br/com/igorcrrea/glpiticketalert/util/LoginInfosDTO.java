package br.com.igorcrrea.glpiticketalert.util;

public class LoginInfosDTO {

    private String url;
    private String appToken;
    private String userToken;

    public LoginInfosDTO() {

    }
    public LoginInfosDTO(String url, String appToken, String userToken) {
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
