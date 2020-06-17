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

package com.yao.mvpdemo;

import android.app.Application;

import com.yao.mvpdemo.di.AppComponent;
import com.yao.mvpdemo.di.AppModule;
import com.yao.mvpdemo.di.DaggerAppComponent;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/16 11:44
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/16 11:44
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
