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

package com.yao.kotilondemo.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotilondemo.data
 * @ClassName:      Plant
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/9 11:11
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/9 11:11
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
data class Plant(@PrimaryKey @ColumnInfo(name="id") val plantId:String,
                 val name:String,
                 val description:String,
                 val growZoneNumber:Int,
                 val wateringInterval:Int =7,
                 val imageUrl:String=""
                 ){

    fun shouldBeWatered(since:Calendar,lastWateringData:Calendar)=since>lastWateringData.apply { add(DAY_OF_YEAR,wateringInterval) }

    override fun toString() =name;
}