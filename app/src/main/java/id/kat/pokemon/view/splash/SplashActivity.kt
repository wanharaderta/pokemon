package id.kat.pokemon.view.splash

import android.os.Bundle
import id.kat.pokemon.R
import id.kat.pokemon.deps.provider.PokeProvider
import id.kat.pokemon.view.base.BaseActivity
import id.kat.pokemon.view.home.HomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        (application as PokeProvider).providesPokeComponent().inject(this)

        fun initView(){

            startActivity<HomeActivity>()

        }

        initView()
    }
}
