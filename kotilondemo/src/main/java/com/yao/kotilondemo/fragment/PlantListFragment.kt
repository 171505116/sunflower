/*
 * Copyright 2019 Google LLC
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

package com.yao.kotilondemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yao.kotilondemo.adapter.PlantAdapter
import com.yao.kotilondemo.databinding.FragmentPlantListBinding

/**
 * @Author: Xiehy
 * @Date: 2020/4/9  9:50
 * @Description: 植物列表页面
 */
class PlantListFragment:Fragment() {

    private val viewModel: PlantListViewModel by viewModels{
        InjectorUtils.pro
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPlantListBinding.inflate(inflater,container,false);
        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter:PlantAdapter){
        viewModel.plants.ob
    }
}

