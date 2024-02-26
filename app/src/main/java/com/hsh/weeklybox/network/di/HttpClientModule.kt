package com.hsh.weeklybox.network.di

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpClientModule {
    @Singleton
    @Provides
    @Named("okHttpClient")
    fun provideHttpClient(
        interceptors: @JvmSuppressWildcards Set<Interceptor>,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .readTimeout(READ_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SEC.toLong(), TimeUnit.SECONDS)
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .build()
    }

    companion object {
        const val READ_TIMEOUT_SEC: Int = 10
        const val WRITE_TIMEOUT_SEC: Int = 10

        fun provideRetrofitInternal(
            okHttpClient: OkHttpClient,
            baseUrl: String,
            customizeBuilder: (Retrofit.Builder.() -> Retrofit.Builder) = { this },
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(TikXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .customizeBuilder()
                .client(okHttpClient)
                .build()
        }
    }
}