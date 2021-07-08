package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
/*
Qui si compone il menu per la scelta dei livelli del gioco. Metto a disposizione all'utente la possibilità
di scegliere i livelli ed inserire un Username.
 */
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val level1 = findViewById<ImageButton>(R.id.btn_level_1)
        val level2 = findViewById<ImageButton>(R.id.btn_level_2)
        val level3 = findViewById<ImageButton>(R.id.btn_level_3)
        val textUser = findViewById<TextInputEditText>(R.id.text_input_user)
        /*
        Qui avviene ciò che Intent permette di fare.Nella prossima Activity, ho bisogno di passare
        la scelta del livello da parte dell'utente. Infatti, utilizzando i metodi di Intent, putExtra,
        avviene una vera e propria trasmissione di dati, che verrano captati dall'Activity successiva.
        Mediante l'uso di metodi specifici.
         */
        level1.setOnClickListener {
            /*
            Nella gestione dell'evento di click del bottone LEVEL, salvo le informazioni utili, che verrano
            poi inviate all'Activity successiva.
             */
            val intentLevel1 = Intent(this, MainActivity3::class.java)
            /*
            Nel primo putExtra, indico il livello scelto da parte dell'utente con il relativo Username scelto.
             */
            intentLevel1.putExtra("Livello", "Level 1")
            intentLevel1.putExtra("Username", textUser.text.toString())
            /*
            Effettuo dei controlli sull'username inserito il quale non può essere vuoto, composto da soli spazi
            e deve avere una lunghezza compresa tra 4 e 10 caratteri. Questa gestione viene effettuata per qualsiasi
            bottone venga cliccato. I bottoni usati non sono semplici Button ma ImageButton.
             */
            if(TextUtils.isEmpty(textUser.text.toString().trim())){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if(textUser.text.toString().length > 10){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if (textUser.text.toString().length < 4){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else
                startActivity(intentLevel1)
        }

        level2.setOnClickListener {
            val intentLevel2 = Intent(this, MainActivity3::class.java)
            intentLevel2.putExtra("Livello","Level 2")
            intentLevel2.putExtra("Username", textUser.text.toString())
            if(TextUtils.isEmpty(textUser.text.toString().trim())){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if(textUser.text.toString().length > 10){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if (textUser.text.toString().length < 4){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else
                startActivity(intentLevel2)
        }

        level3.setOnClickListener {
            val intentLevel3 = Intent(this, MainActivity3::class.java)
            intentLevel3.putExtra("Livello", "Level 3")
            intentLevel3.putExtra("Username", textUser.text.toString())

            if(TextUtils.isEmpty(textUser.text.toString().trim())){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if(textUser.text.toString().length > 10){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else if (textUser.text.toString().length < 4){
                Toast.makeText(this, "No word insert or/and name too long/short!",Toast.LENGTH_SHORT).show()
            }else
                startActivity(intentLevel3)
        }
    }
}