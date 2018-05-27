package com.kat.news.deps.module

import dagger.Module
import dagger.Provides
import id.kat.pokemon.data.Api
import id.kat.pokemon.service.PokeDetailService
import id.kat.pokemon.service.PokeService
import javax.inject.Singleton

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/18/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */

@Module
open class ServiceModule{

    @Provides
    @Singleton
    protected fun providesPokeService(api: Api) = PokeService(api)

    @Provides
    @Singleton
    protected fun providesPokeDetailService(api: Api) = PokeDetailService(api)
}