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

import mypartner.ultimatex.com.mypartner.model.SignUpResponse;
import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText name;
    TextInputEditText password;
    TextInputEditText homeCity;
    TextInputEditText contact;
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
        name = findViewById(R.id.name_sign_up);
        password = findViewById(R.id.password_sign_up);
        homeCity = findViewById(R.id.homeTown_sign_up);
        contact = findViewById(R.id.contact_sign_up);
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
        String emailS = email.getText().toString().trim();
        String passwordS = password.getText().toString();
        String nameS = name.getText().toString().trim();
        String homeCityS = homeCity.getText().toString().trim();
        String contactS = contact.getText().toString().trim();
        String ageS = age.getText().toString().trim();
        String religionS = religion.getText().toString().trim();
        String castS = cast.getText().toString().trim();
        String heightS = height.getText().toString().trim();
        String otherS = other.getText().toString().trim();

        String genderS = getSelectedGender();


        if (!isValidEmail(emailS)) {
            errorDialog("Invalid Email!", "Please enter valid email").show();
            return;
        } else if (nameS.length() < 1) {
            errorDialog("Name is required!", "Please enter your name").show();
            return;
        } else if (passwordS.length() < 4) {
            errorDialog("Password is too short!", "Please enter password with minimum 4 characters").show();
            return;
        } else if (homeCityS.length() < 1) {
            errorDialog("Home town is required!", "Please enter your home town").show();
            return;
        } else if (contactS.length() < 1) {
            errorDialog("Contact is required!", "Please enter your contact number").show();
            return;
        } else if (genderS == null) {
            errorDialog("Gender is required!", "Please select your gender").show();
            return;
        }

        Partner partner = new Partner(emailS, nameS, homeCityS, Integer.parseInt(ageS), genderS, castS, religionS, otherS, contactS, heightS, passwordS);

        Connection.getInstance().signUp(partner, new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.code() == 200) {
                    if (response.body().isSuccess()) {
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra(LoginActivity.LOGGED_IN_KEY, LoginActivity.LOGGED_IN_VALUE);
                        startActivity(intent);
                        finish();
                    }
                }
                if (response.code() == 400) {
                    errorDialog("Email Already exist!", "Please choose another email to sign up.").show();
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
