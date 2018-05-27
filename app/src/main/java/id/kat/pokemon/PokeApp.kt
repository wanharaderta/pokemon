package id.kat.pokemon

import android.support.multidex.MultiDexApplication
import com.kat.news.deps.module.NetworkModule
import com.kat.news.deps.module.ServiceModule
import id.kat.pokemon.deps.component.DaggerPokeComponent
import id.kat.pokemon.deps.component.PokeComponent
import id.kat.pokemon.deps.provider.PokeProvider

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class PokeApp : MultiDexApplication(), PokeProvider{

    private lateinit var component: PokeComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerPokeComponent.builder()
                .networkModule(NetworkModule(this))
                .serviceModule(ServiceModule())
                .build()
    }

    override fun providesPokeComponent(): PokeComponent = component


}