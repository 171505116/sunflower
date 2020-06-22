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

package com.yao.kotlindemo.test.objectDemo

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.kotlindemo.test.objectDemo
 * @ClassName:      oDemo
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/6/22 10:53
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/6/22 10:53
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
fun main(){

     val person = Person()//次构造

     val person2 = Person(6465) //主构造

    Person(464,"DErry") //次构造
    Person(464,'M')//次构造
}