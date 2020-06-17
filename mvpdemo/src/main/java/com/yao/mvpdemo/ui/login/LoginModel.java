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

package com.yao.mvpdemo.ui.login;

import com.yao.mvpdemo.bean.BaseResponse;
import com.yao.mvpdemo.network.api.WanAndroidApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.login
 * @ClassName: LoginModel
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 9:32
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 9:32
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LoginModel implements LoginContract.Model{

    private String user;
    private boolean isSuccess;
    private String msg;


    @Inject
    public LoginModel() {
    }

    public LoginModel(String user,boolean isSuccess,String msg){
        this.user = user;
        this.isSuccess = isSuccess;
        this.msg =msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public Observable<LoginContract.Model> login(final String model, String password) {
        return wanAndroidApi.login(model,password).map(new Function<BaseResponse, LoginContract.Model>() {
            @Override
            public LoginContract.Model apply(BaseResponse baseResponse) throws Exception {
                return new LoginModel(model,baseResponse.getErrorCode() ==0?true:false,baseResponse.getErrorMsg());
            }
        }).toObservable();
    }

    @Inject
    WanAndroidApi wanAndroidApi;

    
}
