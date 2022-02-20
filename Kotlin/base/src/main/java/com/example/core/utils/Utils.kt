package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

fun Float.dp2px(): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this,
    Resources.getSystem().displayMetrics
)

class Utils {
    companion object {
        // 默认参数，Java侧生成2个重载函数
        @JvmStatic
        @JvmOverloads
        fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(BaseApplication.currentApplication(), string, duration).show()
        }
    }
}