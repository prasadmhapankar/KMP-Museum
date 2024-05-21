package org.prasadmhapankar.kmpmuseum

import android.app.Application
import org.prasadmhapankar.kmpmuseum.di.KoinInitializer

class MuseumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer().init()
    }
}
