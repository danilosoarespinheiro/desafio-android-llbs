package com.example.desafioandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/**
 * The Application class for the Desafio Android app, annotated with HiltAndroidApp to enable
 * dependency injection.
 */
@HiltAndroidApp
class MainApplication : Application()