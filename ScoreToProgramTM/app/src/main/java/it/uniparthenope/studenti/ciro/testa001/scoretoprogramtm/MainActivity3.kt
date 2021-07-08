package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.*

/*
Nella quarta ed ultima activity della mia applicazione, avviene tutto ciò che concerne la gestione del gioco
e del database.
 */

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        /*
        Come prima cosa istanzio un oggetto di tipo DBManager che permette di gestire le query, che non dimentichiamo
        sono gestite mediante i cursori.
         */
        val db = DbManager(this)
        val csr: Cursor = db.query() //Accesso alla tabella domande.
        val csr1: Cursor = db.query1() //Accesso alla tabella risposte.

        /*
        Mediante il metodo getStringExtra, l'activity può accedere ai dati trasmessi dall'activity precedente.
        L'accesso avviene mediante il nome che abbiamo dichiarato in putExtra.
         */
        val level = intent.getStringExtra("Livello") //Livello Scelto
        val user = intent.getStringExtra("Username") //Username Scelto

        val textLevel = findViewById<TextView>(R.id.level_text)
        val userText = findViewById<TextView>(R.id.user_text)
        /*
        Gestione della TextView, che viene modificata dinamicamente in base al livello scelto. In ugual modo
        vengono modificati i Text dei bottoni, che corrispondo alle risposte della domanda scelta.
         */
        val domandaview = findViewById<TextView>(R.id.domanda_view)
        val rispostabtn = findViewById<Button>(R.id.btn_risposta1)
        val rispostabtn1 = findViewById<Button>(R.id.btn_risposta2)
        val rispostabtn2 = findViewById<Button>(R.id.btn_risposta3)
        val rispostaimage = findViewById<ImageView>(R.id.risposta_image)
        /*
        Variabile WINLOSE indica se l'utente ha risposto correttamente oppure no alla domanda. Bottone BACK
        permette all'utente, dopo aver concluso il gioco, di tornare al menu della scelta dei livelli.
         */
        val btnback = findViewById<Button>(R.id.btn_back)
        var winlose = false
        val random = Random() //Funzione che genera numeri random.
        var nRandom = 0 //Variabili di appoggio del numero randomico

        textLevel.text = level //Imposto dinamicamente il Text del livello con il relativo colore.
        textLevel.setTextColor(ContextCompat.getColor(this, R.color.purple_500))

        userText.text = user //Imposto dinamicamente il Text del Username con il relativo colore.
        userText.setTextColor(ContextCompat.getColor(this, R.color.purple_500))

        val backgroundLevel = findViewById<ConstraintLayout>(R.id.background_level)
        /*
        Qui avviene il caricamento dei dati relativi alla domande e risposte dal database, effettuando
        query mediante l'uso dei cursori.
         */
        if (level.equals("Level 1")) {//Controllo il livello scelto.
            //Carico dinamicamente il campo del livello 1.
            backgroundLevel.background = ContextCompat.getDrawable(this, R.drawable.campo1)
            nRandom = random.nextInt(3)
            csr.moveToPosition(nRandom) //Mediante i cursori mi muovo nella posizione desiderata all'interno del db.
            val domanda = csr.getString(1) // Salvo in una variabile il valore tradotto del cursore.
            domandaview.text = domanda //Dinamicamente cambio il Text della domanda.


        } else {
            if (level.equals("Level 2")) {
                backgroundLevel.background = ContextCompat.getDrawable(this, R.drawable.campo2)
                csr.moveToPosition(5)
                val domanda1 = csr.getString(1)
                domandaview.text = domanda1


            } else {
                backgroundLevel.background = ContextCompat.getDrawable(this, R.drawable.campo3)
                csr.moveToPosition(8)
                val domanda2 = csr.getString(1)
                domandaview.text = domanda2
            }
        }

        domandaview.setTextColor(ContextCompat.getColor(this, R.color.purple_500)) //Colore della domanda.
        /*
        Essendo che le risposte si riferiscono alle stesse domande, cambio dinamicamente il Text dei button
        con le traduzioni dei cursori.
         */
        if(level.equals("Level 1")){
            csr1.moveToPosition(0)
            val risposta = csr1.getString(1)
            rispostabtn.text = risposta
            csr1.moveToPosition(1)
            val risposta1 = csr1.getString(1)
            rispostabtn1.text = risposta1
            csr1.moveToPosition(2)
            val risposta2 = csr1.getString(1)
            rispostabtn2.text = risposta2
        }else if(level.equals("Level 2")){
            csr1.moveToPosition(9)
            val risposta = csr1.getString(1)
            rispostabtn.text = risposta
            csr1.moveToPosition(10)
            val risposta1 = csr1.getString(1)
            rispostabtn1.text = risposta1
            csr1.moveToPosition(11)
            val risposta2 = csr1.getString(1)
            rispostabtn2.text = risposta2
        }else{
            csr1.moveToPosition(20)
            val risposta = csr1.getString(1)
            rispostabtn.text = risposta
            csr1.moveToPosition(19)
            val risposta1 = csr1.getString(1)
            rispostabtn1.text = risposta1
            csr1.moveToPosition(18)
            val risposta2 = csr1.getString(1)
            rispostabtn2.text = risposta2
        }


        /*
        Poiché l'immagine relativa alla vittoria o sconfitta dell'utente e il bottone back devono apparire
        in end game, allora li disabilito, in modo che non sia visibili/utilizzabili dall'utente.
         */
        rispostaimage.isVisible = false
        btnback.isEnabled = false
        btnback.isVisible = false
        /*
        La gestione delle risposte esatte/errate, consiste nel gestire il click da parte dell'utente su di un
        bottone che identifica la risposta esatta. In seguito, verrano gestiti tutti i click possibili che
        l'utente può effettuare.
         */
        rispostabtn.setOnClickListener {
            if (level.equals("Level 1")) {
                if(nRandom == 0) {
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.

                }else if(nRandom == 1){
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.

                }else{
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portieregoal)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "CORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = true //Variabile che indica se l'utente ha vinto/perso.
                }

            }

            if (level.equals("Level 2")) {
                rispostaimage.isVisible = true
                rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portieregoal)
                Toast.makeText(this, "CORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = true
            }

            if (level.equals("Level 3")) {
                rispostaimage.isVisible = true
                rispostaimage.background =
                    ContextCompat.getDrawable(this, R.drawable.portiereparata)
                Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = false
            }
            /*
            Uso del when per gestire il messaggio che indica se l'utente ha vinto/perso.
             */
            when (winlose) {
                false -> Toast.makeText(this, "TRY AGAIN, YOU'LL BE LUCKIER!", Toast.LENGTH_SHORT)
                    .show()
                true -> Toast.makeText(
                    this,
                    "VERY GOOD TRY THE NEXT LEVELS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            /*
            Dopo l'avvenuto click della risposta, disabilito i pulsanti delle risposte per evitare che l'utente possa
            interagire ulteriormente con il gioco. Attivando il tasto back, l'utente potrà tornare al menu principale.
             */
            rispostabtn.isEnabled = false
            rispostabtn1.isEnabled = false
            rispostabtn2.isEnabled = false
            btnback.isEnabled = true
            btnback.isVisible = true
        }

        rispostabtn1.setOnClickListener {
            if (level.equals("Level 1")) {
                if(nRandom == 0) {
                    rispostaimage.isVisible = true
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portieregoal)
                    Toast.makeText(this, "CORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = true

                }else if(nRandom == 1){
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.

                }else{
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.
                }

            }

            if (level.equals("Level 2")) {
                rispostaimage.isVisible = true
                rispostaimage.background =
                    ContextCompat.getDrawable(this, R.drawable.portiereparata)
                Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = false
            }

            if (level.equals("Level 3")) {
                rispostaimage.isVisible = true
                rispostaimage.background =
                    ContextCompat.getDrawable(this, R.drawable.portiereparata)
                Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = false
            }
            when (winlose) {
                false -> Toast.makeText(this, "TRY AGAIN, YOU'LL BE LUCKIER!", Toast.LENGTH_SHORT)
                    .show()
                true -> Toast.makeText(
                    this,
                    "VERY GOOD TRY THE NEXT LEVELS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            rispostabtn.isEnabled = false
            rispostabtn1.isEnabled = false
            rispostabtn2.isEnabled = false
            btnback.isEnabled = true
            btnback.isVisible = true
        }

        rispostabtn2.setOnClickListener {
            if (level.equals("Level 1")) {
                if(nRandom == 0) {
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.

                }else if(nRandom == 1){
                    rispostaimage.isVisible = true
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portieregoal)
                    Toast.makeText(this, "CORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = true

                }else{
                    rispostaimage.isVisible = true //Rendo visibile l'immagine che indica la rispota corretta/errata.
                    //Cambio l'immagine in base alla risposta.
                    rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portiereparata)
                    //Appare un messaggio che indica se l'utente abbia vinto/perso.
                    Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                    winlose = false //Variabile che indica se l'utente ha vinto/perso.
                }
            }

            if (level.equals("Level 2")) {
                rispostaimage.isVisible = true
                rispostaimage.background =
                    ContextCompat.getDrawable(this, R.drawable.portiereparata)
                Toast.makeText(this, "INCORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = false
            }

            if (level.equals("Level 3")) {
                rispostaimage.isVisible = true
                rispostaimage.background = ContextCompat.getDrawable(this, R.drawable.portieregoal)
                Toast.makeText(this, "CORRECT ANSWER!", Toast.LENGTH_SHORT).show()
                winlose = true
            }
            when (winlose) {
                false -> Toast.makeText(this, "TRY AGAIN, YOU'LL BE LUCKIER!", Toast.LENGTH_SHORT)
                    .show()
                true -> Toast.makeText(this, "VERY GOOD TRY THE NEXT LEVELS!", Toast.LENGTH_SHORT)
                    .show()
            }
            rispostabtn.isEnabled = false
            rispostabtn1.isEnabled = false
            rispostabtn2.isEnabled = false
            btnback.isEnabled = true
            btnback.isVisible = true
        }
        /*
        Quando il pulsante BACK, verrà cliccato dall'utente, l'applicazione si occuperà di chiudere
        l'activity attuale mediante la funzione finish().
         */
        btnback.setOnClickListener {
            super.finish()
        }
    }
}