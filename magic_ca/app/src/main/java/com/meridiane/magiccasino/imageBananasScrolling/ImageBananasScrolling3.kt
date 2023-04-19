package com.meridiane.magiccasino.imageBananasScrolling

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.meridiane.magiccasino.R
import kotlinx.android.synthetic.main.image_bananas_scrolling3.view.*

class ImageBananasScrolling3: FrameLayout {

    internal lateinit var eventBananasEnd3: IEventBananasEnd
    internal var last_result = 0
    internal var oldValue = 0

    companion object{
        private const val ANIMATION_DURATION = 150
    }

    val value: Int
        get() = Integer.parseInt(nextImage3.tag.toString())

    constructor(context: Context): super(context) { init(context) }
    constructor(context: Context,attrs:AttributeSet): super(context,attrs){ init(context) }

    fun setEventEnd3(eventEnd: IEventBananasEnd){
        this.eventBananasEnd3 = eventEnd
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.image_bananas_scrolling3,this)

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
                        setImage(currentImage3,oldValue%8)
                        currentImage3.translationY = 0f

                        if(oldValue != num_rotate){
                            setValueRandom3(image,num_rotate)
                            oldValue++
                        } else {
                            last_result = 0
                            oldValue = 0
                            setImage(nextImage3,image)
                            currentImage3.visibility = View.GONE
                            eventBananasEnd3.eventBananasEnd()
                        }
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}

            }).start()
    }

    private fun setImage(img: ImageView, value: Int) {
        when (value) {
            UtilBananas.bonus -> img.setImageResource(R.drawable.b_roll_bonus)
            UtilBananas.bananasDefault -> img.setImageResource(R.drawable.b_roll_banan_default)
            UtilBananas.bananasYellow -> img.setImageResource(R.drawable.b_roll_banan_yellow)
            UtilBananas.girl -> img.setImageResource(R.drawable.b_roll_girl)
            UtilBananas.monkey -> img.setImageResource(R.drawable.b_roll_monkey)
            UtilBananas.cacadu -> img.setImageResource(R.drawable.b_roll_cacadu)
            UtilBananas.snake -> img.setImageResource(R.drawable.b_roll_snake)
            UtilBananas.flover -> img.setImageResource(R.drawable.b_roll_flower)
            UtilBananas.wild -> img.setImageResource(R.drawable.b_roll_wild)
        }
        img.tag = value
        last_result = value
    }
}