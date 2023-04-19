package com.meridiane.betsson.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meridiane.betsson.*
import com.meridiane.betsson.databinding.RcNewsBinding
import com.meridiane.betsson.details.*
import com.meridiane.betsson.interfaces.FragmentClose

import com.meridiane.betsson.model.MatchesType

class NewsAdapter(private val onItemClickListener: OnItemClickListener, private val fragmentClose: FragmentClose)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var listArray: List<MatchesType> = ArrayList()


    @SuppressLint("NotifyDataSetChanged")
    fun setNewList( footballList: List<MatchesType>
    ) {
        listArray = footballList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(RcNewsBinding
    .inflate(LayoutInflater.from(parent.context), parent, false), parent.context,fragmentClose)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listArray[position])
        holder.itemView.tag = listArray[position]
    }

    override fun getItemCount(): Int = listArray.size

    inner class ViewHolder(private val binding: RcNewsBinding, val context: Context,
                           private val fragmentClose: FragmentClose) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageViewNews.setOnClickListener {
                onItemClickListener.onItemClick(itemView.tag as MatchesType)
            }
        }

        fun bind(listItem: MatchesType) = with(binding) {
            textTitleNews.text = listItem.titler
            imNews.setImageResource(listItem.im1)

            imageViewNews.setImageResource(
                if (listItem.booleean) R.drawable.ic_favorites_true
                else R.drawable.ic_favorites_false
            )

            itemView.setOnClickListener {
                when (listItem.im1) {
                    R.drawable.example_news1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsFragment())
                            .commit()
                    }
                    R.drawable.example_news1_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetalsNewsFragment2()).commit()
                    }
                    R.drawable.example_news1_2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsFragment3()).commit()
                    }
                    R.drawable.example_news2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment1()).commit()
                    }
                    R.drawable.example_news2_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment2()).commit()
                    }
                    R.drawable.img -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment3()).commit()
                    }
                    R.drawable.example_news3 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment1()).commit()
                    }
                    R.drawable.example_news3_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment2()).commit()
                    }
                    R.drawable.example_news3_2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout,NewsFragment(fragmentClose,0),"NewsFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment3()).commit()
                    }
                }
            }
            }
        }
    interface OnItemClickListener {
        fun onItemClick(item: MatchesType)
    }

    }
