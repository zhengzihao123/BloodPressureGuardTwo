package test.jiyun.com.bloodpressureguard.model.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by think on 2017/5/14.
 */

public interface NetWork {
    @GET
    Call<ResponseBody> getLoad(@Url String url, @QueryMap Map<String, String> params);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> getLoadpost(@Url String url, @FieldMap Map<String, String> params);

//    @FormUrlEncoded
//    @POST("appserverapi.ashx")
//    Call<ResponseBody> getPost(@Field("param") String value);

}