package com.sequenia.utils.extensions

import android.annotation.SuppressLint

import android.content.Context

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

import android.provider.Settings

import com.sequenia.BuildConfig
import com.sequenia.utils.helper.LoggerHelper

/**
 * Расширение для [Context], выполняющее вибрацию с проверкой системных настроек.
 * Учитывает как настройки приложения, так и системные настройки вибрации.
 *
 * @param milliseconds Длительность вибрации в миллисекундах (по умолчанию 35 мс).
 *
 * @see Vibrator Системный сервис вибрации.
 * @see Settings.System.HAPTIC_FEEDBACK_ENABLED Системная настройка вибрации.
 */
@SuppressLint("MissingPermission")
fun Context.singleVibrationWithSystemCheck(
    milliseconds: Long = 35L,
    vibrationEffect: Int = VibrationEffect.DEFAULT_AMPLITUDE
) {
    val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    val isVibrationEnabledInApp = sharedPreferences.getBoolean("VibrationEnabled", true)

    if (!isVibrationEnabledInApp) return

    val isVibrationEnabledInSystem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        true
    } else {
        @Suppress("DEPRECATION")
        Settings.System.getInt(
            contentResolver,
            Settings.System.HAPTIC_FEEDBACK_ENABLED, 0
        ) == 1
    }

    if (!isVibrationEnabledInSystem) return

    if (BuildConfig.DEBUG) {
        LoggerHelper.i("Произошла вибрация singleVibrationWithSystemCheck($milliseconds)")
    }

    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (!vibrator.hasVibrator()) return

    try {
        val effect = VibrationEffect.createOneShot(milliseconds, vibrationEffect)
        vibrator.vibrate(effect)
    } catch (e: Exception) {
        if (BuildConfig.DEBUG) {
            LoggerHelper.e("Ошибка при вибрации: ${e.message}")
        }
    }
}
