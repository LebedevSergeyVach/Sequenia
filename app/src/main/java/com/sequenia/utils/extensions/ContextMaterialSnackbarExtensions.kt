package com.sequenia.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Button

import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.snackbar.Snackbar

import com.sequenia.R

import com.google.android.material.R as MaterialR

/**
 * Расширение для [Context], показывающее стилизованный [Snackbar] с возможностью повтора действия.
 * Использует `Material Design` компоненты для кастомизации внешнего вида.
 *
 * @param binding ViewBinding для получения корневого `View`.
 * @param message Текст сообщения для отображения.
 * @param retryAction Лямбда-функция, выполняемая при нажатии на кнопку повтора.
 *
 * @see Snackbar Базовый класс [Snackbar].
 * @see MaterialShapeDrawable Для кастомизации фона.
 */
fun Context.showErrorMaterialSnackbar(
    binding: ViewBinding, message: String, retryAction: () -> Unit,
) {
    Snackbar.make(
        binding.root, message, Snackbar.LENGTH_INDEFINITE
    ).apply {
        view.backgroundTintList = null

        view.background = MaterialShapeDrawable().apply {
            fillColor = ColorStateList.valueOf(
                ContextCompat.getColor(context, R.color.snackbar_background)
            )
//            setCornerSize(2f.dpToPx(context))
            setCornerSize(2f)
        }

        setTextColor(
            ContextCompat.getColor(
                context,
                R.color.white
            )
        )

        setAction(R.string.repeat) {
            retryAction()
            dismiss()
        }

        setActionTextColor(ContextCompat.getColor(context, R.color.secondary_element))

        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
                super.onShown(sb)
                view.findViewById<Button>(MaterialR.id.snackbar_action)
                    ?.apply {
                        background = null
                    }
            }
        })
    }.show()
}
