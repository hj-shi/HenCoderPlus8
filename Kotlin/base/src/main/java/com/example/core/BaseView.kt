package com.example.core

// 接口中声明抽象属性
interface BaseView<T> {
    val presenter: T
}