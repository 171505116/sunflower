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
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar


/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotilondemo.data
 * @ClassName:      GardenPlanting
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/9 17:35
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/9 17:35
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
@Entity(tableName =  "garden_plantings",
        foreignKeys = [
            ForeignKey(entity = Plant::class,parentColumns = ["id"],
            childColumns = ["plant_id"])
        ],
        indices = [Index("plant_id")])
data class GardenPlanting (
        @ColumnInfo(name="plant_id") val plantId:String,
                           @ColumnInfo(name="plant_date") val plantDate: Calendar = Calendar.getInstance(),
                           @ColumnInfo(name ="last_watering_date")val lastWateringDate:Calendar= Calendar.getInstance()){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var gardenPlantingId:Long = 0

}