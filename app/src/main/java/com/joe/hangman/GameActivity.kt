package com.joe.hangman


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var game: HangmanGame // Calling the game

    private lateinit var ivHangmanDraw: ImageView
    private lateinit var tvGameProgress: TextView
    private lateinit var tvAttemptsLeft: TextView
    private lateinit var lettersLayout: LinearLayout
    private lateinit var finalBtns: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Initialize the game with a secret word and 5 attempts
        game = HangmanGame(getSecretWord(), 5)

        // Set up UI elements
        initUI()

        // Render initial UI state
        updateUI(game.guessesLeft)

        tvAttemptsLeft.text = "${game.guessesLeft}"
    }


    /**
     * Finds and initializes UI components, updates text and hangman image.
     * */
    private fun initUI() {
        tvGameProgress = findViewById(R.id.gameProgress)
        tvAttemptsLeft = findViewById(R.id.attemptsLeft)
        ivHangmanDraw = findViewById(R.id.hangman)
        lettersLayout = findViewById(R.id.lettersLayout)
        finalBtns = findViewById(R.id.finalBtns)


        tvGameProgress.text = game.getProgressString().uppercase()
        ivHangmanDraw.setImageDrawable(ContextCompat.getDrawable(this, game.getHangmanDrawable(game.guessesLeft)))
    }

//    private fun getSecretWord1(): String {
//        return "duck"
//    }

    /**
     * Chooses a secret word based on the selected word bank (animals, nature, objects, or random).
     * */
    private fun getSecretWord(): String {

        val choice = intent?.extras?.getString("wordBank").toString().lowercase()

        val wordBank = when (choice) {
            "animals" -> WordBanks.animals
            "nature" -> WordBanks.nature
            "objects" -> WordBanks.objects
            else -> WordBanks.random
        }

        val randomIndex = Random.nextInt(0, wordBank.size)

        return wordBank[randomIndex]
    }

    /**
     * Updates the game UI based on the remaining attempts and win/lose conditions of the game.
     * */
    private fun updateUI(attemptsLeft: Int) {

        val wordString = game.getProgressString()

        when {

            game.hasWon() -> showGameWon(wordString)

            !game.hasWon() -> {

                if (attemptsLeft == 0) {

                    showGameLost(wordString)

                } else {

                    gameRun(attemptsLeft)

                }
            }
        }
    }

    /**
     * Displays losing state of the game: reveal the secret word, update hangman image to the losing one, and show the final buttons.
     * */
    private fun showGameLost(wordToGuess: String) {
        tvGameProgress.text = wordToGuess.uppercase()
        ivHangmanDraw.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hangman6))
        lettersLayout.visibility = View.GONE
        finalBtns.visibility = View.VISIBLE
    }

    /**
     * Displays winning state: reveal the secret word, show the victory image, and show the final buttons.
     * */
    private fun showGameWon(wordToGuess: String) {
        tvGameProgress.text = wordToGuess.uppercase()
        ivHangmanDraw.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hangman7))
        lettersLayout.visibility = View.GONE
        finalBtns.visibility = View.VISIBLE
    }

    /**
     * Updates game visuals while it's running. This includes the correct hangman image, game progress, and attempts left.
     * */
    private fun gameRun(attemptsLeft: Int) {

        val drawable = game.getHangmanDrawable(attemptsLeft)

        ivHangmanDraw.setImageDrawable(ContextCompat.getDrawable(this, drawable))
        tvGameProgress.text = game.getProgressString().uppercase()
        tvAttemptsLeft.text = "$attemptsLeft"

    }

    /**
     * Handles a letter button click: uses the letter, hides the button, and updates UI.
     * */
    fun useLetter(btn: View) {

        val letter: Button = btn as Button

        if (!letter.text.toString().isEmpty()) {
            game.guessSingleLetter("${(letter).text.toString()[0]}")
            letter.visibility = View.INVISIBLE
            updateUI(game.guessesLeft)
            tvAttemptsLeft.text = "${game.guessesLeft}"
        }
    }

    /**
     * Navigates back to the main menu screen.
     * */
    fun menuScreen(view: View) {

        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)

    }

    /**
     * Navigates to the word bank selection screen.
     * */
    fun wordBankScreen(view: View) {

        val intent = Intent(applicationContext, WordBankActivity::class.java)
        startActivity(intent)

    }
}

