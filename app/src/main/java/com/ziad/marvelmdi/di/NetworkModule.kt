package com.ziad.marvelmdi.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ziad.marvelmdi.data.remote.ApiInterface
import com.ziad.marvelmdi.data.remote.AppInterceptor
import com.ziad.marvelmdi.data.remote.EndPoints
import com.ziad.marvelmdi.data.remote.repository.GeneralRepository
import com.ziad.marvelmdi.domain.repository.IGeneralRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): AppInterceptor {
        return AppInterceptor()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiInterface =
        retrofit.create(ApiInterface::class.java)


    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: AppInterceptor,
    ) = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(EndPoints.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesGeneralRepository(
        apiInterface: ApiInterface
    ): IGeneralRepository {
        return GeneralRepository(apiInterface)
    }

}