package com.meridiane.teacher.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.ItemStatisticBinding
import com.meridiane.teacher.domain.models.Lesson

class AdapterFuture(private var onItemClicked: ((lesson: Lesson) -> Unit)) :
    PagingDataAdapter<Lesson, AdapterFuture.LessonViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder =
        LessonViewHolder(
            ItemStatisticBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class LessonViewHolder(private val itemBinding: ItemStatisticBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(lesson: Lesson?) = with(itemBinding) {
            if (lesson != null) {

                cardView.background.setTint(ContextCompat.getColor(root.context,R.color.purple_700))

                textTittleLesson.text =
                    if (lesson.tittle!!.length > 20) lesson.tittle!!.substring(0, 24)
                    else lesson.tittle

                textData.text = lesson.lesson_date

                textLike.text =
                    if(lesson.description!!.length > 17) lesson.tittle!!.substring(0, 17)
                    else lesson.tittle

                itemView.setOnClickListener {
                    if (lesson.like == null) onItemClicked(lesson)
                }
            }
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Lesson>() {

        override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean =
            oldItem == newItem
    }

}