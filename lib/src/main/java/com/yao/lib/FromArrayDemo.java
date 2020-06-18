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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: FromArrayDemo
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 17:14
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 17:14
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FromArrayDemo {

    public static void main(String[] args) {
        fromArray();
    }

    public static void fromArray(){
        //1.设置需要传入的数组
        Integer[] items = {0,1,2,3,4};

        //2.创建被观察者对象（Observable） 时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中所有数据
        Observable.fromArray(items)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("数组遍历");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("数组中的元素="+integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("对Error 事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("遍历结束");
                    }
                });
    }
}
