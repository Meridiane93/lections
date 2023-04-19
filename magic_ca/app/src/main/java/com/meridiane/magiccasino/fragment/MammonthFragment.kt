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
import com.meridiane.magiccasino.databinding.FragmentMammonthBinding
import com.meridiane.magiccasino.imageViewScrolling.IEventEnd
import kotlinx.android.synthetic.main.fragment_games.*

import kotlin.random.Random

class MammonthFragment : Fragment(),IEventEnd{

    private var countDown = 0 // счётчик вращений

    private var _binding: FragmentMammonthBinding? = null

    private val binding get() = _binding!!

    var bet = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMammonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            image1.setEventEnd(this@MammonthFragment)
            image2.setEventEnd(this@MammonthFragment)
            image3.setEventEnd(this@MammonthFragment)

            image4.setEventEnd2(this@MammonthFragment)
            image5.setEventEnd2(this@MammonthFragment)
            image6.setEventEnd2(this@MammonthFragment)

            image7.setEventEnd3(this@MammonthFragment)
            image8.setEventEnd3(this@MammonthFragment)
            image9.setEventEnd3(this@MammonthFragment)

            imExit.setOnClickListener {
                MAIN.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,GamesFragment())
                    .addToBackStack(null)
                    .commit()
            }

            imPlus.setOnClickListener {
                bet++
                txBet.text = bet.toString()
            }
            imMinus.setOnClickListener {
                bet--
                txBet.text = bet.toString()
            }

            binding.txBalance.text = Common.SCORE.toString()

            imageStartSpin.setOnClickListener {

                if (Common.SCORE >= bet) {
                    val rotation = Random.nextInt(15 - 5 + 1) + 5
                    val rotation2 = Random.nextInt(15 - 5 + 1) + 5
                    val rotation3 = Random.nextInt(15 - 5 + 1) + 5

                    val (first, second, third) = (0..8).shuffled()
                    val (first2, second2, third2) = (0..8).shuffled()
                    val (first3, second3, third3) = (0..8).shuffled()

                    image1.setValueRandom(first, rotation)
                    image2.setValueRandom(first2, rotation2)
                    image3.setValueRandom(first3, rotation3)
                    image4.setValueRandom2(second, rotation)
                    image5.setValueRandom2(second2, rotation2)
                    image6.setValueRandom2(second3, rotation3)
                    image7.setValueRandom3(third, rotation)
                    image8.setValueRandom3(third2, rotation2)
                    image9.setValueRandom3(third3, rotation3)

                    Common.SCORE -= bet
                    txBalance.text = Common.SCORE.toString()
                    imageStartSpin.isClickable = false
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

                winCount = when (image5.value) {
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
                winCount += txBet.text.toString().toInt()

                if (image4.value == image5.value && image5.value == image6.value) {
                    winCount *= 3
                    Common.SCORE += winCount
                    txWin.text = "$winCount"
                } else if (image1.value == image5.value && image9.value == image5.value ||
                    image3.value == image5.value && image7.value == image5.value) {
                    winCount *= 4
                    Common.SCORE += winCount
                    txWin.text = "$winCount"
                } else txWin.text = "0"

                txBalance.text = Common.SCORE.toString()
                imageStartSpin.isClickable = true
            }
        }
    }
    override fun eventEnd() { check() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
    // для беспорядочного рандома можно так
    //binding.imageStartSpin.setOnClickListener {
        //if (Common.SCORE >= bet) {
        //    image1.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
         //   image2.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
          //  image3.setValueRandom(Random.nextInt(8),
          //      Random.nextInt(15-5+1)+5)
         //   image4.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
          //  image5.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
          //  image6.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
         //   image7.setValueRandom(Random.nextInt(8),
          //      Random.nextInt(15-5+1)+5)
         //   image8.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)
         //   image9.setValueRandom(Random.nextInt(8),
         //       Random.nextInt(15-5+1)+5)

         //   Common.SCORE -= bet
       //     binding.txBalance.text = Common.SCORE.toString()
      //  }
//binding.imageStartSpin.setOnClickListener {
  //  if (Common.SCORE >= bet) {
//
   //     val nextI = Random.nextInt(8)
    //    val nextI2 = Random.nextInt(8)
     //   val nextI3 = Random.nextInt(8)
      //  val rotation = Random.nextInt(15-5+1)+5
      //  val rotation2 = Random.nextInt(15-5+1)+5
     //   val rotation3 = Random.nextInt(15-5+1)+5

    //    image1.setValueRandom(nextI-2, rotation)
    //    image2.setValueRandom(nextI2-2, rotation2)
    //    image3.setValueRandom(nextI3-2, rotation3)
     //   image4.setValueRandom(nextI-1, rotation)
     //   image5.setValueRandom(nextI2-1, rotation2)
     //   image6.setValueRandom(nextI3-1, rotation3)
     //   image7.setValueRandom(nextI, rotation)
     //   image8.setValueRandom(nextI2, rotation2)
     //   image9.setValueRandom(nextI3, rotation3)

     //   Common.SCORE -= bet
    //    binding.txBalance.text = Common.SCORE.toString()
   // }