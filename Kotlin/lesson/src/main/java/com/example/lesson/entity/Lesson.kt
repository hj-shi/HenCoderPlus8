package com.example.lesson.entity

// 主构造器中使用 var 修饰参数 能同时生成对应属性
class Lesson(var date: String?, var content: String, var state: State) {
    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },

        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },

        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String
    }

}