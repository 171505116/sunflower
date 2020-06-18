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

import android.util.Log;

import com.yao.mvpdemo.R;
import com.yao.mvpdemo.base.BaseActivity;
import com.yao.mvpdemo.bean.PersonBean;
import com.yao.mvpdemo.ui.UIUtils;
import com.yao.mvpdemo.ui.login.di.DaggerLoginComponent;
import com.yao.mvpdemo.ui.login.di.LoginModule;

import javax.inject.Inject;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.ui.login
 * @ClassName: LoginActivity
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/17 11:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/17 11:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LoginActivity extends BaseActivity<LoginContract.Presenter> {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Inject
    PersonBean personBean;

    @Override
    protected void initData() {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrameLayout);
        if(loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            UIUtils.addFragmentToActivity(getSupportFragmentManager(),loginFragment,R.id.contentFrameLayout);
        }

        DaggerLoginComponent.builder().appComponent(mApplication.getAppComponent())
                .loginModule(new LoginModule(loginFragment))
                .build()
                .inject(this);

        personBean.setAuthor("Nas");
        Log.d(TAG,personBean.getAuthor());
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }
}
