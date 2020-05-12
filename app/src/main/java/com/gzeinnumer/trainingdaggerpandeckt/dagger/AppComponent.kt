package com.gzeinnumer.trainingdaggerpandeckt.dagger

import android.app.Application
import com.gzeinnumer.trainingdaggerpandeckt.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//todo 4
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        //todo 8
        ActivityBuildersModule::class,
        //end todo 8
        //todo 11
        AppModule::class
        //end todo 11
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }
}

//todo 5 rebuild