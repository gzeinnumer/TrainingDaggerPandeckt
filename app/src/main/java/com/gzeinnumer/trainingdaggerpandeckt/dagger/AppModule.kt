package com.gzeinnumer.trainingdaggerpandeckt.dagger

import android.app.Application
import android.content.Context
import com.gzeinnumer.trainingdaggerpandeckt.SessionManager
import com.gzeinnumer.trainingdaggerpandeckt.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//todo 10
@Module
class AppModule {

    //todo 12
    @Singleton
    @Provides
    fun providesString(): String {
        return "ProvideString"
    } //end todo 12

    //todo 15
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    } //end todo 15

    //todo 20
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    } //end todo 20

    //todo 24
    @Singleton
    @Provides
    fun providesSessionManager(context: Context): SessionManager {
        return SessionManager(context)
    } //end todo 24
}