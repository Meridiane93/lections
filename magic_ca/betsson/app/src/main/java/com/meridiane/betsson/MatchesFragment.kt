package com.meridiane.betsson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.meridiane.betsson.adapters.MatchesAdapter
import com.meridiane.betsson.databinding.FragmentMatchesBinding
import com.meridiane.betsson.interfaces.FragmentClose
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

class MatchesFragment(private val fClose: FragmentClose, private var listForMain: ArrayList<MatchesType>) : Fragment() {

    private lateinit var binding: FragmentMatchesBinding

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(MainApp.INSTANCE.database)
    }

    private val listMatchesFragment = ArrayList<MatchesType>()

    private val listFootballMatches = ArrayList<MatchesType>()
    private val listBasketballMatches = ArrayList<MatchesType>()
    private val listHockeyMatches = ArrayList<MatchesType>()

    private val adapter = MatchesAdapter(listMatchesFragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        binding = FragmentMatchesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageDrawer.setOnClickListener {
            fClose.fragmentClose()
        }

        binding.rcAdapter.layoutManager = LinearLayoutManager(context)
        binding.bottomNavigationViewMatches.setOnItemSelectedListener {
            when(it.itemId) {

                R.id.ad_football -> binding.rcAdapter.adapter = MatchesAdapter(listFootballMatches)
                R.id.ad_basketball ->  binding.rcAdapter.adapter = MatchesAdapter(listBasketballMatches)
                R.id.ad_hockey -> binding.rcAdapter.adapter = MatchesAdapter(listHockeyMatches)

            }
            true
        }
        addLists()

        val listObserver = ArrayList<MatchesType>()
        if (listObserver.size != 0) listForMain = listObserver

        var count = 0
        for (i in listForMain) {
            for (a in listFootballMatches) {
                if (i.im1 == a.im1) {
                    listFootballMatches[count].booleean = i.booleean
                    listFootballMatches[count].id = i.id
                }
                count++
            }
            count = 0
        }

            var check = false
            val matchesForMainAct = ArrayList<MatchesType>()
            for (matches in listForMain) { if (matches.typede == "Football") matchesForMainAct.add(matches) }

        for ((countListFootballMatch, it) in listFootballMatches.withIndex()){
               for (a in matchesForMainAct) {
                   if (it.booleean && a.im1 == it.im1) check = true
                   count++
               }
                if (!check) listFootballMatches[countListFootballMatch].booleean = false
            }

        binding.rcAdapter.adapter = MatchesAdapter(listFootballMatches)

        mainViewModel.allType.observe(viewLifecycleOwner) { list ->

            for (i: MatchesType in list) {
                listObserver.add(i)
            }
            adapter.updateAdapter(listObserver)
        }
    }

    // оставил вшивать здесь так как сразу список сортирую на 3 категории
    private fun addLists(){
        // example array for rcView
        listFootballMatches.add(MatchesType(null,"Premier League","12 November 2022 \n 14:30","Manchester \n City",
            "Brendford",R.drawable.example_notes1,R.drawable.example_notes1_1,"1.14","8.50","17.10",false,"Football"))
        listFootballMatches.add(MatchesType(null,"Premier League","12 November 2022 \n 17:00", "Liverpool","Southampton",R.drawable.example_notes2,
            R.drawable.example_notes2_2,"1.26","6.50","10.00",false,"Football"))

        listFootballMatches.add(MatchesType(null,"Premier League","12 November 2022 \n 14:30", "Newcastle \n United","Chelsea",R.drawable.example_notes3,
            R.drawable.example_notes3_1,"2.31","3.48","3.04",false,"Football"))


        listBasketballMatches.add(MatchesType(null,"NBA","08 November 2022 \n 03:45","Chicago Bulls","Toronto Raptors",R.drawable.example_basket1
            ,R.drawable.example_basket1_2,"1.64","-","2.28",false,"Basketball"))

        listBasketballMatches.add(MatchesType(null,"NBA","08 November 2022 \n 04:00","Memphis \n Grizzlies","Boston \n Celtics",
            R.drawable.example_basket2,R.drawable.example_basket2_1,"2.13","-","1.75",false,"Basketball"))

        listBasketballMatches.add(MatchesType(null,"NBA","08 November 2022 \n 04:45","Dallas \n Mavericks","Brooklyn Nets",
            R.drawable.example_basket3,R.drawable.img_1,"1.38","-","3.17",false,"Basketball"))


        listHockeyMatches.add(MatchesType(null,"NHL","09 November 2022 \n 02:30", "Tampa Bay \n Lightning","Edmonton \n Oilerss"
            ,R.drawable.example_fockey1,R.drawable.example_hockey1_1,"1.94","4.50","3.30",false,"Hockey"))

        listHockeyMatches.add(MatchesType(null,"NHL","09 November 2022 \n 03:00","Winnipeg \n Jets", "Dallas Stars"
            ,R.drawable.example_hockey2,R.drawable.example_hockey2_1,"2.37","4.20","2.65",false,"Hockey"))

        listHockeyMatches.add(MatchesType(null,"NHL","09 November 2022 \n 05:30","Los Angeles \n Kings", "Minnesota \n Wild"
            ,R.drawable.example_hockey3,R.drawable.example_hockey3_1,"2.60","4.20","2.38",false,"Hockey"))

        listMatchesFragment.addAll(listFootballMatches)
        listMatchesFragment.addAll(listBasketballMatches)
        listMatchesFragment.addAll(listHockeyMatches)
    }
}