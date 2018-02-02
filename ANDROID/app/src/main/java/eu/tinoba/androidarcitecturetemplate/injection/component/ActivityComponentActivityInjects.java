package eu.tinoba.androidarcitecturetemplate.injection.component;

import eu.tinoba.androidarcitecturetemplate.ui.details.DetailsActivity;
import eu.tinoba.androidarcitecturetemplate.ui.home.HomeActivity;
import eu.tinoba.androidarcitecturetemplate.ui.login.LoginActivity;
import eu.tinoba.androidarcitecturetemplate.ui.register.RegisterActivity;

public interface ActivityComponentActivityInjects {

    void inject(HomeActivity homeActivity);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(DetailsActivity detailsActivity);

}
