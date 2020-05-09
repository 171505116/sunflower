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

package com.yao.recyclerviewdemo.viewholder

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.yao.recyclerviewdemo.R
import kotlinx.android.synthetic.main.item_cardview_layout.view.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.recyclerviewdemo.viewholder
 * @ClassName:      RvViewHolder
 * @Description:     RecyclerView 的viewholder
 * @Author:         Anson
 * @CreateDate:     2020/5/9 11:11
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/5/9 11:11
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class RvViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    var tv_item :TextView= itemView.findViewById(R.id.tv_areaName)
}