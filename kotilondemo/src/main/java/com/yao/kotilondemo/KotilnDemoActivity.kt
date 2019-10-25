/*
 * Copyright 2019 Google LLC
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

package com.yao.kotilondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class KotilnDemoActivity : AppCompatActivity() {


    companion object {
        val TAG = "KotilnDemoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var de: Int = 1;
        val d = 2
        sum(de, d)
        Log.e(TAG, sum(de, d).toString())
        val a: (Int, Int) -> String = { x, y -> (x + y).toString() }
        Log.e(TAG, inMethod().toString())
        whenMethod(1)
        var p = Person("hello",22)
        p.show()
        var man = Man("man",20)
        man.show()
        var c = C()
        c.bar()
        var d_class = D()
        d_class.foo()
        d_class.bar()
    }

    fun sumlambda(a: Int, b: Int): String {

        return a.toString();
    }

    //定义常量
    /**
     * 可变常量
     * var d
     * 语法 var <标识符> : <类型> = <初始化值>
     *
     *
     * 不可变常量
     * val  <标识符> : <类型> = <初始化值>
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 函数定义
     * 使用关键字
     * 有返回值
     * 语法 fun sum(a: Int, b: Int): Int
     *
     * 无返回值
     * fun printSum(a: Int, b: Int): Unit
     * Unit 类似Java中的void，可以不写
     *
     * 可变参数长度的函数定义
     * 函数的变长参数可以用 vararg
     * fun vars(vararg v:Int){
     *       for(vt in v){
     *    print(vt)
     *    }
     * }
     *
     * lambda(匿名函数)
     *  val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
     */

    /**
     * 条件表达式
     * if
     * 例子参考下面的max函数max
     *
     * 三元操作符
     * val c  = if(a>=b) a else b
     *
     *
     * 使用区间 in 运算符来检测某个数字是否在指定区间内，区间格式为 x..y
     *
     * when 表达式
     * 类似其他语言的switch
     *
     */
    fun max(a: Int, b: Int): Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    fun inMethod(): Int {
        val a = 2;
        val b = 3;
        if (a in 1..8) {
            return a;
        } else {
            return b;
        }
    }

    fun whenMethod(x: Int) {
        when (x) {
            1 -> Log.e(TAG, "1")
            2 -> Log.e(TAG, "2")
            else -> {

            }
        }
    }

    /**
     * 定义类
     * 主构造函数
     * 次构造函数
     * 使用open 关键字修饰，才可以被继承
     */
    open class Person(name: String) {
        val TAG = "person"
        val name = name
        //主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块
        val firstProperty = "First property:$name".also(::println)
        var age: Int = 0



        //次构造函数
        constructor(name: String, age: Int) : this(name) {
            this.age = age;
        }

        open fun show(){
            Log.e(TAG,"name:"+name+":age"+age.toString())
        }

        init {
            Log.e(TAG, " First ${name}")
        }

        init {
            Log.e(TAG, "Second ")
        }
    }


    /**
     * 继承
     */
    inner class Man:Person{

        constructor(name: String,age: Int): super(name,age){

        }

        override fun show() {
            super.show()
        }
    }

    /**
     * 接口
     *
     */
    interface  A{
        fun foo(){
            val e = Log.e(TAG, "A")
        }

        fun bar()
    }

    interface B{
        fun foo(){
            Log.e(TAG,"B")}
        fun bar(){
            Log.e(TAG,"bar")}
    }

    inner class C:A{
        override fun bar() {
            Log.e(TAG," override bar")
        }
    }

    inner class D:A,B{
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }
}
