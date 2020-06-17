/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yao.mvpdemo.network.di;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.yao.mvpdemo.network.JsonUtil;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.network.di
 * @ClassName: HttpModule
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/16 17:39
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/16 17:39
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Module
public class HttpModule {

    private static final String TAG = HttpModule.class.getSimpleName();

    static class HttpLogger implements HttpLoggingInterceptor.Logger {

        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            //请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }

            boolean isJson = (message.startsWith("{")) && message.endsWith("}")
                    || (message.startsWith("[")) && message.endsWith("]");

            if (isJson) {
                message = JsonUtil.formatJson(message);
            }

            mMessage.append(message.concat("\n"));

            if (message.startsWith("<-- END HTTP")) {
                Log.d(TAG, mMessage.toString());
            }
        }

    }
        @Provides
        Retrofit provideRetrofit(OkHttpClient client, HttpUrl baseUrl){
            return new Retrofit.Builder().baseUrl(baseUrl).client(client)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        @Provides
        OkHttpClient provideOkHttpClient(){
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new
                    HttpLoggingInterceptor(new HttpLogger());
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = httpBuilder.addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .readTimeout(10000, TimeUnit.SECONDS)
                    .connectTimeout(10000,TimeUnit.SECONDS)
                    .writeTimeout(10000,TimeUnit.SECONDS)
                    .build();
            return client;
        }

}
