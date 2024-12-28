package com.example.remotedatabase

import android.app.Application
import com.example.remotedatabase.ui.theme.container.AppContainer
import com.example.remotedatabase.ui.theme.container.MahasiswaContainer

class MahasiswaApplications: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}