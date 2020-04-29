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

package com.yao.jujutimer

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.yao.jujutimer.activity.SettingActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.Timer
import kotlin.concurrent.timerTask





class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //显示时间
         var tv_timer = findViewById<TextView>(R.id.tv_timer);
        //设置字体
        val typeface = Typeface.createFromAsset(this.getAssets(), "font/digital-7.ttf")
        tv_timer.setTypeface(typeface)
        tv_timer.setText(getNow())


        Timer().schedule(timerTask {
            tv_timer.post { tv_timer.setText(getNow()) }
        },0,1000)


        var iv_setting = findViewById<ImageView>(R.id.iv_setting)
        iv_setting.setOnClickListener {
            //打开设置页面
            val intent = Intent(baseContext,SettingActivity::class.java);
            startActivity(intent)
        }
    }

    fun getNow():String{
        return SimpleDateFormat("HH:mm:ss").format(Date())
    }

}