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

package com.yao.recyclerviewdemo.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.recyclerviewdemo.itemDecoration
 * @ClassName:      SpacesItemDecoration
 * @Description:     设置recyclerview item 的间距，水平间距
 * @Author:         Anson
 * @CreateDate:     2020/5/9 11:46
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/5/9 11:46
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SpacesItemDecoration(var spaceInt: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = spaceInt
        outRect.right = spaceInt
        outRect.bottom = spaceInt

        if (parent.getChildAdapterPosition(view) ==0)
            outRect.top = spaceInt
    }
}