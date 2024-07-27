package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this)
        val textView = TextView(this)
        textView.id = R.id.titleTextView
        textView.text = getString(R.string.i_am_an_android_developer)
        linearLayout.addView(textView)
        val button = Button(this)
        linearLayout.addView(button)

        setContentView(linearLayout)
    }
}