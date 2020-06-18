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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.lib
 * @ClassName: FromIterable
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 17:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 17:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FromIterable {

    public static void main(String[] args) {
        //设置一个集合
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //2.通过fromIterable()将集合中的对象/数据发送出去
        Observable.fromIterable(list)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("集合遍历");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("集合遍历中的数据元素="+integer);
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
