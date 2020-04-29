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

package com.yao.bottomsheetdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.yao.bottomsheetdemo.adapter.SettingAdapter;
import com.yao.bottomsheetdemo.bean.SettingBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {


    public static final String TAG = SettingActivity.class.getSimpleName();
    private MaterialToolbar toolbar;
    private RecyclerView recyclerView;
    private List<SettingBean> list = new ArrayList<SettingBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initView();
    }

    private void initView() {
        recyclerView  = findViewById(R.id.rv_setting);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        //设置水平分隔线
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        SettingBean settingBean = new SettingBean();
        settingBean.title = "检测更新";
        list.add(settingBean);
         settingBean = new SettingBean();
        settingBean.title = "关于APP";
        list.add(settingBean);

        SettingAdapter settingAdapter = new SettingAdapter(list);
        recyclerView.setAdapter(settingAdapter);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("设置");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
