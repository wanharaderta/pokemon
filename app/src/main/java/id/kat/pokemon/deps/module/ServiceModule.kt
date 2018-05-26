package com.kat.news.deps.module

import dagger.Module
import dagger.Provides
import id.kat.pokemon.data.Api
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
    protected fun providesArticleService(api: Api) = PokeService(api)
}