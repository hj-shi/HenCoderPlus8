package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.Utils
import com.example.lesson.LessonActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val usernameKey = "username"
    private val passwordKey = "password"

    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))

        val btn_login = findViewById<Button>(R.id.btn_login);
        val img_code = findViewById<CodeView>(R.id.code_view);
        btn_login.setOnClickListener(this);
        img_code.setOnClickListener(this);
    }


    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.getText().toString()
        val password = et_password.getText().toString()
        val code = et_code.getText().toString()

        val user = User(username, password, code)

        // 函数嵌套
        fun verify(): Boolean {
            val userName = user.username
            if (userName != null && userName.length < 4) {
                Utils.toast("用户名不合法")
                return false
            }
            val passWord = user.password
            if (passWord != null && passWord.length < 4) {
                Utils.toast("密码不合法")
                return false
            }
            return true
        }

        // 调用嵌套函数，有创建临时对象的开销
        if (verify()) {
            CacheUtils.save(usernameKey, username);
            CacheUtils.save(passwordKey, password);
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

}