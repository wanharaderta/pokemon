package id.kat.pokemon.view.detail

import android.os.Bundle
import id.kat.pokemon.R
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.view.base.BaseActivity

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class DetailActivity: BaseActivity(){

    private var poke: Poke? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        poke = intent.getParcelableExtra("poke")

        fun initView() {
            setFragment(DetailPokeFragment.newInstance(poke),
                    DetailPokeFragment.TAG.toString(),false,
                    intArrayOf(0, R.anim.slide_in_from_left,0,0))

        }

        initView()

    }
}