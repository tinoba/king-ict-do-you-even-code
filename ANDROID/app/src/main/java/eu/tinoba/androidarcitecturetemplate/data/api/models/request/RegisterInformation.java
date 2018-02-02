package eu.tinoba.androidarcitecturetemplate.data.api.models.request;

import java.util.List;

public class RegisterInformation {

    public final String Teamname;

    public final String Password;

    public final List<Members> members;

    public RegisterInformation(final String teamname, final String password, final List<Members> members) {
        Teamname = teamname;
        Password = password;
        this.members = members;
    }

    public static class Members {

        public final String name;
        public final String surname;
        public final String mail;

        public Members(final String name, final String surname, final String mail) {
            this.name = name;
            this.surname = surname;
            this.mail = mail;
        }
    }
}
