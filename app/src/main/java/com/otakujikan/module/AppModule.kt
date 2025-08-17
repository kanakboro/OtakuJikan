package com.otakujikan.module

import com.otakujikan.domain.provider.gsonprovider.GsonProvider
import com.otakujikan.domain.provider.gsonprovider.GsonProviderImpl
import com.otakujikan.domain.provider.networkprovider.NetworkProvider
import com.otakujikan.domain.provider.networkprovider.NetworkProviderImpl
import com.otakujikan.domain.repository.fetchanimedetail.FetchAnimeDetailRepository
import com.otakujikan.domain.repository.fetchanimedetail.FetchAnimeDetailRepositoryImpl
import com.otakujikan.domain.repository.fetchtopanime.FetchTopAnimeRepository
import com.otakujikan.domain.repository.fetchtopanime.FetchTopAnimeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    /**
     * Binds the implementation of [FetchTopAnimeRepository] to be used in the ViewModel.
     */
    @Binds
    abstract fun provideFetchTopAnimeRepository(
        impl: FetchTopAnimeRepositoryImpl
    ): FetchTopAnimeRepository

    /**
     * Binds the implementation of [FetchAnimeDetailRepository] to be used in the ViewModel.
     */
    @Binds
    abstract fun provideFetchAnimeDetailRepository(
        impl: FetchAnimeDetailRepositoryImpl
    ): FetchAnimeDetailRepository

    /**
     * Binds the implementation of [GsonProvider] to be used in the ViewModel.
     */
    @Binds
    abstract fun bindGsonProvider(
        gsonProviderImpl: GsonProviderImpl
    ): GsonProvider


    /**
     * Binds the implementation of [NetworkProvider] to be used in the ViewModel.
     */
    @Binds
    abstract fun bindNetworkProvider(
        networkProviderImpl: NetworkProviderImpl
    ): NetworkProvider
}