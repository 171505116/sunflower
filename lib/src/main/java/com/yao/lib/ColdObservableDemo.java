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

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: ColdObservableDemo
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 11:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 11:46
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ColdObservableDemo {

    /**
     * 冷： 观察者订阅了，才会开始执行发射数据流的代码
     * Observable  和 Observer 是一对一的关系
     * 对 Cold Observable 而已，有多个Observer的时候，
     * 它们各自的事件是独立的
     * 事件是什么？
     *  事件类型         作用
     *   onNext()        观察者会回调它的onNext()方法
     *   onError()        onError事件发送之后，其他事件不会继续发送
     *   onComplete()     onComplete事件发送之后，其他事件不会继续发送
     */
    public static void main(String[] args) {

        //创建一个"冷"的被观察者
        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(final ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS,Schedulers.computation()).take(Integer.MAX_VALUE).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        observableEmitter.onNext(aLong);
                    }
                });
            }
        }).observeOn(Schedulers.newThread());
    System.out.println("programmer running");
        //
        observable.subscribe(new Consumer<Long>(){

            public void accept(Long aLong) throws Exception {
                System.out.println("Consumer1:"+aLong);
            }
        });


        observable.subscribe(new io.reactivex.functions.Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("    consumer2: " + aLong);
            }
        });


        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(new io.reactivex.functions.Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("            consumer3: " + aLong);
            }
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
