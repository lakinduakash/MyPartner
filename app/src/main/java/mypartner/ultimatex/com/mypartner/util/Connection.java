package mypartner.ultimatex.com.mypartner.util;

import mypartner.ultimatex.com.mypartner.Partner;
import mypartner.ultimatex.com.mypartner.model.LoginRequest;
import mypartner.ultimatex.com.mypartner.model.LoginResponse;
import mypartner.ultimatex.com.mypartner.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Connection {

    private static Retrofit retrofit;
    private static Connection connection;

    private final String BASE_URL = "http://192.168.1.101:8000/";

    private Connection() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Connection getInstance() {
        if (retrofit == null || connection == null)
            return connection = new Connection();
        else
            return connection;
    }

    public void login(LoginRequest request, Callback<LoginResponse> responseCallback) {
        PartnerService partnerService = retrofit.create(PartnerService.class);

        Call<LoginResponse> loginResponseCall = partnerService.login(request);
        loginResponseCall.enqueue(responseCallback);
    }

    public void signUp(Partner partner, Callback<SignUpResponse> responseCallback) {
        PartnerService partnerService = retrofit.create(PartnerService.class);

        Call<SignUpResponse> loginResponseCall = partnerService.signUp(partner);
        loginResponseCall.enqueue(responseCallback);
    }

}
