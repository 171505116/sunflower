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

package com.yao.kotlindemo.test.objectDemo3

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotlindemo.test.objectDemo3
 * @ClassName:      User
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/6/22 11:44
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/6/22 11:44
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */

/**
 * 数据类 == Java 实体Bean
 */
//不会再修改了，可以是使用val
// get set 构造equals hashCode toString copy
data class User(val id:Int,val name:String,val sex:Char)