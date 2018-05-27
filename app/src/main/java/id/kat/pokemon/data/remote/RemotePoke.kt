package id.kat.pokemon.data.remote
import com.google.gson.annotations.SerializedName


/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */

data class RemotePoke(
    @SerializedName("count") var count: Int,
    @SerializedName("previous") var previous: Any,
    @SerializedName("results") var results: List<Result>,
    @SerializedName("next") var next: String
)

data class Result(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)