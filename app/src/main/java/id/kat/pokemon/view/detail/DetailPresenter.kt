package id.kat.pokemon.view.detail

import android.util.Log
import com.kat.news.utils.safeDispose
import com.kat.news.view.base.Presenter
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.data.remote.RemotePokeDetail
import id.kat.pokemon.service.PokeDetailService
import id.kotlin.training.movies.services.NetworkCallback
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/27/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class DetailPresenter : Presenter<DetailView> {

    private var view: DetailView? = null
    private var disposables: CompositeDisposable? = null

    override fun onAttach(view: DetailView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    override fun onDetach() {
        view = null
        disposables.safeDispose()
    }

    fun getDetail(service: PokeDetailService, poke: Poke?) {
        view?.onProgress()

        Log.d("bbbbbbbbb", poke?.number.toString())

        val disposable = service.getDetail(poke?.number, object : NetworkCallback<RemotePokeDetail>{
            override fun onSuccess(response: RemotePokeDetail) {
                view?.onSuccess(response)
            }

            override fun onError(e: Throwable) {
                Log.e("detail",e.message,e)
            }

        })

        disposable.let { disposables?.add(it) }
    }

}