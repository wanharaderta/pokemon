package id.kat.pokemon.view.home

import android.util.Log
import com.kat.news.utils.safeDispose
import com.kat.news.view.base.Presenter
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.data.remote.RemotePoke
import id.kat.pokemon.service.PokeService
import id.kotlin.training.movies.services.NetworkCallback
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class HomePresenter : Presenter<HomeView>{


    private var view: HomeView? = null
    private var disposables: CompositeDisposable? = null


    override fun onAttach(view: HomeView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun getPokemon(service: PokeService, offSet: Int){
        view?.onProgress()

        val disposable = service.getPokemon(21,offSet,object : NetworkCallback<RemotePoke>{
            override fun onSuccess(response: RemotePoke) {
                if (offSet == 50)
                    view?.onSuccess(response,offSet)
                else
                    view?.onSuccess(response,offSet)

            }

            override fun onError(e: Throwable) {
                Log.e("pokemon",e.message, e)
            }

        })

        disposable.let { disposables?.add(it) }

    }

    fun pokemonDetail(poke: Poke){
        view?.onPokeDetail(poke)
    }
}