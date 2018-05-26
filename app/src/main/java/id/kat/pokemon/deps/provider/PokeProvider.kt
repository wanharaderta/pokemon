package id.kat.pokemon.deps.provider

import id.kat.pokemon.deps.component.PokeComponent

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
interface PokeProvider{

    fun providesPokeComponent():PokeComponent
}