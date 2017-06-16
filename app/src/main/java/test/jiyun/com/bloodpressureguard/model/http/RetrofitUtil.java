package test.jiyun.com.bloodpressureguard.model.http;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import test.jiyun.com.bloodpressureguard.App;
import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;


/**
 * Created by think on 2017/5/14.
 */

public class RetrofitUtil {

    //公用的Url
    private String baseUrl = "http://api.wws.xywy.com/";
    private Retrofit retrofit;
    private static RetrofitUtil retrofitUtil = null;
    private NetWork netWork;

    private RetrofitUtil() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        netWork = retrofit.create(NetWork.class);
    }

    public static RetrofitUtil getInstance() {

        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                retrofitUtil = new RetrofitUtil();
            }
        }
        return retrofitUtil;
    }

    public void getRetrofit(String url, Map<String, String> params, final ResaultCallBack callBack, final Class tClass) {
        if (params.size() == 0 || params == null) {
            callBack.onErrorParams("参数错误");
        } else {
            Call<ResponseBody> call = netWork.getLoad(url, params);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            final String body = response.body().string();
                            App.activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Object generic = getGeneric(body, tClass);
                                    if (generic != null) {
                                        callBack.onSuccess(generic);
                                    } else {
                                        callBack.onError("解析对象为空");
                                    }

                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.notNet("请求失败");
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, final Throwable t) {
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(t.getMessage());
                        }
                    });
                }
            });
        }
    }


    public void postRetrofit(String url, Map<String, String> params, final ResaultCallBack callBack, final Class tClass) {
        if (params.size() == 0 || params == null) {
            callBack.onErrorParams("参数错误");
        } else {
            Call<ResponseBody> loadpost = netWork.getLoadpost(url, params);
            loadpost.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            final String body = response.body().string();
                            App.activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onSuccess(getGeneric(body, tClass));
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.notNet("请求失败");
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, final Throwable t) {
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(t.getMessage());
                        }
                    });
                }
            });
        }
    }

//    public void postRetrofitTwo(String value, final ResaultCallBack callBack, final Class tClass) {
//        if (value.length() == 0 || value == null) {
//            callBack.onErrorParams("参数错误");
//        } else {
//            Call<ResponseBody> post = netWork.getPost(value);
//            post.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if (response.isSuccessful()) {
//                        try {
//                            final String body = response.body().string();
//                            App.activity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    callBack.onSuccess(getGeneric(body, tClass));
//                                }
//                            });
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        App.activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                callBack.notNet("请求失败");
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, final Throwable t) {
//                    App.activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            callBack.onError(t.getMessage());
//                        }
//                    });
//                }
//            });
//        }
//    }

    private Object getGeneric(String jsonData, Class c) {

        if (jsonData.contains("\"schedule\":\"\"")) {
            jsonData = jsonData.replace("\"schedule\":\"\"",
                    "\"schedule\": {\"rdtime\":[],\"num\":{}}");
        }
        Log.i("jsonData", jsonData);
        Gson gson = new Gson();
        return gson.fromJson(jsonData, c);
    }

}
