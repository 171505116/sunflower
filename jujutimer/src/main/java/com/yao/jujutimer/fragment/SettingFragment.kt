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

package com.yao.jujutimer.fragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yao.jujutimer.R

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.jujutimer.fragment
 * @ClassName:      SettingFragment
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/4/30 16:47
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/4/30 16:47
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        val updatePreference: Preference? = findPreference("update")
        updatePreference?.setOnPreferenceClickListener {
            Toast.makeText(context, "更新", Toast.LENGTH_SHORT).show()
            true
        }
    }
}