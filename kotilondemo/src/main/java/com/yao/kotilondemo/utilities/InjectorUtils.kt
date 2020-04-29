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

package com.yao.kotilondemo.utilities

import android.content.Context
import com.yao.kotilondemo.data.AppDatabase
import com.yao.kotilondemo.data.GardenPlantingRepository
import com.yao.kotilondemo.data.PlantRepository
import com.yao.kotilondemo.viewmodels.GardenPlantingListViewModelFactory
import com.yao.kotilondemo.viewmodels.PlantDetailViewModelFactory
import com.yao.kotilondemo.viewmodels.PlantListViewModelFactory

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotilondemo.utilities
 * @ClassName:      InjectorUtils
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/9 14:30
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/9 14:30
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
object InjectorUtils {

    private fun getPlantRepository(context:Context):PlantRepository{
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }

    private fun getGardenPlantingRepository(context:Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(context: Context):GardenPlantingListViewModelFactory{
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context:Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return  PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(context:Context,plantId:String):PlantDetailViewModelFactory{
        return PlantDetailViewModelFactory(getPlantRepository(context),getGardenPlantingRepository(context),plantId)
    }
}