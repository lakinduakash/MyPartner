package mypartner.ultimatex.com.mypartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import mypartner.ultimatex.com.mypartner.model.PartnerId;
import mypartner.ultimatex.com.mypartner.tinydb.TinyDB;
import mypartner.ultimatex.com.mypartner.util.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final static String LOGGED_IN_TINY_DB_KEY = "loggedIn";
    public final static String ID_KEY = "selected_id";

    TinyDB tinyDB;
    boolean loggedIn;

    private int myId;

    private ArrayList<PartnerId> responseList = new ArrayList<>();

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        rv = findViewById(R.id.recyclerView);

        tinyDB = new TinyDB(this);

        final Intent intent = getIntent();
        String l = intent.getStringExtra(LoginActivity.LOGGED_IN_KEY);

        myId = intent.getIntExtra(LoginActivity.LOGGED_IN_ID_KEY, 0);

        Log.d("logged in id", "" + myId);

        if (LoginActivity.LOGGED_IN_VALUE.equals(l)) {
            tinyDB.putBoolean(LOGGED_IN_TINY_DB_KEY, true);
        }

        final Intent profileIntent = new Intent(this, ProfileActivity.class);

        checkLogin();
        setListView();

        rv.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                profileIntent.putExtra(ID_KEY, responseList.get(position).getId());
                startActivity(profileIntent);
            }
        }));

    }

    private void checkLogin() {
        loggedIn = tinyDB.getBoolean(LOGGED_IN_TINY_DB_KEY);

        if (!loggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void logOut() {
        tinyDB.putBoolean(LOGGED_IN_TINY_DB_KEY, false);
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
        else if (item.getItemId() == R.id.action_my_profile) {
            startActivity(new Intent(this, ProfileActivity.class).putExtra(ID_KEY, myId));
        }
        return super.onOptionsItemSelected(item);
    }

    public void setListView() {
        Connection.getInstance().getIdList(new Callback<PartnerId[]>() {
            @Override
            public void onResponse(Call<PartnerId[]> call, Response<PartnerId[]> response) {
                if (response.code() == 200) {
                    PartnerId[] partnerIds = response.body();
                    if (partnerIds != null) {
                        responseList.addAll(Arrays.asList(partnerIds));
                        LinearLayoutManager rvl = new LinearLayoutManager(MainActivity.this);
                        rv.setLayoutManager(rvl);
                        rv.setAdapter(new PartnerIdListAdapter(responseList));
                    }
                }
            }

            @Override
            public void onFailure(Call<PartnerId[]> call, Throwable t) {
                ArrayList<PartnerId> list = new ArrayList<>();
                list.add(new PartnerId(0, "No connection"));
                list.add(new PartnerId(0, "Connect to the sever"));
                LinearLayoutManager rvl = new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(rvl);
                rv.setAdapter(new PartnerIdListAdapter(list));
            }
        });
    }
}
