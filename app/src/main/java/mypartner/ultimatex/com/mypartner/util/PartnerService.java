package mypartner.ultimatex.com.mypartner.util;

import java.util.List;

import mypartner.ultimatex.com.mypartner.Partner;
import mypartner.ultimatex.com.mypartner.model.LoginRequest;
import mypartner.ultimatex.com.mypartner.model.LoginResponse;
import mypartner.ultimatex.com.mypartner.model.PartnerId;
import mypartner.ultimatex.com.mypartner.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PartnerService {

    @GET("hello/")
    Call<List<Partner>> list(String user);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/signup")
    Call<SignUpResponse> signUp(@Body Partner partner);

    @GET("api/userlist")
    Call<PartnerId[]> getIdList();

}
