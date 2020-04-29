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

package com.yao.navigationdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.yao.navigationdemo.R
import kotlinx.android.synthetic.main.list_02_fm_layout.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.navigationdemo.fragment
 * @ClassName:      List01Fragment
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/13 10:37
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/13 10:37
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class List02Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_02_fm_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_01fragment.setOnClickListener{
            Navigation.findNavController(it).navigateUp()
        }
        btn_03fragment.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_list03)
        }
    }

}