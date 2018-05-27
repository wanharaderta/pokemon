package id.kat.pokemon.data.local

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
data class Poke(val name: String? = null, val url: String? = null, val _number: Int?) : Parcelable {
    var number: Int? = _number
        get() {
            val urlPartes = url?.split("/".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
            return Integer.parseInt(urlPartes?.get(urlPartes.size - 1))
        }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readValue(Int::class.java.classLoader) as Int?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(url)
        writeValue(_number)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Poke> = object : Parcelable.Creator<Poke> {
            override fun createFromParcel(source: Parcel): Poke = Poke(source)
            override fun newArray(size: Int): Array<Poke?> = arrayOfNulls(size)
        }
    }
}