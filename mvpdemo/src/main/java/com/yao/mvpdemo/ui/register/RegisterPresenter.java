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

import androidx.annotation.UiThread;

import com.yao.mvpdemo.base.AbPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.register
 * @ClassName: RegisterPresenter
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 14:53
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 14:53
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RegisterPresenter extends AbPresenter<RegisterContract.Model,RegisterContract.View> implements RegisterContract.Presenter{

    private Disposable disposable;

    @Inject
    public RegisterPresenter(RegisterContract.Model model,RegisterContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void register(String mobile, String password, String rePassword) {



        disposable = mModel.register(mobile,password,rePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterContract.Model>() {
                    @Override
                    public void accept(RegisterContract.Model model) throws Exception {
                        mView.registerSuccess(model);
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
        mView =null;
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
            disposable = null;
        }
    }
}
