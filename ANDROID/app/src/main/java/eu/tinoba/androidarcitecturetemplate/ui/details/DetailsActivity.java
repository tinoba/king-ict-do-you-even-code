package eu.tinoba.androidarcitecturetemplate.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.tinoba.androidarcitecturetemplate.R;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;
import timber.log.Timber;

public class DetailsActivity extends BaseActivity implements DetailsView {


    @Inject
    DetailsPresenter presenter;
    private String id;

    @BindView(R.id.details_spin_kit)
    SpinKitView detailsSpinKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Details");
        id = getIntent().getStringExtra("teamId");

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getData(id);
    }

    @Override
    public void showDetailsData(LoginResponse loginResponse) throws JSONException {
        String teamName = new JSONObject(loginResponse.Result).getString("TeamName");
        Timber.e(teamName);
        JSONArray lista = new JSONArray(new JSONObject(loginResponse.Result).getString("Members"));
        for (int i = 0; i < 4; i++) {

            try {
                JSONObject object = lista.getJSONObject(i);
                String name = object.getString("Name");
                String surname = object.getString("Surname");
                String email = object.getString("Mail");
            } catch (Exception e) {
                Timber.e(e);
            }


        }

        detailsSpinKit.setVisibility(View.GONE);

    }

    @Override
    public void failed() {
        Toast.makeText(this, "Details data failed", Toast.LENGTH_SHORT).show();
        detailsSpinKit.setVisibility(View.GONE);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
