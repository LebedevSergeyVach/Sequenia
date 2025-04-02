package com.sequenia.utils.extensions

import android.content.Context
import android.provider.Settings

import android.os.Vibrator

import com.sequenia.utils.helper.LoggerHelper
import com.sequenia.BuildConfig


/**
 * Вызывает простую вибрацию на определенное количество миллисекунд,
 * только если вибрация включена в системе и в настройках приложения.
 *
 * @param milliseconds Длительность вибрации в миллисекундах (по умолчанию 50 мс).
 */
@Suppress("DEPRECATION")
fun Context.singleVibrationWithSystemCheck(
    milliseconds: Long = 35L
) {
    val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    val isVibrationEnabledInApp = sharedPreferences.getBoolean("VibrationEnabled", true)

    if (isVibrationEnabledInApp) {
        val isVibrationEnabledInSystem = Settings.System.getInt(
            this.contentResolver,
            Settings.System.HAPTIC_FEEDBACK_ENABLED,
            0
        ) == 1

        if (isVibrationEnabledInSystem) {
            if (BuildConfig.DEBUG) {
                LoggerHelper.i("Произошла вибрация singleVibrationWithSystemCheck($milliseconds)")
            }

            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(milliseconds)
            }
        }
    }
}
