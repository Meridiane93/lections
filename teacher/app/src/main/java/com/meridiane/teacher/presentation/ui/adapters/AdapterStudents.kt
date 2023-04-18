package com.meridiane.teacher.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.meridiane.teacher.databinding.ItemStudentBinding
import com.meridiane.teacher.domain.models.Student

class AdapterStudents(private var onItemClicked: ((student: Student?) -> Unit)) :
    PagingDataAdapter<Student, AdapterStudents.StudentViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
        StudentViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentViewHolder(
        private val itemBinding: ItemStudentBinding,
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(student: Student?) = with(itemBinding) {

            textView.text =
                if (student?.full_name!!.length > 15) student.full_name!!.substring(0, 15)
                else student.full_name

            textTelegram.text =
                if (("Telegram: " + student.telegram).length > 15)
                        ("Telegram: " + student.telegram).substring(0, 15)
                else "Telegram: " + student.telegram

            textNumber.text = if (("Phone: " + student.telephone_number).length > 15)
                ("Phone: " + student.telephone_number).substring(0, 15)
            else "Phone: " + student.telephone_number

            imagePhoto.load(student.image)

            itemView.setOnClickListener {
                onItemClicked(student)
            }
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Student>() {

        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem == newItem
    }

}