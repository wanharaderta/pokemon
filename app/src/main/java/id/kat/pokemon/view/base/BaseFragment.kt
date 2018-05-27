package id.kat.pokemon.view.base

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
open class BaseFragment : Fragment() {

    lateinit var activity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity() as BaseActivity
    }
}