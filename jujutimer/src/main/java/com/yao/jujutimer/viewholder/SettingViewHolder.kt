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

package com.yao.jujutimer.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.yao.jujutimer.R
import kotlinx.android.synthetic.main.item_setting_layout.view.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.jujutimer.viewholder
 * @ClassName:      SettingViewHolder
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/30 14:45
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/30 14:45
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SettingViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    var  materialTextView: MaterialTextView = itemview.findViewById(R.id.tv_md_setting) as MaterialTextView
}