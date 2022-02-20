package com.example.lesson

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LessonPresenter {
    private val LESSON_PATH = "lessons"

    private var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    private var lessons = ArrayList<Lesson>()

    private val type: Type = object : TypeToken<List<Lesson?>?>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<ArrayList<Lesson>> {
            override fun onSuccess(@NonNull  lessons:  ArrayList<Lesson>) {
                this@LessonPresenter.lessons = lessons;
                activity.runOnUiThread {
                    activity.showResult(lessons);
                }
            }

            override fun onFailure(@Nullable message: String) {
                activity.runOnUiThread {
                    Utils.toast(message);

                }
            }
        })
    }
    fun showPlayback() {
        val playbackLessons = ArrayList<Lesson>();
        for (lesson in lessons) {
            if (lesson.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson);
            }
        }
        activity.showResult(playbackLessons);
    }

}