package mypartner.ultimatex.com.mypartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

import mypartner.ultimatex.com.mypartner.model.PartnerId;
import mypartner.ultimatex.com.mypartner.tinydb.TinyDB;
import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static String LOGGED_IN_KEY = "loggedIn";
    TinyDB tinyDB;
    boolean loggedIn;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        rv = findViewById(R.id.recyclerView);

        tinyDB = new TinyDB(this);

        Intent intent = getIntent();
        String l = intent.getStringExtra(LoginActivity.LOGGED_IN_KEY);

        if (LoginActivity.LOGGED_IN_VALUE.equals(l)) {
            tinyDB.putBoolean(LOGGED_IN_KEY, true);
        }

        checkLogin();
        setListView();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_log_out)
            logOut();
        return super.onOptionsItemSelected(item);
    }

    public void setListView() {
        Connection.getInstance().getIdList(new Callback<PartnerId[]>() {
            @Override
            public void onResponse(Call<PartnerId[]> call, Response<PartnerId[]> response) {
                if (response.code() == 200) {
                    PartnerId[] partnerIds = response.body();
                    if (partnerIds != null) {
                        ArrayList<PartnerId> list = new ArrayList<>(Arrays.asList(partnerIds));
                        LinearLayoutManager rvl = new LinearLayoutManager(MainActivity.this);
                        rv.setLayoutManager(rvl);
                        rv.setAdapter(new PartnerIdListAdapter(list));
                    }
                }
            }

            @Override
            public void onFailure(Call<PartnerId[]> call, Throwable t) {
                ArrayList<PartnerId> list = new ArrayList<>();
                list.add(new PartnerId(1, "M"));
                list.add(new PartnerId(2, "M"));
                LinearLayoutManager rvl = new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(rvl);
                rv.setAdapter(new PartnerIdListAdapter(list));
            }
        });
    }
}
