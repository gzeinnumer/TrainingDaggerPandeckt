package com.gzeinnumer.trainingdaggerpandeckt

import com.gzeinnumer.trainingdaggerpandeckt.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

//todo 2
class MyApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return null
        //todo 6 komentarkan yang diatas
        return DaggerAppComponent.builder().application(this).build();
        //end todo 6
    }
}