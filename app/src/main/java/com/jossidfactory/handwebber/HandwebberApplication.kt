package com.jossidfactory.handwebber

import android.app.Application
import com.jossidfactory.handwebber.di.DataModule
import com.jossidfactory.handwebber.di.DomainModule
import com.jossidfactory.handwebber.di.ViewModule
import com.jossidfactory.handwebber.utils.CrashReportingTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import timber.log.Timber

class HandwebberApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@HandwebberApplication)
            modules(
                DataModule,
                DomainModule,
                ViewModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}