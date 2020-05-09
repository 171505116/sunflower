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

import android.Manifest
import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yao.downloaderfile.data.NasOffLineMapProvince
import pub.devrel.easypermissions.EasyPermissions
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import com.yao.downloaderfile.OffMapDownloadListener as OffMapDownloadListener
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

const val READ_EXTERNAL_STORAGE:Int = 123
const val WRITE_EXTERNAL_STORAGE = 124
/**
 * @Author: Xiehy
 * @Date: 2020/5/7  10:21
 * @Description:   下载页面
 */
class MainActivity : AppCompatActivity(),EasyPermissions.PermissionCallbacks {
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }




    //读写权限
    val perms: Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    /**
     * 离线地图下载地址
     */
    val OFFLINEMAPURL:String = "http://192.168.1.243:8096/offlineMap/a204.zip";
    val TAG:String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initData()
        var btn_download = findViewById<Button>(R.id.btn_download)
        var offDownloadManager: OffDownloadManager = OffDownloadManager(this, OFFLINEMAPURL,"a204.zip")
        offDownloadManager.setListener(object :OffMapDownloadListener{
            override fun onPrepare() {

            }

            override fun onSuccess(path: String) {
               Log.d(TAG,"下载成功:"+path)
            }

            override fun onFailed(throwable: Throwable) {

            }

        } )
        btn_download.setOnClickListener(View.OnClickListener {
            if(EasyPermissions.hasPermissions(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                offDownloadManager.download()
            }else{
                Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if(!EasyPermissions.hasPermissions(this,perms.toString())){
            EasyPermissions.requestPermissions(this,"",READ_EXTERNAL_STORAGE,perms.toString())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private fun initData() {
        var json = getJsonByAsset()
        Log.e(TAG,json)
        //解析json 数据
        var gson = Gson()
        //var type = TypeToken<List<NasOffLineMapProvince>>()::getType
        //var result:List<NasOffLineMapProvince> = gson.fromJson(json, NasOffLineMapProvince::class.javaObjectType)
        var result:List<NasOffLineMapProvince> = gson.fromJson<Array<NasOffLineMapProvince>>(json, Array<NasOffLineMapProvince>::class.java).toMutableList()
//        result?.let {
//            if (result.size>0){
//                Log.e(TAG,result.size.toString())
//            }
//        }


    }


    public fun getJsonByAsset():String{
        var inputStream = resources.assets.open("offline.txt")
        var inputStreamReader = InputStreamReader(inputStream, Charsets.UTF_8)
        var br = BufferedReader(inputStreamReader)
        var sb = StringBuffer()
        var length:String ? = null
        while ({length = br.readLine();length}()!= null){
            sb.append(length)
        }
        //关流
        br.close()
        inputStreamReader.close()
        inputStream.close()
        return sb.toString()
    }
}
