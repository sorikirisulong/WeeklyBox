package com.hsh.weeklybox.network.di

import com.hsh.weeklybox.network.provider.IEndpointProvider
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(dagger.hilt.components.SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    @Named("apiRetrofit")
    fun provideApiRetrofit(
        @Named("okHttpClient") okHttpClient: OkHttpClient,
        dynamicConverterFactory: TikXmlConverterFactory,
        endpointProvider: IEndpointProvider,
    ): Retrofit {
        return HttpClientModule.provideRetrofitInternal(
            okHttpClient,
            endpointProvider.BASE_URL
        ) {
            addConverterFactory(dynamicConverterFactory)
        }
    }

    @Singleton
    @Provides
    fun provideDynamicConverterFactory(): TikXmlConverterFactory {
        return TikXmlConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}