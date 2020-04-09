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
import androidx.room.*

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotilondemo.data
 * @ClassName:      GardenPlantingDao
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/9 17:17
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/9 17:17
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings():LiveData<List<GardenPlanting>>

    @Query("SELECT EXISTS(SELECT 1 FROM garden_plantings WHERE plant_id =:plantId LIMIT 1)")
    fun isPlanted(plantId:String):LiveData<Boolean>

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT (plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): LiveData<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting:GardenPlanting):Long

    @Delete
    suspend fun deleteGardenPlanting(gardenPlanting:GardenPlanting)

}