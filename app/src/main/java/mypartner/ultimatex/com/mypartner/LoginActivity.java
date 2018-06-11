package mypartner.ultimatex.com.mypartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mypartner.ultimatex.com.mypartner.model.LoginRequest;
import mypartner.ultimatex.com.mypartner.model.LoginResponse;
import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGGED_IN_KEY = "my_key_logged_in";
    public static final String LOGGED_IN_VALUE = "my_value_logged_in";

    Button loggingButton;
    Button signUpButton;
    TextInputEditText email;
    TextInputEditText password;
    TextView errorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loggingButton = findViewById(R.id.buttonLogin);
        signUpButton = findViewById(R.id.buttonSignup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        errorText = findViewById(R.id.errorText);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loggingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Connection con = Connection.getInstance();
        con.login(new LoginRequest(email.getText().toString(), password.getText().toString()), new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    boolean a = response.body().isLoggedIn();
                    if (a) {
                        errorText.setText(response.body().getEmail());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(LOGGED_IN_KEY, LOGGED_IN_VALUE);
                        startActivity(intent);
                        finish();
                    }
                    else
                        errorText.setText("" + response.code());
                } else {
                    errorText.setText("" + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                errorText.setText("error");
            }
        });

    }
}
