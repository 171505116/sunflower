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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: IntervalDemo
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/19 11:26
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/19 11:26
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class IntervalDemo {

    public static void main(String[] args) {
        interval();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void interval() {

        /**
         * 周期性操作
         *
         */
        Observable.interval(2,1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("每隔1s进行1次操作");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("对Complete事件作出响应");
                    }
                });
        // interval 默认在computation 调度器上执行，也可
        // 自定义指定线程调度器
    }
}
