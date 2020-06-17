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

package com.yao.mvpdemo.network.api;

import com.yao.mvpdemo.bean.BaseResponse;
import com.yao.mvpdemo.bean.ProjectBean;
import com.yao.mvpdemo.bean.ProjectItem;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.network
 * @ClassName: WanAndroidApi
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/16 12:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/16 12:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface WanAndroidApi {

    @GET("project/tree/json")
    Observable<ProjectBean> getProject();


    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItem> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

    @POST("user/register")
    @FormUrlEncoded
    Maybe<BaseResponse> register(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);


    @POST("user/login")
    @FormUrlEncoded
    Maybe<BaseResponse> login(@Field("username") String username, @Field("password") String password);


}
