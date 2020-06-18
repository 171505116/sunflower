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

package com.yao.rxjavademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.rxjavademo
 * @ClassName: MainFragment
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 14:32
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 14:32
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainFragment extends ListFragment {


    private static final String TAG = MainFragment.class.getSimpleName();
    ArrayAdapter<String> adapter;

    Disposable disposable1;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] array = new String[]{
                "背压测试1",
                "背压测试1，取消",
                "RxJava流程分析",
                "maptest"
        };
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, array);
        setListAdapter(adapter);
    }

    Subscription subscription;

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = adapter.getItem(position);
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                backpressure04();
                break;
            case 1:
                if (disposable1 != null) {
                    disposable1.dispose();
                }
                break;
            case 2:
                rxjavademo();
                break;
            default:
                break;
        }

    }

    private void rxjavademo() {
        //1.创建一个被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Log.d(TAG, "subscribe:" + Thread.currentThread().getName());
                emitter.onComplete();
            }
        });
        //2.创建一个观察者
        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe:" + Thread.currentThread().getName());
                Log.d(TAG, "onSubscribe:" + d);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext:" + Thread.currentThread().getName());
                Log.d(TAG, "onNext:" + s);
            }


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + Thread.currentThread().getName());
                Log.d(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete:" + Thread.currentThread().getName());
                Log.d(TAG, "onComplete:");
            }
        };

        Observable observableSubscribeOn = observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.single());

        Observable observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .observeOn(Schedulers.single());

        //4.订阅
        observableObserveOn.subscribe(observer);

    }

    /**
     * 背压测试
     */
    private void backpressure01() {
        //出现背压问题，手机会出现anr  问题
        disposable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);

                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "Call:" + integer);
//                        Thread.sleep(10L);
                    }
                });

    }

    /**
     * 背压解决方案
     */
    private void backpressure02() {
        disposable1 = Observable.<Integer>create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                    Thread.sleep(10L);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.sample(1, TimeUnit.SECONDS)//sample操作符是定期扫描源Observable产生的结果，在指定的间隔周期内进行采样
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "call:" + integer);
                    }
                });
    }

    public void backpressure03() {
        disposable1 = Observable.<Integer>create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .sample(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "call:" + integer);
                    }
                });
    }

    public void backpressure04() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d("Zero", "emit 1");
                emitter.onNext(1);
                Log.d("Zero", "emit 2");
                emitter.onNext(2);
                Log.d("Zero", "emit 3");
                emitter.onNext(3);
                Log.d("Zero", "emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext:" + integer);
                        subscription.request(2);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }
}
