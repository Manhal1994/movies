/* Developed by Manhal */

@file:Suppress("unused")

package com.manhal.movies.initializer

import android.content.Context
import androidx.startup.Initializer
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      Timber.d("TimberInitializer is initialized.")
    }
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
    SandwichInitializer::class.java
  )
}
