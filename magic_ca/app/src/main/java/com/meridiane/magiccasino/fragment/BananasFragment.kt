package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.meridiane.magiccasino.Common
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.R
import com.meridiane.magiccasino.databinding.FragmentBananasBinding
import com.meridiane.magiccasino.imageBananasScrolling.IEventBananasEnd
import kotlinx.android.synthetic.main.fragment_games.*
import kotlin.random.Random

class BananasFragment : Fragment(),IEventBananasEnd {

    private val binding get() = _binding!!
    private var _binding: FragmentBananasBinding? = null
    private var countDown = 0 // счётчик вращений
    var bet = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBananasBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            imageView1.setEventEnd(this@BananasFragment)
            imageView2.setEventEnd(this@BananasFragment)
            imageView3.setEventEnd(this@BananasFragment)

            imageView4.setEventEnd2(this@BananasFragment)
            imageView5.setEventEnd2(this@BananasFragment)
            imageView6.setEventEnd2(this@BananasFragment)

            imageView7.setEventEnd3(this@BananasFragment)
            imageView8.setEventEnd3(this@BananasFragment)
            imageView9.setEventEnd3(this@BananasFragment)

            exitBanan.setOnClickListener {
                MAIN.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,GamesFragment())
                    .addToBackStack(null)
                    .commit()
            }

            imageView11.setOnClickListener {
                bet++
                txBetBanan.text = bet.toString()
            }
            minusBanan.setOnClickListener {
                bet--
                txBetBanan.text = bet.toString()
            }

            binding.txBalanceBanan.text = Common.SCORE.toString()

            btSpinWinBanan.setOnClickListener {

                if (Common.SCORE >= bet) {
                    val rotation = Random.nextInt(15 - 5 + 1) + 5
                    val rotation2 = Random.nextInt(15 - 5 + 1) + 5
                    val rotation3 = Random.nextInt(15 - 5 + 1) + 5

                    val (first, second, third) = (0..8).shuffled()
                    val (first2, second2, third2) = (0..8).shuffled()
                    val (first3, second3, third3) = (0..8).shuffled()

                    imageView1.setValueRandom(first, rotation)
                    imageView2.setValueRandom(first2, rotation2)
                    imageView3.setValueRandom(first3, rotation3)
                    imageView4.setValueRandom2(second, rotation)
                    imageView5.setValueRandom2(second2, rotation2)
                    imageView6.setValueRandom2(second3, rotation3)
                    imageView7.setValueRandom3(third, rotation)
                    imageView8.setValueRandom3(third2, rotation2)
                    imageView9.setValueRandom3(third3, rotation3)

                    Common.SCORE -= bet
                    txBalanceBanan.text = Common.SCORE.toString()
                    btSpinWinBanan.isClickable = false
                } else Toast.makeText(MAIN, "У вас закончился баланс", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun check() {
        with(binding) {

            var winCount = 0

            if (countDown < 8) countDown++
            else {

                countDown = 0

                winCount = when (imageView5.value) {
                    0 -> 5 // слот волк
                    1 -> 15 // слот вулкан
                    2 -> 7 // слот тигр
                    3 -> 9 // слот мамонт
                    4 -> 12 // слот изумруд
                    5 -> 2 // слот птица
                    6 -> 3 // слот свинья
                    7 -> 10 // слот камень
                    else -> 11 // слот медведь
                }
                winCount += txBetBanan.text.toString().toInt()

                if (imageView4.value == imageView5.value && imageView5.value == imageView6.value) {
                    winCount *= 3
                    Common.SCORE += winCount
                    txWinBanan.text = "$winCount"
                } else if (imageView1.value == imageView5.value && imageView9.value == imageView5.value ||
                    imageView3.value == imageView5.value && imageView7.value == imageView5.value
                ) {
                    winCount *= 4
                    Common.SCORE += winCount
                    txWinBanan.text = "$winCount"
                } else txWinBanan.text = "0"

                txBalanceBanan.text = Common.SCORE.toString()
                btSpinWinBanan.isClickable = true
            }
        }
    }

    override fun eventBananasEnd() { check() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}