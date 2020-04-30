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

package com.yao.jujutimer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yao.jujutimer.R
import com.yao.jujutimer.bean.SettingBean
import com.yao.jujutimer.viewholder.SettingViewHolder

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.jujutimer.adapter
 * @ClassName:      SettingListAdapter
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/30 14:50
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/30 14:50
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SettingListAdapter(val list: List<SettingBean>) : RecyclerView.Adapter<SettingViewHolder>() {
    override fun getItemCount(): Int {
        return  list.size
    }


    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.materialTextView.setText(list[position].title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        var   view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting_layout,parent,false)
        return SettingViewHolder(view)
    }



    private class SettingDiffCallback : DiffUtil.ItemCallback<SettingBean>() {

        override fun areItemsTheSame(oldItem: SettingBean, newItem: SettingBean): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: SettingBean, newItem: SettingBean): Boolean {
            return oldItem == newItem
        }
    }
}