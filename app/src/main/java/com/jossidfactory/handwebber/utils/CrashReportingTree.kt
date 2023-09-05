package com.jossidfactory.handwebber.utils

import android.util.Log
import androidx.annotation.NonNull
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        @NonNull message: String,
        t: Throwable?
    ) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
    }
}