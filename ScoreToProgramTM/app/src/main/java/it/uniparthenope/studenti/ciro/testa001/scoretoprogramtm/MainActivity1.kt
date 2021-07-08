package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
/*
La seconda Activity è solo una schermata principale che rappresente il menu principale del gioco
 */
class MainActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val startGame = findViewById<Button>(R.id.btn_start_game)

        startGame.setOnClickListener{//Gestisco il click del bottono startGame
            val  intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)//Avvio la terza Activity
        }
    }
}