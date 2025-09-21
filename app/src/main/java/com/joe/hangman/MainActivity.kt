package com.joe.hangman

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

    /**
     * Navigates to the word bank selection screen when the Play button is pressed.
     * */
    fun nextScreen(view: View) {

        val intent = Intent(applicationContext, WordBankActivity::class.java)
        startActivity(intent)
    }
}

