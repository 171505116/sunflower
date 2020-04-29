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

package com.yao.bottomsheetdemo.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.yao.bottomsheetdemo.R;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.bottomsheetdemo.viewholder
 * @ClassName: SettingViewHolder
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/4/29 14:25
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/29 14:25
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class SettingViewHolder extends RecyclerView.ViewHolder {

    public MaterialTextView materialTextView;

    public SettingViewHolder(@NonNull View itemView) {
        super(itemView);
        materialTextView =   itemView.findViewById(R.id.tv_md_setting);
    }
}
