package id.kat.pokemon.deps.component

import com.kat.news.deps.module.NetworkModule
import com.kat.news.deps.module.ServiceModule
import dagger.Component
import id.kat.pokemon.view.splash.SplashActivity
import javax.inject.Singleton

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
@Singleton
@Component(modules = arrayOf( NetworkModule::class, ServiceModule::class))
interface PokeComponent{

    fun inject(splashActivity: SplashActivity)
}