package id.kat.pokemon.data

import id.kat.pokemon.data.remote.RemotePoke
import id.kat.pokemon.data.remote.RemotePokeDetail
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
interface Api {


    @GET("pokemon")
    fun getPoke(@Query("limit") limit: Int,
                @Query("offset") offset: Int): Flowable<RemotePoke>

    @GET("pokemon/{number}")
    fun getDetail(@Path("number") number: Int?): Flowable<RemotePokeDetail>
}