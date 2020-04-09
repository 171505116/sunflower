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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotilondemo.data
 * @ClassName:      PlantDao
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/9 16:27
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/9 16:27
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants():LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber:Int):LiveData<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId:String):LiveData<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants:List<Plant>)
}