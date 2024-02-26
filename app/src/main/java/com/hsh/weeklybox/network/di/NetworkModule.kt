package com.hsh.weeklybox.network.di

import android.util.Log
import com.hsh.weeklybox.BuildConfig
import com.hsh.weeklybox.network.provider.EndpointProvider
import com.hsh.weeklybox.network.provider.IEndpointProvider
import com.hsh.weeklybox.network.repo.CommonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    fun provideApi(@Named("apiRetrofit") retrofit: Retrofit): CommonApi {
        return retrofit.create(CommonApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {
    @Provides
    @IntoSet
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { message ->
            if (BuildConfig.DEBUG) {
                try {
                    JSONObject(message)
                    Log.d("Response", message)
                } catch (ignored: Throwable) {
                    HttpLoggingInterceptor.Logger.DEFAULT.log(message)
                }
            }
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideEndpointProvider(): IEndpointProvider {
        return EndpointProvider
    }
}