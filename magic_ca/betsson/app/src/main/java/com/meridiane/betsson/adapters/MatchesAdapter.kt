package com.meridiane.betsson.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meridiane.betsson.MainApp
import com.meridiane.betsson.R
import com.meridiane.betsson.databinding.RcFootballMatchesBinding
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

class MatchesAdapter(private var listArrayMatches: ArrayList<MatchesType>)
    : RecyclerView.Adapter<MatchesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RcFootballMatchesBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listArrayMatches[position])

    override fun getItemCount(): Int = listArrayMatches.size

    class ViewHolder(private val binding: RcFootballMatchesBinding,val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val mainViewModel: MainViewModel by lazy {
            MainViewModel((context.applicationContext as MainApp).database)
        }

        fun bind(listItem:MatchesType)= with(binding){

            textTitleMatchesFootball.text = listItem.titler
            textDateMatchesFootball.text = listItem.dater
            textImMatchesFootball1.text = listItem.strIm1
            textImMatchesFootball2.text = listItem.strIm2
            imMatcheFootball1.setImageResource(listItem.im1)
            listItem.im2.let { imMatchesFootball2.setImageResource(it) }
            btCoefficienFotball1.text = listItem.intew1
            btCoefficienFotballs.text = listItem.intew2
            btCoefficienFotballs2.text = listItem.intew3

            imageView2.setImageResource(if(listItem.booleean) R.drawable.ic_favorites_true
                                        else R.drawable.ic_favorites_false)

            imageView2.setOnClickListener {
                if (listItem.booleean) {
                    imageView2.setImageResource(R.drawable.ic_favorites_false)
                    mainViewModel.deleteEntity(listItem.id!!)
                    mainViewModel.insertType(MatchesType(null,listItem.titler,listItem.dater,listItem.strIm1,listItem.strIm2,
                        listItem.im1,listItem.im2,listItem.intew1,listItem.intew2,listItem.intew3,false,listItem.typede))

                }else {
                    imageView2.setImageResource(R.drawable.ic_favorites_true)
                    mainViewModel.insertType(MatchesType(listItem.id,listItem.titler,listItem.dater,listItem.strIm1,listItem.strIm2,
                        listItem.im1,listItem.im2,listItem.intew1,listItem.intew2,listItem.intew3,true,listItem.typede))
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItem: ArrayList<MatchesType>){
        var listA = 0
            for (i in listItem) {
                for (a in listArrayMatches){
                    if (i.im1 == a.im1) {
                        listArrayMatches[listA].booleean = i.booleean
                        listArrayMatches[listA].id = i.id
                    }
                    listA++
                }
                listA = 0
            }
        notifyDataSetChanged()
    }
}