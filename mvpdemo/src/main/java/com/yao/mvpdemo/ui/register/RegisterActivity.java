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


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.yao.mvpdemo.R;
import com.yao.mvpdemo.base.BaseActivity;
import com.yao.mvpdemo.ui.login.LoginActivity;
import com.yao.mvpdemo.ui.register.di.DaggerRegisterComponent;
import com.yao.mvpdemo.ui.register.di.RegisterModule;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_re_password)
    EditText etRePassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private static int SECOND = 20;
    private Observable<Boolean> registerObservable;

    @Override
    protected int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

        DaggerRegisterComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);

        registerObservable = RxView.clicks(btnRegister)
                .throttleFirst(SECOND, TimeUnit.SECONDS) //防止20秒内连续点击,或者只使用doOnNext部分
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Function<Object, Boolean>() {
                    @Override
                    public Boolean apply(Object o) throws Exception {
                        return false;
                    }
                })
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean enabled) throws Exception {
                        Log.d(TAG,"enabled:"+enabled);
                        btnRegister.setEnabled(enabled);
                    }
                });
        registerObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean s) throws Exception {
                Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                        .take(SECOND)
                        .subscribe(new Consumer<Long>() {
                                       @Override
                                       public void accept(Long aLong) throws Exception {
                                           RxTextView.text(btnRegister).accept("剩余" + (SECOND - aLong) + "秒");
                                       }
                                   }
                                , new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        throwable.printStackTrace();
                                    }
                                }
                                , new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        RxTextView.text(btnRegister).accept("注 册");
                                        RxView.enabled(btnRegister).accept(true);
                                    }
                                });
            }
        });


        Observable<CharSequence> observableName = RxTextView.textChanges(etUsername);
        Observable<CharSequence> observablePassword = RxTextView.textChanges(etPassword);
        Observable<CharSequence> observableRePassword = RxTextView.textChanges(etRePassword);

        Observable.combineLatest(observableName
                , observablePassword
                , observableRePassword
                , new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence usr, CharSequence pwd, CharSequence rePwd) throws Exception {
                        return registerValid(usr.toString(), pwd.toString(), rePwd.toString());
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean enabled) throws Exception {
                        btnRegister.setEnabled(enabled);
                    }
                });

        RxView.clicks(btnRegister)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object v) throws Exception {
                        String username = etUsername.getText().toString();
                        String password = etPassword.getText().toString();
                        Log.d(TAG,"点击注册");
                        mPresenter.register(username, password, password);
                    }
                });


    }

    private static Boolean registerValid(String usr, String pwd, String rePwd) {
        return isUsrValid(usr) && isPasswordValid(pwd, rePwd);
    }

    private static boolean isUsrValid(String usr) {
        return usr.length() == 11;
    }

    private static boolean isPasswordValid(String pwd, String rePwd) {
        return pwd.length() >= 6 && TextUtils.equals(pwd, rePwd);
    }

    @Override
    public void registerSuccess(RegisterContract.Model result) {
        if (result.isSuccess()) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }
}
