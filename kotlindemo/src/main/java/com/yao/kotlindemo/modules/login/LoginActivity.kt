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

package com.yao.kotlindemo.modules.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yao.kotlindemo.R
import kotlinx.android.synthetic.main.activity_login_user.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotlindemo.modules.login
 * @ClassName:      LoginActivity
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/6/22 10:15
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/6/22 10:15
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class LoginActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)

        user_login_bt.setOnClickListener(onClickLister)
    }

    private val onClickLister = View.OnClickListener {

        when(it.id){
            R.id.user_login_bt->{
                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()




            }
        }
    }
}