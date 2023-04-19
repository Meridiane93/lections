package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.meridiane.magiccasino.Common
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.databinding.FragmentMammonthBinding
import com.meridiane.magiccasino.imageViewScrolling.IEventEnd
import kotlinx.android.synthetic.main.fragment_mammonth.*

import kotlin.random.Random

class MammonthFragment : Fragment(),IEventEnd {

    private lateinit var binding : FragmentMammonthBinding
    var bet = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMammonthBinding.inflate(layoutInflater,container,false)

        binding.image1.setEventEnd(this)
        binding.image2.setEventEnd(this)
        binding.image3.setEventEnd(this)
        binding.image4.setEventEnd2(this)
        binding.image5.setEventEnd2(this)
        binding.image6.setEventEnd2(this)
        binding.image7.setEventEnd3(this)
        binding.image8.setEventEnd3(this)
        binding.image9.setEventEnd3(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imExit.setOnClickListener {
            MAIN.finish()
        }

        binding.imPlus.setOnClickListener {
            bet++
            binding.txBet.text = bet.toString()
        }
        binding.imMinus.setOnClickListener {
            bet--
            binding.txBet.text = bet.toString()
        }

        binding.imageStartSpin.setOnClickListener {

            if (Common.SCORE >= bet) {

            val nextI = Random.nextInt(8)
            val nextI2 = Random.nextInt(8)
            val nextI3 = Random.nextInt(8)
            val rotation = Random.nextInt(15-5+1)+5
            val rotation2 = Random.nextInt(15-5+1)+5
            val rotation3 = Random.nextInt(15-5+1)+5

            image1.setValueRandom(nextI+1, rotation)
            image2.setValueRandom(nextI2+1, rotation2)
            image3.setValueRandom(nextI3+1, rotation3)
            image4.setValueRandom2(nextI, rotation)
            image5.setValueRandom2(nextI2, rotation2)
            image6.setValueRandom2(nextI3, rotation3)
            image7.setValueRandom3(nextI+2, rotation)
            image8.setValueRandom3(nextI2+2, rotation2)
            image9.setValueRandom3(nextI3+2, rotation3)

               Common.SCORE -= bet
                 binding.txBalance.text = Common.SCORE.toString()
              } else Toast.makeText(MAIN,"У вас закончился баланс", Toast.LENGTH_SHORT).show()
        }
    }

    override fun eventEnd(result: Int, count: Int) = with(binding) {

        if (image4.value == image5.value && image5.value == image6.value){
            Toast.makeText(MAIN,"Вы выиграли большой приз",Toast.LENGTH_SHORT).show()
            Common.SCORE += 300
            tx_win.text = "300"
        }
        else if (image4.value == image5.value ||
            image5.value == image6.value ||
            image4.value == image6.value) {
            Toast.makeText(MAIN,"Вы выиграли малый приз",Toast.LENGTH_SHORT).show()
            Common.SCORE += 100
            tx_win.text = "100"
        }else {
            Toast.makeText(MAIN,"Выигрыша нет, попробуй ещё",Toast.LENGTH_SHORT).show()
            Common.SCORE -= tx_bet.toString().toInt()
        }
        tx_balance.text = Common.SCORE.toString()
        tx_win.text = "0"
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