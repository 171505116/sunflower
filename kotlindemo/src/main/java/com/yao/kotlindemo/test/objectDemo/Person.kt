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
 * @ClassName:      Person
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/6/22 10:53
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/6/22 10:53
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
// public final class Person 默认就是这样的，不能被继承
// 加上open 就可以被继承
open class Person(id :Int) {

    //次构造
    constructor(id:Int,name:String):this(id){

    }

    //次构造
    constructor(id:Int,sex:Char):this(id){

    }

    //次构造
    constructor():this(787){
        println("次构造 onstructor():this(787)")
    }
}