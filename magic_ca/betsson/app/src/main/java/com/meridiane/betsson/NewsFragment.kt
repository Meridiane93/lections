package com.meridiane.betsson

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.meridiane.betsson.adapters.NewsAdapter
import com.meridiane.betsson.databinding.FragmentNewsBinding
import com.meridiane.betsson.interfaces.FragmentClose
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

class NewsFragment(private val fClose: FragmentClose,var int:Int) : Fragment(),
    NewsAdapter.OnItemClickListener {

    private val pref: SharedPreferences by lazy {
        requireContext().getSharedPreferences("TABLES", Context.MODE_PRIVATE)
    }

    private lateinit var binding: FragmentNewsBinding

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(MainApp.INSTANCE.database)
    }

        private var srtSavePreferences = ""
        private val listNewsFragment: MutableList<MatchesType> = ArrayList()

        private val listFootballNews = ArrayList<MatchesType>()
        private val listBasketballNews = ArrayList<MatchesType>()
        private val listHockeyNews = ArrayList<MatchesType>()
        private var counter = 0

        private val adapter: NewsAdapter = NewsAdapter(this,fClose)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        save()
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View {
            binding = FragmentNewsBinding.inflate(layoutInflater)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            counter = if(int == 1) int else 0

            mainViewModel.allType.observe(viewLifecycleOwner) { list ->
                for (item: MatchesType in list) {
                    if (item.im2 == 1) listNewsFragment.add(item)
                }
                listNews(listNewsFragment as ArrayList<MatchesType>,listFootballNews,"Football")
                listNews(listNewsFragment ,listBasketballNews,"Basketball")
                listNews(listNewsFragment ,listHockeyNews,"Hockey")
                srtSavePreferences = pref.getString("String","Football").toString()
                val listr = if (counter != 1) {
                     when (srtSavePreferences) {
                        "Football" -> listFootballNews
                        "Basketball" -> listBasketballNews
                        "Hockey" -> listHockeyNews
                        else -> listFootballNews
                    }
                } else listFootballNews
                adapter.setNewList(listr)
            }

            binding.imageDrawer.setOnClickListener {
                fClose.fragmentClose()
            }

            binding.rcAdapterNews.layoutManager = LinearLayoutManager(context)
            binding.rcAdapterNews.adapter = adapter
            srtSavePreferences = pref.getString("String","Football").toString()
            val listr = when(srtSavePreferences) {
                "Football" -> listFootballNews
                "Basketball" -> listBasketballNews
                "Hockey" -> listHockeyNews
                else -> listFootballNews
            }
            adapter.setNewList(listr)
            binding.bottomNavigationViewNews.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.ad_football ->{
                        srtSavePreferences = "Football"
                        adapter.setNewList(listFootballNews)
                    }
                    R.id.ad_basketball ->{
                        srtSavePreferences = "Basketball"
                        adapter.setNewList(listBasketballNews)
                    }
                    R.id.ad_hockey -> {
                        srtSavePreferences = "Hockey"
                        adapter.setNewList(listHockeyNews)
                    }
                }
                savePref(srtSavePreferences)
                true
            }
        }

        private fun checkBd(listItem: List<MatchesType>?, str:String):Boolean{
            val boolean = false
            if (listItem == null ) return false
            else {
                for (i in listItem) {
                    if (str == i.intew1)  return true
                }
            }
            return boolean
        }

        private fun listNews(listNews: ArrayList<MatchesType>, listHobby:ArrayList<MatchesType>, str:String){
            var count = 0
            for (i in listNews) {
                for (a in listHobby) {
                    if (i.im1 == a.im1) {
                        listHobby[count].booleean = i.booleean
                    }
                    count++
                }
                count = 0
            }

            var check = false
            val matchesForMainAct = ArrayList<MatchesType>()
            for (matches in listNews) { if (matches.typede == str) matchesForMainAct.add(matches) }

            for ((countListFootballMatch, it) in listHobby.withIndex()){
                for (a in matchesForMainAct) {
                    if (it.booleean && a.im1 == it.im1) check = true
                    count++
                }
                if (!check) listHobby[countListFootballMatch].booleean = false
            }
        }

        private fun save(){
            listFootballNews.add(MatchesType(1001,"Man Utd manager is unhappy that the team constantly filed for Ronaldo in the match against Aston Villa","1","2",
                "3",R.drawable.example_news1,1,"4","1","6",checkBd(listNewsFragment,"4"),"Football"))
            listFootballNews.add(MatchesType(1002,"Coutinho injured and likely to miss 2022 World Cup","1","12",
                "13",R.drawable.example_news1_1,1,"15","1","17",checkBd(listNewsFragment,"15"),"Football"))
            listFootballNews.add(MatchesType(1003,"Salah's brace gives Liverpool Premier League victory over Tottenham Hotspur","1","19",
                "21",R.drawable.example_news1_2,1,"231","1","251",checkBd(listNewsFragment,"231"),"Football"))
            mainViewModel.initDB(listFootballNews)

            listBasketballNews.add(MatchesType(1004,"Milwaukee Lost Giannis Antetokounmpo Defeated Oklahoma, Denver Defeated San Antonio","331","441",
                "431",R.drawable.example_news2,1,"1","1","1",checkBd(listNewsFragment,"1"),"Basketball"))
            listBasketballNews.add(MatchesType(1005,"Brooklyn Flyers defenseman Irving loses contract with major sports equipment manufacturer over anti-Semitic posts","1","1",
                "1",R.drawable.example_news2_1 ,1,"909","1","1",checkBd(listNewsFragment,"909"),"Basketball"))
            listBasketballNews.add(MatchesType(1006,"Brooklyn defeated Washington by 42 points, Golden State lost in the fifth game in a row","1","1",
                "1",R.drawable.img,1,"910","1","1",checkBd(listNewsFragment,"910"),"Basketball"))
            mainViewModel.initDB(listBasketballNews)

            listHockeyNews.add(MatchesType(1007,"“How lucky we are! Thank you, Alex.\" The owner of “Washington” thanked Ovechkin for the record","1","1",
                "1",R.drawable.example_news3,1,"911","1","1",checkBd(listNewsFragment,"911"),"Hockey"))
            listHockeyNews.add(MatchesType(1008,"Brooklyn Flyers defenseman Irving loses contract with major sports equipment manufacturer over anti-Semitic posts","1","1",
                "1",R.drawable.example_news3_1,1,"912","1","1",checkBd(listNewsFragment,"912"),"Hockey"))
            listHockeyNews.add(MatchesType(1009,"Florida Lost to Los Angeles in the NHL Despite Bobrovsky's 34 Saves","1","1",
                "1",R.drawable.example_news3_2,1,"913","1","1",checkBd(listNewsFragment,"913"),"Hockey"))
            mainViewModel.initDB(listHockeyNews)
        }

        private fun savePref(str: String){
            val editor = pref.edit()
            editor?.putString("String",str)
            editor?.apply()
        }

        override fun onItemClick(item: MatchesType) {
            int = 0
            counter = int
            item.booleean = !item.booleean
            mainViewModel.updateNote(item)
        }

    override fun onPause() {
        super.onPause()
        int = 0
    }
    }
