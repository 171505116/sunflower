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

package com.yao.bottomsheetdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior<RelativeLayout> behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomsheet_layout);
        RelativeLayout rl = findViewById(R.id.bottom_drawer);
        TextView tv_show = findViewById(R.id.tv_show);
        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果布局隐藏，要设置这个展开
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        TextView tv = findViewById(R.id.textView);
        //监听
        behavior = BottomSheetBehavior.from(rl);
        behavior.addBottomSheetCallback(createBottomSheetCallback(tv));
    }

    /**
     * 监听bottomSheet
     *
     * @return
     */
    private BottomSheetBehavior.BottomSheetCallback createBottomSheetCallback(final TextView textView) {
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        textView.setText("STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        textView.setText("STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        textView.setText("STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        textView.setText("STATE_HALF_EXPANDED");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        };
        return bottomSheetCallback;
    }


}
