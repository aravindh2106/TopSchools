package `in`.logicsoft.project

import android.app.Application
import di.initKoin

class SchoolApp : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}