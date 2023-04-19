package com.meridiane.betsson.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meridiane.betsson.*
import com.meridiane.betsson.details.*
import com.meridiane.betsson.interfaces.FragmentClose
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

private const val NewsHolderVar = 1
private const val MatchHolder = 0

class FavoritesAdapter(private val listArray: List<MatchesType>, private val fragmentClose: FragmentClose)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NewsHolderVar) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_news, parent, false)
            NewsHolder(view, parent.context,fragmentClose)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rc_football_matches, parent, false)
            MatchHolder(view, parent.context)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == NewsHolderVar) (holder as NewsHolder).bind(listArray[position])
        else (holder as MatchHolder).bind(listArray[position])
    }

    override fun getItemCount(): Int = listArray.size

    override fun getItemViewType(position: Int): Int = if (listArray[position].im2 == 1) {
            NewsHolderVar
        } else {
            MatchHolder
        }

    class MatchHolder(itemView: View, context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private val mainViewModel: MainViewModel by lazy {
            MainViewModel((context.applicationContext as MainApp).database)
        }

        private val textTitleMatchesFootball = itemView.findViewById<TextView>(R.id.textTitleMatchesFootball)
        private val textDateMatchesFootball = itemView.findViewById<TextView>(R.id.textDateMatchesFootball)
        private val textImMatchesFootball1 = itemView.findViewById<TextView>(R.id.textImMatchesFootball1)
        private val textImMatchesFootball2 = itemView.findViewById<TextView>(R.id.textImMatchesFootball2)
        private val imMatcheFootball1 = itemView.findViewById<ImageView>(R.id.imMatcheFootball1)
        private val imMatchesFootball2 = itemView.findViewById<ImageView>(R.id.imMatchesFootball2)
        private val btCoefficienFotball1 = itemView.findViewById<TextView>(R.id.bt_coefficien_fotball1)
        private val btCoefficienFotballs = itemView.findViewById<TextView>(R.id.bt_coefficien_fotballs)
        private val btCoefficienFotballs2 = itemView.findViewById<TextView>(R.id.bt_coefficien_fotballs2)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView2)

        fun bind(listItem: MatchesType) {
            textTitleMatchesFootball.text = listItem.titler
            textDateMatchesFootball.text = listItem.dater
            textImMatchesFootball1.text = listItem.strIm1
            textImMatchesFootball2.text = listItem.strIm2
            imMatcheFootball1.setImageResource(listItem.im1)
            imMatchesFootball2.setImageResource(listItem.im2)
            btCoefficienFotball1.text = listItem.intew1
            btCoefficienFotballs.text = listItem.intew2
            btCoefficienFotballs2.text = listItem.intew3
            imageView.setImageResource(R.drawable.ic_favorites_true)

            imageView.setOnClickListener {
                mainViewModel.deleteEntity(listItem.id!!)
                mainViewModel.insertType(MatchesType(null,listItem.titler,listItem.dater,listItem.strIm1,listItem.strIm2,
                    listItem.im1,listItem.im2,listItem.intew1,listItem.intew2,listItem.intew3,false,listItem.typede))

            }
        }
    }

    class NewsHolder(itemView: View, val context: Context, private val fragmentClose: FragmentClose) :
        RecyclerView.ViewHolder(itemView) {

        private val mainViewModel: MainViewModel by lazy {
            MainViewModel((context.applicationContext as MainApp).database)
        }

        private val imNews = itemView.findViewById<ImageView>(R.id.imNews)
        private val imageViewNews = itemView.findViewById<ImageView>(R.id.imageViewNews)
        private val textTitleNews = itemView.findViewById<TextView>(R.id.textTitleNews)

        fun bind(listItem: MatchesType) {
            imNews.setImageResource(listItem.im1)
            imageViewNews.setImageResource(R.drawable.ic_favorites_true)
            textTitleNews.text = listItem.titler

            imageViewNews.setOnClickListener {
                mainViewModel.deleteEntity(listItem.id!!)
            }

            itemView.setOnClickListener {
                when (listItem.im1) {
                    R.drawable.example_news1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsFragment()).commit()
                    }
                    R.drawable.example_news1_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetalsNewsFragment2()).commit()
                    }
                    R.drawable.example_news1_2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsFragment3()).commit()
                    }
                    R.drawable.example_news2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment1()).commit()
                    }
                    R.drawable.example_news2_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment2()).commit()
                    }
                    R.drawable.img -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsNewsBasketFragment3()).commit()
                    }
                    R.drawable.example_news3 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment1()).commit()
                    }
                    R.drawable.example_news3_1 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment2()).commit()
                    }
                    R.drawable.example_news3_2 -> {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .add(R.id.frameLayout, FavoritesFragment(fragmentClose,0),"FavoritesFragment")
                            .addToBackStack(null)
                            .replace(R.id.frameLayout, DetailsHockeyFragment3()).commit()
                    }
                }
            }
        }
    }
}



