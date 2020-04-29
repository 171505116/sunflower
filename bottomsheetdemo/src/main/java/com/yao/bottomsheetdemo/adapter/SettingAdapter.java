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

package com.yao.bottomsheetdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yao.bottomsheetdemo.R;
import com.yao.bottomsheetdemo.bean.SettingBean;
import com.yao.bottomsheetdemo.viewholder.SettingViewHolder;

import java.util.List;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.bottomsheetdemo.adapter
 * @ClassName: SettingAdapter
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/4/29 15:19
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/29 15:19
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingViewHolder> {

    private List<SettingBean> settingBeans;

    public SettingAdapter(List<SettingBean> beanList){
        this.settingBeans = beanList;
    }
    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_layout,parent,false);
        SettingViewHolder holder = new SettingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        SettingBean settingBean = settingBeans.get(position);
        holder.materialTextView.setText(settingBean.title);
    }

    @Override
    public int getItemCount() {
        if (settingBeans!= null && settingBeans.size()>0){
            return settingBeans.size();
        }
        return 0;
    }
}
