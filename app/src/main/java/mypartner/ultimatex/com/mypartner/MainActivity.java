package mypartner.ultimatex.com.mypartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mypartner.ultimatex.com.mypartner.tinydb.TinyDB;

public class MainActivity extends AppCompatActivity {

    static String LOGGED_IN_KEY = "loggedIn";
    TinyDB tinyDB;
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinyDB = new TinyDB(this);

        Intent intent = getIntent();
        String l = intent.getStringExtra(LoginActivity.LOGGED_IN_KEY);

        if (LoginActivity.LOGGED_IN_VALUE.equals(l)) {
            tinyDB.putBoolean(LOGGED_IN_KEY, true);
        }

        checkLogin();

    }

    private void checkLogin() {
        loggedIn = tinyDB.getBoolean(LOGGED_IN_KEY);

        if (!loggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void logOut() {
        tinyDB.putBoolean(LOGGED_IN_KEY, false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
