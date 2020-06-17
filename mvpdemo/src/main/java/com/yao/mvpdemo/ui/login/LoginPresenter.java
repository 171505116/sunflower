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

import com.yao.mvpdemo.base.AbPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.login
 * @ClassName: LoginPresenter
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 9:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 9:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LoginPresenter extends AbPresenter<LoginContract.Model,LoginContract.View> implements LoginContract.Presenter{

    private Disposable disposable;

    @Inject
    public LoginPresenter(LoginContract.Model model,LoginContract.View view){
        mModel = model;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void login(String mobile, String password) {
        //通过model 获取数据
        disposable = mModel.login(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginContract.Model>() {
                    @Override
                    public void accept(LoginContract.Model model) throws Exception {
                        mView.loginSuccess(model);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });

    }

    @Override
    public void start() {
        this.mView.setPresenter(this);
    }

    @Override
    public void onDestory() {
        mView = null;
        if(disposable!= null && !disposable.isDisposed()){
            disposable.dispose();
            disposable=null;
        }
    }
}
