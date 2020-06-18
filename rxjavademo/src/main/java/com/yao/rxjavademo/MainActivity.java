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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framelayout,MainFragment.newInstance(),MainFragment.class.getName());
        transaction.commit();

        //1.创建一个Observable 可被观察的
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                if(!emitter.isDisposed()){
                    emitter.onNext("hello rxjava2");
                    emitter.onNext("hello 小伙伴们");
                }
                emitter.onComplete();
            }
        });

        //2.创建一个Observer 观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe:" + d);
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };
        //3 观察者通过订阅(subscribe) 被观察者 把它们连接到一起
        // observer (观察者) 订阅 observable(被观察者)
        //如果没有产生关系，被观察者里面的方法不会执行
        //observable.subscribe(observer);
    }
}

