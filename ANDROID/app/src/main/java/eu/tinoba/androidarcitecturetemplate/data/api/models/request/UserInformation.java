package eu.tinoba.androidarcitecturetemplate.data.api.models.request;


public class UserInformation {

    public final String Teamname;

    public final String Password;

    public UserInformation(String teamname, String password) {
        Teamname = teamname;
        Password = password;
    }
}
