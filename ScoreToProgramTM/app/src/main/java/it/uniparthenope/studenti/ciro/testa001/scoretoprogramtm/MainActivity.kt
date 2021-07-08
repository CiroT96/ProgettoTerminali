package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

/*
Activity principale, che viene visualizzata come schermata principale della mia applicazione
dove spiego in breve, chi sono, come nasce l'idea
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = findViewById<ImageButton>(R.id.btn_start)//Tasto start che permette di passare ad una nuova activity
        /*
        Utiizzo la libreria intent per passare da una activity all'altra, utile soprattutto più avanti
        quando servirà passare informazioni da varie activity
         */
        start.setOnClickListener{// Gestico l'evento del click del pulsante start
            val  intent = Intent(this, MainActivity1::class.java)
            startActivity(intent)//Avvio la seconda Activity
        }
    }
}