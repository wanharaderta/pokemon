package id.kat.pokemon.view.home

import com.kat.news.view.base.View
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.data.remote.RemotePoke

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/18/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
interface HomeView:View{

    fun onProgress()

    fun onSuccess(response: RemotePoke, offSet: Int)

    fun onPokeDetail(poke: Poke)
}