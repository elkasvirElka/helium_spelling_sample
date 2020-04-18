package com.elviraminnullina.helium_samples

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.lang3.StringUtils
import java.util.*


private const val TEST = "TEST"


enum class ControlEvent {
    keyPress,
    removePress,
    setSelection
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var previousCount = 0
        var previousValue = ""
        edit_text.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    value: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    previousValue = value.toString()
                }

                override fun onTextChanged(
                    value: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    if (previousCount <  value?.length?:0) {
                        value?.getOrNull(start.coerceAtLeast(before))?.toString()?.apply {
                            printData(
                                ControlEvent.keyPress,
                                this.toLowerCase(),
                                value.toString()
                            )
                        }
                    } else {
                        printData(
                            ControlEvent.removePress,
                            start.coerceAtLeast(before).toString(),
                            value.toString()
                        )
                    }
                    previousCount = value?.length?:0
                }

            }
        )
    }

    override fun onResume() {
        super.onResume()

        edit_text.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun sendAccessibilityEvent(host: View?, eventType: Int) {
                super.sendAccessibilityEvent(host, eventType)
                if (eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED) {

                    val selectionStart = edit_text.selectionStart
                    val selectionEnd = edit_text.selectionEnd
                    if (selectionStart != selectionEnd) {
                        printData(
                            ControlEvent.setSelection,
                            "${selectionStart},${selectionEnd - selectionStart}",
                            edit_text.text.toString()
                        )
                    }
                }
            }
        }

    }

    private fun printData(controlEvent: ControlEvent, identifier: String, value: String) {
        Log.e(
            TEST, "controlEvent: $controlEvent \r\n" +
                    "identifier: $identifier \n" +
                    "timestamp: ${Date(System.currentTimeMillis())} \n" +
                    "value: $value \n"
        )
    }

}

