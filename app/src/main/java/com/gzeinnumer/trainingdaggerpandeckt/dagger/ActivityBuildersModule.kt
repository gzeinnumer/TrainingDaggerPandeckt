package com.gzeinnumer.trainingdaggerpandeckt.dagger

import com.gzeinnumer.trainingdaggerpandeckt.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

//todo 7
@Module
abstract class ActivityBuildersModule{
    @ContributesAndroidInjector
    abstract fun constributesMainActivity() : MainActivity
}