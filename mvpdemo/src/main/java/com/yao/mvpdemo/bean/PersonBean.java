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

package com.yao.mvpdemo.bean;

import java.util.List;

import javax.inject.Inject;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.mvpdemo.bean
 * @ClassName: PersonBean
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/18 9:32
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 9:32
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class PersonBean {


    /**
     * _id : 5eb12aa117bf93950887f234
     * author : HWilliamGo
     * category : GanHuo
     * createdAt : 2020-05-05 16:58:09
     * desc : 一个用于获取View Tree信息的工具
     * images : ["https://gank.io/images/61103737624140b1840e2dfd7f24ff43"]
     * likeCounts : 0
     * publishedAt : 2020-05-05 16:58:09
     * stars : 1
     * title :  Android调试ViewTree工具
     * type : Android
     * url : https://github.com/HWilliamgo/FastViewTree
     * views : 17
     */

    private String _id;
    private String author;
    private String category;
    private String createdAt;
    private String desc;
    private int likeCounts;
    private String publishedAt;
    private int stars;
    private String title;
    private String type;
    private String url;
    private int views;
    private List<String> images;

    public PersonBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
