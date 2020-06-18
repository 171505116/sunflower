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


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import sun.rmi.runtime.Log;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: CacheDemo
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 16:49
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 16:49
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CacheDemo {

    static String memoryCache = null;
    static String diskCache = null;

    /**
     * 从磁盘/内存缓存中获取缓存数据
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 设置第1个Observable:检查内存缓存是否有该数据缓存
         *
         */
        final Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                if(memoryCache!= null){
                    //若有该数据，则发送
                    emitter.onNext(memoryCache);
                }else{
                    //若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }
            }
        });

        /**
         * 设置第2个Observable:检查磁盘缓存是否该数据的 缓存
         */
        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (diskCache != null){
                    //若有数据，则发送
                    emitter.onNext(diskCache);
                }else {
                    //若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }
            }
        });

        /**
         * 设置第3个Observable:通过网络获取数据
         */
        Observable<String> network = Observable.just("从网络中获取数据");

        /**
         * 通过concat() 和firstElement()操作符实现缓存功能
         */
        // 1.通过concat()合并memory,disk,network 3个被观察者的事件
        // 并将它们按顺序串联成队列
        Observable.concat(memory,disk,network)
                .firstElement()
                .doOnSuccess(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnSuccess:"+s);
                        memoryCache = s;
                        diskCache = s;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnComplete");
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("最终获取的数据来源:="+s);
                System.out.println("memoryCache:"+memoryCache);
                System.out.println("diskCache:"+diskCache);
            }
        });
    }
}
