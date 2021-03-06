package id.kat.pokemon.service


import com.kat.news.utils.disposableSubscriber
import id.kat.pokemon.data.Api
import id.kat.pokemon.data.remote.RemotePoke
import id.kotlin.training.movies.services.NetworkCallTransformer
import id.kotlin.training.movies.services.NetworkCallback
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class PokeService(private val api: Api){

    fun getPokemon(limit: Int, offset: Int,callback: NetworkCallback<RemotePoke>): Disposable {
        return api.getPoke(limit,offset)
                .compose(NetworkCallTransformer<RemotePoke>())
                .onErrorResumeNext(Function { Flowable.error { it } })
                .subscribeWith(disposableSubscriber<RemotePoke>(
                        next = { response -> callback.onSuccess(response) },
                        error = { exception -> callback.onError(exception) }

                ))

    }
}