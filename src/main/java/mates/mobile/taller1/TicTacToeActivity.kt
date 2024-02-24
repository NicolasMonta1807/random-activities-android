package mates.mobile.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import mates.mobile.taller1.R
import mates.mobile.taller1.databinding.ActivityTicTacToeBinding

class TicTacToeActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityTicTacToeBinding

    // Initial game state
    private var player1: ArrayList<Int> = ArrayList(5)
    private var player2: ArrayList<Int> = ArrayList(5)
    private var currentTurn = 1
    private lateinit var gameButtons : ArrayList<Button>

    // Ways to win the game
    private val winConditions : ArrayList<ArrayList<Int>> = arrayListOf(
        arrayListOf(1,2,3),
        arrayListOf(4,5,6),
        arrayListOf(7,8,9),
        arrayListOf(1,5,9),
        arrayListOf(3,5,7),
        arrayListOf(1,4,7),
        arrayListOf(2,5,8),
        arrayListOf(3,6,9)
    )

    private fun checkWin(): Boolean {
        var win = false
        if (currentTurn == 1)
        {
            for(way: ArrayList<Int> in winConditions) {
                if(player1.containsAll(way)) {
                    win = true
                    break
                }
            }
        }
        else {
            for(way: ArrayList<Int> in winConditions) {
                if(player2.containsAll(way)) {
                    win = true
                    break;
                }
            }
        }
        return win;
    }

    private fun turnHandler(gridPosition: Int, button : Button) {
        if (currentTurn == 1) {
            player1.add(gridPosition)
            button.text = resources.getString(R.string.player1_mark)
        } else {
            player2.add(gridPosition)
            button.text = resources.getString(R.string.player2_mark)
        }

        if (!checkWin())
        {
            currentTurn = if (currentTurn == 1) 2 else 1
            updateTurnMarker()
        }
        else
        {
            disableAllButtons()
            binding.winAlert.text = "Player $currentTurn wins!!!"
        }
    }

    private fun updateTurnMarker() {
        binding.currentTurn.text =
            String.format(resources.getString(R.string.initialState), currentTurn)
    }

    private fun disableAllButtons() {
        for (button : Button in gameButtons) {
            button.isClickable = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize application
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Format resource string for first player
        binding.currentTurn.text = String.format(binding.currentTurn.text.toString(), currentTurn)

        gameButtons = arrayListOf<Button>(
            binding.grid1,
            binding.grid2,
            binding.grid3,
            binding.grid4,
            binding.grid5,
            binding.grid6,
            binding.grid7,
            binding.grid8,
            binding.grid9
        )

        for (button: Button in gameButtons) {
            button.setOnClickListener {
                if (button.isEnabled) {
                    turnHandler(button.tag.toString().toInt(), button)
                    button.isClickable = false
                }
            }
        }

        val restart = binding.restartButton
        restart.setOnClickListener {
            for (button: Button in gameButtons) {
                button.text = resources.getString(R.string.emptyTile)
                button.isClickable = true
            }
            binding.winAlert.text = ""
            currentTurn = 1
            updateTurnMarker()
            player1.clear()
            player2.clear()
        }
    }
}