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

package com.yao.lib2.d;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib2.d
 * @ClassName: Engine
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/15 16:40
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/15 16:40
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Engine {


    /**
     * 用于自定义注解，我能可以通过@Scope自定义的注解来限定注解作用域，实现局部的单例
     * 1. @Scope定义一个CarScope注解
     */
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CarScope{

    }

    private String name;

    Engine(String name){
        this.name = name;
        System.out.println("Engine create:"+name);
    }



    public void run(){
        System.out.println("running");
    }
}
