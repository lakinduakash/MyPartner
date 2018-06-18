package mypartner.ultimatex.com.mypartner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private int id;

    private TextView textViewHomeTown;
    private TextView textViewAge;
    private TextView textViewGender;
    private TextView textViewEmail;
    private TextView textViewReligion;
    private TextView textViewCast;
    private TextView textViewHeight;
    private TextView textViewOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        id = getIntent().getIntExtra(MainActivity.ID_KEY, -1);

        myToolbar.setTitle("User Details " + id);
        setSupportActionBar(myToolbar);

        textViewHomeTown = findViewById(R.id.textView_home_town);
        textViewAge = findViewById(R.id.textView_age);
        textViewGender = findViewById(R.id.textView_gender);
        textViewEmail = findViewById(R.id.textView_email);
        textViewReligion = findViewById(R.id.textView_religion);
        textViewCast = findViewById(R.id.textView_cast);
        textViewHeight = findViewById(R.id.textView_height);
        textViewOther = findViewById(R.id.textView_other);

        setDetails();


    }

    private void setDetails() {
        if (id != -1) {
            Connection.getInstance().getProfileDetails(id, new Callback<Partner[]>() {
                @Override
                public void onResponse(Call<Partner[]> call, Response<Partner[]> response) {
                    if (response.code() == 200) {
                        Partner[] partners = response.body();

                        if (partners.length > 0) {
                            Partner p = partners[0];

                            textViewHomeTown.setText(p.getCity());
                            textViewAge.setText("" + p.getAge());
                            textViewGender.setText("M".equals(p.getGender()) ? "Male" : "Female");
                            textViewEmail.setText(p.getEmail());
                            textViewReligion.setText(p.getReligion());
                            textViewCast.setText(p.getCast());
                            textViewHeight.setText(p.getHeight());
                            textViewOther.setText(p.getOther());

                        }
                    }
                }

                @Override
                public void onFailure(Call<Partner[]> call, Throwable t) {

                }
            });
        }
    }
}
