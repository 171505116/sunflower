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

package com.yao.lib2.c;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib2.c
 * @ClassName: MarkCarModule
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/15 16:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/15 16:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Module
public class MarkCarModule {

    public MarkCarModule() {
    }

    @Engine.QualifierA
    @Provides
    Engine providesEngineA() {
        return new Engine("Nas-GearA");
    }

    @Engine.QualifierB
    @Provides
    Engine providesEngineB() {
        return new Engine("Nas-GearB");
    }
}
