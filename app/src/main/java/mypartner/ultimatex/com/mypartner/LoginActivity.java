package mypartner.ultimatex.com.mypartner;

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
                    if (a)
                        errorText.setText(response.body().getEmail());
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
