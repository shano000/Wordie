package com.example.wordie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val wordToGuess = FourLetterWordList().getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hide the correct answer
        findViewById<TextView>(R.id.tvRightAnswer).visibility = View.GONE

        // Hide the restart button
        findViewById<Button>(R.id.btnRestart).visibility = View.GONE

        // This will get random 4 letter word from the list
        findViewById<TextView>(R.id.tvRightAnswer).text = wordToGuess


        // When user clicked the guess button, grab the input text
        findViewById<Button>(R.id.btnGuess).setOnClickListener{
            val input = findViewById<EditText>(R.id.etGuess).getText().toString()
            // Call function to show the input
            populateInput(input)
        }

        // When restart button is clicked, relaunch the activity
        findViewById<Button>(R.id.btnRestart).setOnClickListener{
            val intent = intent
            finish()
            startActivity(intent)
        }
    }
    // This is the function when user input, grab the text and check
    private fun populateInput(answer: String){
        val guess1 = findViewById<TextView>(R.id.tvAnswer1)
        val guess2 = findViewById<TextView>(R.id.tvAnswer2)
        val guess3 = findViewById<TextView>(R.id.tvAnswer3)

        // If input less than 4 letter, let user know
        if (answer.toString().length < 4){
            Toast.makeText(this, "Please enter 4 letters", Toast.LENGTH_SHORT).show()
        }else{
            if (guess1.text.isEmpty()){
                //Retrieving the text from user input
                guess1.text = answer.toString()

                // Call checkGuess function
                val check1 = checkGuess(answer.toString())
                findViewById<TextView>(R.id.tvAnswer1Check).text = check1

                // Clear the input text
                findViewById<EditText>(R.id.etGuess).getText().clear()
            }else if(guess2.text.isEmpty()){
                //Retrieving the text from user input
                guess2.text = answer.toString()

                // Call checkGuess function
                val check2 = checkGuess(answer.toString())
                findViewById<TextView>(R.id.tvAnswer2Check).text = check2

                // Clear the input text
                findViewById<EditText>(R.id.etGuess).getText().clear()
            }else if(guess2.text.isEmpty()){
            }else if(guess3.text.isEmpty()){
                //Retrieving the text from user input
                guess3.text = answer.toString()

                // Call checkGuess function
                val check3 = checkGuess(answer.toString())
                findViewById<TextView>(R.id.tvAnswer3Check).text = check3

                // Clear the input text
                findViewById<EditText>(R.id.etGuess).getText().clear()

                // show the correct answer button
                findViewById<TextView>(R.id.tvRightAnswer).visibility = View.VISIBLE

                // show the restart button
                findViewById<Button>(R.id.btnRestart).visibility = View.VISIBLE

                // show a toast to user that they have exceeded number of guesses
                Toast.makeText(this, "You only get 3 guesses !", Toast.LENGTH_SHORT).show()

                // disable guess button
                findViewById<Button>(R.id.btnGuess).isEnabled =false
            }

        }
    }
    //This is the function to check all the guess that user put in
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}