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

package com.yao.dagger2javademo.mvp;

/**
 * @ProjectName: sunflower
 * @Package: com.yao.dagger2javademo.mvp
 * @ClassName: MainContract
 * @Description: java类作用描述
 * @Author: Anson
 * @CreateDate: 2020/6/15 10:54
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/15 10:54
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface MainContract {

    interface View{
        void success();
        void failed();
    }

}
