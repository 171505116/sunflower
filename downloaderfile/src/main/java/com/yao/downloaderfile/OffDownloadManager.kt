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

package com.yao.downloaderfile

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File

/**
 *
 * @ProjectName:    sunflower
 * @Package:        com.yao.downloaderfile
 * @ClassName:      DownloadManager
 * @Description:     java类作用描述
 * @Author:         Anson
 * @CreateDate:     2020/5/7 16:40
 * @UpdateUser:     更新者：
 * @UpdateDate:     2020/5/7 16:40
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class OffDownloadManager(context: Context,url:String){
    var downloadManager: DownloadManager?=null
    var context:Context = context
    var downloadId:Long =0
    var url:String =url
    var name:String ?= null
    var path:String?= null
    var offMapDownloadListener:OffMapDownloadListener?=null;
    val TAG:String = OffDownloadManager::class.java.simpleName
    var receiver = Receiver()

    constructor(context: Context,url:String,name:String):this(context,url){
        this.name = name
    }

    fun setListener( offMapDownloadListener: OffMapDownloadListener){
        this.offMapDownloadListener = offMapDownloadListener
    }

    fun download(){
       var request: DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
        //移动网络情况下是否循序漫游
        request.setAllowedOverRoaming(false)
        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        request.setTitle(name)
        request.setDescription("文件正在下载中.....")

        //设置下载路径
        var file =  File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),name)
        request.setDestinationUri(Uri.fromFile(file))
        path = file.absolutePath.toString()

        //获取DownloadManager
        if (downloadManager == null){
            downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        }

        //将下载请求计入下载队列，加入下载队列后会给该任务返回一个long 类型的id,通过该id 可以取消任务
        if (downloadManager!= null){
            if(offMapDownloadListener!= null){
                offMapDownloadListener!!.onPrepare()
            }
            downloadId = downloadManager!!.enqueue(request)
        }

        //注册广播接收者，监听下载状态
        context.registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    inner class Receiver:BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            var query = DownloadManager.Query()
            //通过下载的id查找
            query.setFilterById(downloadId)
            var cursor: Cursor? = downloadManager?.query(query)
            if(cursor!!.moveToFirst()){
                var status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
               when (status) {
                    DownloadManager.STATUS_PAUSED -> Log.d(TAG, "下载暂停")
                    DownloadManager.STATUS_PENDING -> Log.d(TAG, "下载延迟")
                    DownloadManager.STATUS_RUNNING -> Log.d(TAG, "正在下载")
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        offMapDownloadListener?.onSuccess(path.toString())
                        Log.d(TAG, "下载完成")
                        cursor.close()
                        p0?.unregisterReceiver(receiver)

                    }
                    else-> {
                        Log.d(TAG, "下载失败")
                        cursor.close()
                        p0?.unregisterReceiver(receiver)
                    }

                }
            }

        }
    }
}