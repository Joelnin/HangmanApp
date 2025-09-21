package com.joe.hangman

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WordBankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_bank)
    }

    /**
     * Starts a game using the Animals word bank.
     * */
    fun animalsBank(view: View) {

        val wordBank = findViewById<Button>(R.id.animalsBtn)

        val intent = Intent(applicationContext, GameActivity::class.java)

        intent.putExtra("wordBank", wordBank.text.toString().lowercase())

        startActivity(intent)
    }

    /**
     * Starts a game using the Nature word bank.
     * */
    fun natureBank(view: View) {

        val wordBank = findViewById<Button>(R.id.natureBtn)

        val intent = Intent(applicationContext, GameActivity::class.java)

        intent.putExtra("wordBank", wordBank.text.toString().lowercase())

        startActivity(intent)
    }

    /**
     * Starts a game using the Objects word bank.
     * */
    fun objectsBank(view: View) {

        val wordBank = findViewById<Button>(R.id.objectsBtn)

        val intent = Intent(applicationContext, GameActivity::class.java)

        intent.putExtra("wordBank", wordBank.text.toString().lowercase())

        startActivity(intent)
    }

    /**
     * Starts a game using the Random word bank.
     * */
    fun randomBank(view: View) {

        val wordBank = findViewById<Button>(R.id.randomBtn)

        val intent = Intent(applicationContext, GameActivity::class.java)

        intent.putExtra("wordBank", wordBank.text.toString().lowercase())

        startActivity(intent)
    }
}