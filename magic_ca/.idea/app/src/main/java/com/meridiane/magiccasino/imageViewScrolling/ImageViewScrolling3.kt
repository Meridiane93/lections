package com.meridiane.magiccasino.imageViewScrolling

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.meridiane.magiccasino.R
import kotlinx.android.synthetic.main.image_view_scrolling.view.*
import kotlinx.android.synthetic.main.image_view_scrolling3.view.*


class ImageViewScrolling3: FrameLayout {
    internal lateinit var eventEnd: IEventEnd

    internal var last_result3 = 0
    internal var oldValue3 = 0

    companion object{
        private const val ANIMATION_DURATION = 150
    }

    val value: Int
        get() = Integer.parseInt(nextImage3.tag.toString())

    fun setEventEnd3(eventEnd: IEventEnd){
        this.eventEnd = eventEnd
    }

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context,attrs:AttributeSet): super(context,attrs){
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling3,this)

        nextImage3.translationY = height.toFloat()
    }

    fun setValueRandom3(image: Int, num_rotate: Int){
        currentImage3.visibility = View.VISIBLE
        currentImage3.animate()
            .translationY((-height).toFloat())
            .setDuration(ANIMATION_DURATION.toLong()).start()

        nextImage3.translationY = nextImage3.height.toFloat()

        nextImage3.animate().translationY(0f).setDuration(ANIMATION_DURATION.toLong())
            .setListener(object : Animator.AnimatorListener { // прослушивание анимации у объекта
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                        setImage(currentImage3,oldValue3%8)
                        currentImage3.translationY = 0f

                        if(oldValue3 != num_rotate){
                            setValueRandom3(image,num_rotate)
                            oldValue3++
                        } else {
                            last_result3 = 0
                            oldValue3 = 0
                            setImage(nextImage3,image)
                            currentImage3.visibility = View.GONE
                                // eventEnd.eventEnd(image%8,num_rotate)
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
        last_result3 = value
    }
}