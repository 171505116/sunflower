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

package com.yao.jujutimer.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.yao.jujutimer.R
import kotlinx.android.synthetic.main.activity_setting_layout.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.jujutimer.activity
 * @ClassName:      SettingActivity
 * @Description:     设置 页面，（包括升级，关于app（显示版本号，作者信息），）
 * @Author:         Anson
 * @CreateDate:     2020/4/21 13:40
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/21 13:40
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SettingActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
        setContentView(R.layout.activity_setting_layout)

        recyclerView = findViewById(R.id.rv_setting)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initActionBar()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("设置")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}