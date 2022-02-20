package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list: List<Lesson> = ArrayList<Lesson>()

    fun updateAndNotify( list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position]);
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class LessonViewHolder: BaseViewHolder {
        constructor(@NonNull itemView: View): super(itemView)

        companion object {
            @NonNull
            fun onCreate( parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_lesson, parent, false))
            }
        }

        fun onBind( lesson: Lesson) {
            var date = lesson.date
            if (date == null) {
                date = "日期待定";
            }
            setText(R.id.tv_date, date);

            setText(R.id.tv_content, lesson.content)

            val  state: Lesson.State = lesson.state
            setText(R.id.tv_state, state.stateName());
            var colorRes = when (state) {
                Lesson.State.PLAYBACK -> R.color.playback
                Lesson.State.LIVE -> R.color.live
                Lesson.State.WAIT -> R.color.wait
            }
            val backgroundColor = itemView.context.getColor(colorRes)
            getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
        }

    }
}