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

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.downloaderfile
 * @ClassName: AndroidDownloadManager
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/5/8 15:58
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/8 15:58
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AndroidDownloadManager {

    private static final String TAG = AndroidDownloadManager.class.getSimpleName();
    private DownloadManager downloadManager;
    private Context context;
    private long downloadId;
    private String url;
    private String name;

    private String path;

    private AndroidDownloadManagerListener listener;

//    public AndroidDownloadManager(Context context, String url) {
//        this(context, url, getFileNameByUrl(url));
//    }

    public AndroidDownloadManager(Context context, String url, String name) {
        this.context = context;
        this.url = url;
        this.name = name;
        //注册广播接收者，监听下载状态
        context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public AndroidDownloadManager setListener(AndroidDownloadManagerListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 开始下载
     */
    public void download() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://192.168.1.243:8096/offlineMap/a204.zip"));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //移动网络情况下是否允许漫游
        //request.setAllowedOverRoaming(false);
        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("a204.zip");
        request.setDescription("文件正在下载中......");
        request.setVisibleInDownloadsUi(true);

        //设置下载的路径
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "a204.zip");
        request.setDestinationUri(Uri.fromFile(file));
        path = file.getAbsolutePath();

        //获取DownloadManager
        if (downloadManager == null) {
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        }
        //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
        if (downloadManager != null) {
            if (listener != null) {
                listener.onPrepare();
            }
            downloadId = downloadManager.enqueue(request);
        }


    }

    //广播监听下载的各个状态
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager.Query query = new DownloadManager.Query();
            //通过下载的id查找
            query.setFilterById(downloadId);
            Cursor cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                switch (status) {
                    //下载暂停
                    case DownloadManager.STATUS_PAUSED:
                        Log.d(TAG,"下载暂停");
                        break;
                    //下载延迟
                    case DownloadManager.STATUS_PENDING:
                        Log.d(TAG,"下载延迟");
                        break;
                    //正在下载
                    case DownloadManager.STATUS_RUNNING:
                        Log.d(TAG,"正在下载");
                        break;
                    //下载完成
                    case DownloadManager.STATUS_SUCCESSFUL:
                        if (listener != null) {
                            listener.onSuccess(path);
                        }
                        cursor.close();
                        context.unregisterReceiver(receiver);
                        break;
                    //下载失败
                    case DownloadManager.STATUS_FAILED:
                        if (listener != null) {
                            Log.d(TAG,"下载失败");
                            //listener.onFailed(new Exception("下载失败"));
                        }
                        cursor.close();
                        context.unregisterReceiver(receiver);
                        break;
                }
            }
        }
    };


    // ——————————————————————私有方法———————————————————————

    /**
     * 通过URL获取文件名
     *
     * @param url
     * @return
     */
    private static final String getFileNameByUrl(String url) {
        String filename = url.substring(url.lastIndexOf("/") + 1);
        filename = filename.substring(0, filename.indexOf("?") == -1 ? filename.length() : filename.indexOf("?"));
        return filename;
    }

}
