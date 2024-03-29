package mypartner.ultimatex.com.mypartner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import mypartner.ultimatex.com.mypartner.model.LoginRequest;
import mypartner.ultimatex.com.mypartner.model.LoginResponse;
import mypartner.ultimatex.com.mypartner.model.SignUpResponse;
import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static mypartner.ultimatex.com.mypartner.LoginActivity.LOGGED_IN_ID_KEY;


public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email;

    TextInputEditText password;
    TextInputEditText homeCity;
    TextInputEditText age;
    TextInputEditText religion;
    TextInputEditText cast;
    TextInputEditText height;
    TextInputEditText other;

    RadioGroup gender;

    Button signUpButton;

    public static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        email = findViewById(R.id.email_sign_up);

        password = findViewById(R.id.password_sign_up);
        homeCity = findViewById(R.id.homeTown_sign_up);
        age = findViewById(R.id.age_sign_up);
        religion = findViewById(R.id.religion_sign_up);
        cast = findViewById(R.id.cast_sign_up);
        height = findViewById(R.id.height_sign_up);
        other = findViewById(R.id.other_sign_up);
        gender = findViewById(R.id.gender_sign_up);
        signUpButton = findViewById(R.id.sign_up_button_2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void signUp() {
        final String emailS = email.getText().toString().trim();
        final String passwordS = password.getText().toString();

        String homeCityS = homeCity.getText().toString().trim();
        String ageS = age.getText().toString().trim();
        String religionS = religion.getText().toString().trim();
        String castS = cast.getText().toString().trim();
        String heightS = height.getText().toString().trim();
        String otherS = other.getText().toString().trim();

        String genderS = getSelectedGender();


        if (!isValidEmail(emailS) ) {
            errorDialog("Invalid email", "Please enter an valid email address").show();
            return;
        } else if (passwordS.length() < 4) {
            errorDialog("Password is too short!", "Please enter password with minimum 4 characters").show();
            return;
        } else if (homeCityS.length() < 1) {
            errorDialog("Home town is required!", "Please enter your home town").show();
            return;
        } else if (genderS == null) {
            errorDialog("Gender is required!", "Please select your gender").show();
            return;
        }

        Partner partner = new Partner(emailS, homeCityS, Integer.parseInt(ageS), genderS, castS, religionS, otherS, heightS, passwordS);

        Connection.getInstance().signUp(partner, new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                if (response.code() == 200) {
                    if (response.body().isSuccess()) {

                        Connection.getInstance().login(new LoginRequest(emailS, passwordS), new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.putExtra(LoginActivity.LOGGED_IN_KEY, LoginActivity.LOGGED_IN_VALUE);
                                intent.putExtra(LOGGED_IN_ID_KEY, response.body().getId());
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {

                            }
                        });

                    }
                }
                if (response.code() == 400) {
                    errorDialog("Username Already Exist!", "Please choose another email to sign up.").show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                errorDialog("Fail!", "" + t.toString()).show();
            }
        });
    }

    private AlertDialog errorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        return builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

    }

    private String getSelectedGender() {
        int id = gender.getCheckedRadioButtonId();
        if (id == R.id.radioButtonMale)
            return "M";
        else if (id == R.id.radioButtonFemale)
            return "F";
        else
            return null;
    }
}
