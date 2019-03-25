package de.smartasapps.urbanllamafarmer

import android.app.Application
import de.smartasapps.urbanllamafarmer.nodes.nodeCreateModule
import org.koin.android.ext.android.startKoin

class FarmerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule, nodeCreateModule))
    }
}
