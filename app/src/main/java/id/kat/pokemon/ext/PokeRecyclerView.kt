package id.kat.pokemon.ext

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 2/8/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class PokeRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun canScrollVertically(direction: Int): Boolean {
        // check if scrolling up
        if (direction < 1) {
            val original = super.canScrollVertically(direction)
            return !original && getChildAt(0) != null && getChildAt(0).top < 0 || original
        }
        return super.canScrollVertically(direction)

    }
}