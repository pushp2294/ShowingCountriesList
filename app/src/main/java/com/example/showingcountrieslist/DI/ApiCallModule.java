package com.example.showingcountrieslist.DI;

import com.example.showingcountrieslist.util.ApiServices;
import com.example.showingcountrieslist.util.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiCallModule {

    @Provides
    @Singleton
    OkHttpClient getOkhttpClient()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder  builder=new OkHttpClient.Builder();
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(400, TimeUnit.SECONDS)
                .writeTimeout(400, TimeUnit.SECONDS)
                .build();
        return  okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient okHttpClient)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    ApiServices getapiServiceClass(Retrofit retrofit)
    {
        ApiServices apiServiceClass =retrofit.create(ApiServices.class);
        return  apiServiceClass;
    }
}
