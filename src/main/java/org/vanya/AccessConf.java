package org.vanya;

/**
 * Created by vanya on 13.03.15.
 */
public class AccessConf {
    private static AccessConf ourInstance = new AccessConf();
    private String accesToken;
    private String userId;

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static AccessConf getInstance() {
        return ourInstance;
    }

    private AccessConf() {

    }
}
