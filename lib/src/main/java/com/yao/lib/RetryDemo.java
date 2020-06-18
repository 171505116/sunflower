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

package com.yao.lib;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: RetryDemo
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 17:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 17:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RetryDemo {

    static int count = 0;//重连次数

    public static void main(String[] args) {
        retry();
    }

    private static void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>(){

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0;i<100;i++){
                    emitter.onNext(i);
                    if (i ==2){
                        emitter.onError(new IOException("retry error"));
                    }
                }
                emitter.onComplete();
            }
        }).retry(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                if(throwable instanceof IOException&& count++<4){
                    System.out.println("重连次数："+count);
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("integer:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
