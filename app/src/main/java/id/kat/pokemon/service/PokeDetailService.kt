package id.kat.pokemon.service

import com.kat.news.utils.disposableSubscriber
import id.kat.pokemon.data.Api
import id.kat.pokemon.data.remote.RemotePokeDetail
import id.kotlin.training.movies.services.NetworkCallTransformer
import id.kotlin.training.movies.services.NetworkCallback
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/27/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class PokeDetailService(private val api: Api){

    fun getDetail(number: Int?, callback: NetworkCallback<RemotePokeDetail>): Disposable{

        return api.getDetail(number)
                .compose(NetworkCallTransformer<RemotePokeDetail>())
                .onErrorResumeNext(Function { Flowable.error { it } })
                .subscribeWith(disposableSubscriber<RemotePokeDetail>(
                        next = { response -> callback.onSuccess(response) },
                        error = { exception -> callback.onError(exception) }
                ))
    }

}