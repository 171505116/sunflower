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

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.yao.mvpdemo.R;
import com.yao.mvpdemo.base.BaseFragment;
import com.yao.mvpdemo.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.login
 * @ClassName: LoginFragment
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 9:58
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 9:58
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements
        LoginContract.View {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    private LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        Observable<CharSequence> observableName = RxTextView.textChanges(etUsername);
        Observable<CharSequence> observablePassword = RxTextView.textChanges(etPassword);


        Observable.combineLatest(observableName, observablePassword
                , new BiFunction<CharSequence, CharSequence, Object>() {
                    @Override
                    public Object apply(CharSequence phone, CharSequence password) throws Exception {
                        return isUsrValid(phone.toString()) && isPasswordValid(password.toString());
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object enabled) throws Exception {
                        btnLogin.setEnabled((Boolean) enabled);
                    }
                });


        RxView.clicks(btnLogin).throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        String username = etUsername.getText().toString();
                        String password = etPassword.getText().toString();
                        mPresenter.login(username, password);
                    }
                });
     }

    @Override
    public void loginSuccess(LoginContract.Model result) {
        if (result.isSuccess()) {
            startActivity(new Intent(mActivity, MainActivity.class));
        } else {
            Toast.makeText(mActivity, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean isUsrValid(String usr) {
        return usr.length() == 11;
    }

    private static boolean isPasswordValid(String pwd) {
        return pwd.length() >= 6;
    }


    protected static LoginFragment newInstance() {
        return new LoginFragment();
    }
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if(mPresenter == null){//P层和V层关联起来
            mPresenter = presenter;
        }
    }
}
