package com.jdan.newsonline.widget.retrofit

import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.constants.UrlFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppClient{

    private var mRetrofit : Retrofit? =null

     fun retrofit(): Retrofit? {
         if (mRetrofit == null){
             var builder = OkHttpClient.Builder()

             if (Config.DEBUG){
                 var loggingInterceptor = HttpLoggingInterceptor()
                 loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                 builder.addInterceptor(loggingInterceptor)
             }
             val okHttpClient = builder.build()
             mRetrofit = Retrofit.Builder()
                     .baseUrl(UrlFactory.BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                     .client(okHttpClient)
                     .build()
         }
         return mRetrofit
     }
}