package com.joe.hangman

/**
 * Core game logic for the Hangman game.
 * */
class HangmanGame(val secretWord: String, var guessesLeft: Int) {

    // Stores all guessed letters (mutable collection)
    private val guessedLetters = mutableSetOf<Char>()

    /**
     * Returns the current progress of the game as a list of characters.
     * Correctly guessed letters are shown, others are hidden as "_".
     * */
    fun getProgress(): List<Any> {
        val progress = secretWord.map { if (guessedLetters.contains(it)) it else '_' }
        return progress
    }

    /**
     * Returns the progress as a spaced string (e.g.: "T _ E E").
     * */
    fun getProgressString(): String {
        return getProgress().joinToString(separator = " ")
    }

    /**
     * Processes a single-letter guess.
     * Adds it to guessed letters and decreases attempts if the guess is incorrect.
     * */
    fun guessSingleLetter(guess: String) {

        val letter = guess.lowercase()

        guessedLetters.add(letter[0])

        if (!secretWord.contains(letter)) {
            guessesLeft--
        }
    }

    /**
     * Returns true if the player has guessed the entire word.
     * */
    fun hasWon(): Boolean {

        val condition = !getProgress().contains('_')

        return condition
    }

    /**
     * Returns drawable resource ID for hangman image based on attempts left.
     * */
    fun getHangmanDrawable(attemptsLeft: Int): Int {
        return when (attemptsLeft) {
            0 -> R.drawable.hangman6
            1 -> R.drawable.hangman5
            2 -> R.drawable.hangman4
            3 -> R.drawable.hangman3
            4 -> R.drawable.hangman2

            else -> R.drawable.hangman1
        }
    }
}
