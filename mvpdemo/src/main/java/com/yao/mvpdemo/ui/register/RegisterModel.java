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

package com.yao.mvpdemo.ui.register;

import com.yao.mvpdemo.bean.BaseResponse;
import com.yao.mvpdemo.network.api.WanAndroidApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.register
 * @ClassName: RegisterModel
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 14:31
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 14:31
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RegisterModel implements RegisterContract.Model{

    @Inject
    public RegisterModel() {
    }

    private String user;
    private boolean isSucess;
    private String msg;

    public RegisterModel(String user, boolean isSucess, String msg) {
        this.user = user;
        this.isSucess = isSucess;
        this.msg = msg;
    }

    @Inject
    WanAndroidApi wanAndroidApi;

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
        return isSucess;
    }

    @Override
    public Observable<RegisterContract.Model> register(final String mobile, String password, String rePassword) {
        return wanAndroidApi.register(mobile,password,rePassword)
                .map(new Function<BaseResponse, RegisterContract.Model>() {
                    @Override
                    public RegisterContract.Model apply(BaseResponse baseResponse) throws Exception {
                        return   (RegisterContract.Model) new RegisterModel(mobile, baseResponse.getErrorCode() == 0 ? true : false, baseResponse.getErrorMsg());
                    }
                }).toObservable();
    }
}
