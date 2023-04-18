package com.meridiane.teacher.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.meridiane.teacher.databinding.ItemQuestionBinding
import com.meridiane.teacher.domain.models.Question

class AdapterQuestions(private var onItemClicked: ((student: Question?) -> Unit)) :
    PagingDataAdapter<Question, AdapterQuestions.QuestionViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder =
        QuestionViewHolder(
            ItemQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class QuestionViewHolder(private val itemBinding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(question: Question?) = with(itemBinding) {

            if (question != null) {

                textTittleQuestion.text =
                    if (question.tittle!!.length > 20) question.tittle!!.substring(0, 24)
                    else question.tittle

                textFormsQuestion.text =
                    if (question.questions!!.length > 25) question.questions!!.substring(0, 28)
                    else question.questions

            }

            itemView.setOnClickListener {
                onItemClicked(question)
            }
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Question>() {

        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem == newItem
    }

}