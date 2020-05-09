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

package com.yao.downloaderfile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.downloaderfile
 * @ClassName: MainActivity01
 * @Description: 使用java 实现下载功能
 * @Author: Anson
 * @CreateDate: 2020/5/8 15:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/8 15:46
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainActivity01 extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = MainActivity01.class.getSimpleName();
    private static final String url = "http://192.168.1.243:8096/offlineMap/a204.zip";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_down = findViewById(R.id.btn_download);
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"下载");
                new AndroidDownloadManager(MainActivity01.this, url,"a204.zip")
                        .setListener(new AndroidDownloadManagerListener() {
                            @Override
                            public void onPrepare() {
                                Log.d(TAG, "onPrepare");
                            }

                            @Override
                            public void onSuccess(String path) {
                                Log.d(TAG, "onSuccess >>>>" + path);
                            }

                            @Override
                            public void onFailed(Throwable throwable) {
                                Log.e(TAG, "onFailed", throwable);
                            }
                        })
                        .download();
            }
        });
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
