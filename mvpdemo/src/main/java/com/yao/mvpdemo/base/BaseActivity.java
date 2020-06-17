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

package com.yao.mvpdemo.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yao.mvpdemo.App;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.base
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/16 11:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/16 11:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {


    protected App mApplication;
    private Unbinder mUnbinder;
    protected Context mContext;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (App) getApplication();
        mContext = this;
        setContentView(layoutId());
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    protected abstract void initData();

    protected abstract int layoutId();
}
