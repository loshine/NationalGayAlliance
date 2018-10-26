package xzy.loshine.nga

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.paperdb.Paper
import xzy.loshine.nga.di.components.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        Paper.init(applicationContext)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}