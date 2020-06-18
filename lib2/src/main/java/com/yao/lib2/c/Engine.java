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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib2.c
 * @ClassName: Engine
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/15 16:15
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/15 16:15
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Engine {


    /**
     * 用于自定义注解，也就是说@Qulifier就如同Java提供的几种基本元注解一样用来标记注解类。
     * 我们在使用@Module来标注提供依赖的方法时，方法名我们是可以随便定义的
     * （虽然我们定义方法名一般以provide开头，但这并不是强制的，只是为了增加可读性而已）。
     * 那么Dagger2怎么知道这个方法是为谁提供依赖呢？答案就是返回值的类型，
     * Dagger2根据返回值的类型来决定为哪个被@Inject标记了的变量赋值。
     * 但是问题来了，一旦有多个一样的返回类型Dagger2就懵逼了。@Qulifier的存在正式为了解决这个问题，
     * 我们使用@Qulifier来定义自己的注解，然后通过自定义的注解去标注提供依赖的方法和依赖需求方
     * （也就是被@Inject标注的变量），这样Dagger2就知道为谁提供依赖了。----一个更为精简的定义：当类型不足以鉴别一个依赖的时候，我们就可以使用这个注解标示
     * 1. 使用@Qulifier定义两个注解
     */

    private String name;

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierA{

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierB{

    }


    public Engine(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }

    public void run(){
        System.out.println("running");
    }
}
