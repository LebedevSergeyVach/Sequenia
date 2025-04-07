package com.sequenia.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

/**
 * [SplashActivity] - стартовая Activity приложения, отображающая экран-заставку.
 *
 * @property [SplashScreen] Используется для управления экраном-заставкой
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen: SplashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        enableEdgeToEdge()

        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}
