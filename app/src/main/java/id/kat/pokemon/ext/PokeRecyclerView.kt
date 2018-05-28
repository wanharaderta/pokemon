package id.kat.pokemon.ext

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 2/8/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class PokeRecyclerView : RecyclerView {
    private var listener: CustomTouchListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    interface CustomTouchListener {
        fun onRecViewTouched()
        fun onRecViewUp()
    }

    fun setCustomTouchListener(listener: CustomTouchListener) {
        this.listener = listener
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        Log.v("CustomSwipe2", event.action.toString())
        // The findChildViewUnder() method returns null if the touch event
        // occurs outside of a child View.
        // Change the MotionEvent action as needed. Here we use ACTION_DOWN
        // as a simple, naive indication of a click.
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (listener != null) {
                listener!!.onRecViewTouched()
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (listener != null) {
                listener!!.onRecViewUp()
            }
        } else if (event.action == MotionEvent.ACTION_POINTER_DOWN) {
            if (listener != null) {
                listener!!.onRecViewTouched()
            }
        } else if (event.action == MotionEvent.ACTION_POINTER_UP) {
            if (listener != null) {
                listener!!.onRecViewUp()
            }
        }
        return super.dispatchTouchEvent(event)
    }
}

