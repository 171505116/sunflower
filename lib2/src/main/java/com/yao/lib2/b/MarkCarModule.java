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

package com.yao.lib2.b;

import dagger.Module;
import dagger.Provides;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib2.b
 * @ClassName: MarkCarModule
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 10:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 10:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
/**
 * 用于标注提供依赖的类。你可能会有点困惑，上面不是提到用@Inject标记构造函数就可以提供依赖了么，
 * 为什么还需要@Module？很多时候我们需要提供依赖的构造函数是第三方库的，我们没法给它加上@Inject注解，
 * 又比如说提供以来的构造函数是带参数的，如果我们之所简单的使用@Inject标记它，那么他的参数又怎么来呢？@Module正是帮我们解决这些问题的
 */
@Module
public class MarkCarModule {

    @Provides
    Engine providesEngine(){
        return new Engine("Nas-Gear");
    }

}
