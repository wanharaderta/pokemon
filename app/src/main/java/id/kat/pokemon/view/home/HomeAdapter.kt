package id.kat.pokemon.view.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kat.news.utils.loadImage
import id.kat.pokemon.R
import id.kat.pokemon.data.local.Poke
import kotlinx.android.synthetic.main.item_pokemon.view.*

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/18/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
class HomeAdapter(
        private val context: Context,
        private val poke: List<Poke>,
        private val listener: HomeListener
) : RecyclerView.Adapter<HomeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder = HomeHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_pokemon,
                    parent,
                    false)
    )

    override fun getItemCount(): Int = poke.size


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val poke = poke[position]
        holder.bindView(context, poke, listener)
    }


}


class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(context: Context,
                 poke: Poke,
                 listener: HomeListener) {

        with(poke) {
            itemView.vtitle.text = name
            loadImage(context, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png", itemView.image)
            itemView.setOnClickListener { listener.onClick(poke) }
        }
    }

}
