package com.meridiane.magiccasino.imageViewScrolling

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.R
import kotlinx.android.synthetic.main.image_view_scrolling2.view.*

class ImageViewScrolling2: FrameLayout {

    internal lateinit var eventEnd2: IEventEnd
    internal var last_result2 = 0
    internal var oldValue2 = 0


    companion object{
        private const val ANIMATION_DURATION = 150
    }

    val value: Int
        get() = Integer.parseInt(nextImage2.tag.toString())


    constructor(context: Context,attrs:AttributeSet): super(context,attrs){ init(MAIN) }
    constructor(context: Context): super(context) { init(MAIN) }

    fun setEventEnd2(eventEnd2: IEventEnd){
        this.eventEnd2 = eventEnd2
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling2,this)

        nextImage2.translationY = height.toFloat()
    }

    fun setValueRandom2(image: Int, num_rotate: Int){
        currentImage2.visibility = View.VISIBLE
        currentImage2.animate()
            .translationY((-height).toFloat())
            .setDuration(ANIMATION_DURATION.toLong()).start()

        nextImage2.translationY = nextImage2.height.toFloat()

        nextImage2.animate().translationY(0f).setDuration(ANIMATION_DURATION.toLong())
            .setListener(object : Animator.AnimatorListener { // прослушивание анимации у объекта
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                        setImage(currentImage2,oldValue2%8)
                    currentImage2.translationY = 0f

                        if(oldValue2 != num_rotate){
                            setValueRandom2(image,num_rotate)
                            oldValue2++
                        } else {
                            last_result2 = 0
                            oldValue2 = 0
                            setImage(nextImage2,image)
                            currentImage2.visibility = View.GONE
                            eventEnd2.eventEnd()
                        }
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}

            }).start()
    }

    private fun setImage(img: ImageView, value: Int) {
        when (value) {
            Util.volf -> img.setImageResource(R.drawable.m_roll_volk)
            Util.vulkan -> img.setImageResource(R.drawable.m_roll_vulkan)
            Util.tigree -> img.setImageResource(R.drawable.m_roll_tigree)
            Util.mammoth -> img.setImageResource(R.drawable.m_roll_mammonth)
            Util.emerald -> img.setImageResource(R.drawable.m_roll_izumrude)
            Util.bird -> img.setImageResource(R.drawable.m_roll_ptiza)
            Util.pig -> img.setImageResource(R.drawable.m_roll_svin)
            Util.stone -> img.setImageResource(R.drawable.m_roll_kamen)
            Util.bear -> img.setImageResource(R.drawable.m_roll_bear)
        }

        img.tag = value
        last_result2 = value
    }
}