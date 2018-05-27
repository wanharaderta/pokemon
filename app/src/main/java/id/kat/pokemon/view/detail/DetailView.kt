package id.kat.pokemon.view.detail

import com.kat.news.view.base.View
import id.kat.pokemon.data.remote.RemotePokeDetail

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/27/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
interface DetailView:View {

    fun onProgress()

    fun onSuccess(response: RemotePokeDetail)
}