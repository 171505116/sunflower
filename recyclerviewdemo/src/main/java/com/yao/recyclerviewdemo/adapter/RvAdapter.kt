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

package com.yao.recyclerviewdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yao.recyclerviewdemo.R
import com.yao.recyclerviewdemo.bean.BaseBean
import com.yao.recyclerviewdemo.viewholder.RvViewHolder

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.recyclerviewdemo.adapter
 * @ClassName:      RvAdapter
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/5/9 11:19
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/5/9 11:19
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class RvAdapter(var datas:List<BaseBean>) :RecyclerView.Adapter<RvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_layout,parent,false)
        return  RvViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        var baseBean = datas.get(position)
        holder.tv_item.text = baseBean.name
    }
}