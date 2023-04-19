package com.meridiane.betsson

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.meridiane.betsson.adapters.FavoritesAdapter
import com.meridiane.betsson.databinding.FragmentFavoritesBinding
import com.meridiane.betsson.interfaces.FragmentClose
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

class FavoritesFragment(private val fClose: FragmentClose, var int:Int) : Fragment() {

    private val list = mutableListOf<MatchesType>()

    private lateinit var binding: FragmentFavoritesBinding

    private val pref: SharedPreferences by lazy {
        requireContext().getSharedPreferences("TABLES", Context.MODE_PRIVATE)
    }
    private var srtSavePreferences = ""

    private lateinit var adapter: FavoritesAdapter

    private var count = 0

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        count = if (int == 1) int
        else 0

        binding.imageDrawer.setOnClickListener {
            fClose.fragmentClose()
        }
        mainViewModel.allType.observe(viewLifecycleOwner) {
            srtSavePreferences = pref.getString("String","Football").toString()
            if (count != 1) {
                when (srtSavePreferences) {
                    "Football" -> listFavoritesFootball(it)
                    "Basketball" -> listFavoritesBasketBall(it)
                    "Hockey" -> listFavoritesHockey(it)
                    else -> listFavoritesFootball(it)
                }
            } else listFavoritesFootball(it)
            adapter = FavoritesAdapter(list,fClose)
            binding.bottomNavigationViewFavorites.setOnItemSelectedListener { its ->
                when(its.itemId) {
                    R.id.ad_football ->{
                        srtSavePreferences = "Football"
                        listFavoritesFootball(it)
                        binding.rcAdapterFavorites.adapter = FavoritesAdapter(list,fClose)
                    }
                    R.id.ad_basketball ->{
                        srtSavePreferences = "Basketball"
                        listFavoritesBasketBall(it)
                        binding.rcAdapterFavorites.adapter = FavoritesAdapter(list,fClose)
                    }
                    R.id.ad_hockey ->{
                        srtSavePreferences = "Hockey"
                        listFavoritesHockey(it)
                        binding.rcAdapterFavorites.adapter = FavoritesAdapter(list,fClose)
                    }
                }
                savePref(srtSavePreferences)
                true
            }
            binding.rcAdapterFavorites.layoutManager = LinearLayoutManager(context)
            binding.rcAdapterFavorites.adapter = FavoritesAdapter(list,fClose)
        }
    }

    private fun listFavoritesFootball(listArray: List<MatchesType>){
        int = 0
        count = int
        list.clear()
        for (i in listArray) if (i.typede == "Football" && i.booleean) list.add(i)
    }

    private fun listFavoritesBasketBall(listArray: List<MatchesType>) {
        int = 0
        count = int
        list.clear()
        for (i in listArray) if (i.typede == "Basketball" && i.booleean) list.add(i)
    }

    private fun listFavoritesHockey(listArray: List<MatchesType>) {
        int = 0
        count = int
        list.clear()
        for (i in listArray) if (i.typede == "Hockey" && i.booleean) list.add(i)
    }

    private fun savePref(str: String){
        val editor = pref.edit()
        editor?.putString("String",str)
        editor?.apply()
    }

    override fun onPause() {
        super.onPause()
        int = 0
    }
}