package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
Utilizzando il linguaggio Java con il supporto dalla libreria SQLiteDatabase, dove utilizzerò la classe
SQLiteOpenHelper, per creare la mia gestione di un database SQLite
*/
public class MioDatabaseHelper extends SQLiteOpenHelper {
    private static final String db_name = "db_DQ"; //Nome del mio database
    /*
    Costruttore che permette di instanziare la classe MioDatabaseHelper, dove indico il contesto cioè
    indico il package personale dove il database verrà inserito; nome del database; factory per creare
     il cursore ad un oggetto che assume un valore nullo iniziale; la versione del mio database
     */
    public MioDatabaseHelper(Context context){
        super(context, db_name, null, 1);
    }
    /*
    Vado a fare l'override dei metodi onCreate & onUpdate. Il primo permette all'applicazione
    di creare le tabelle utili per il database; il secondo permette di effettuare un downgrade oppure
    upgrade nel caso in cui la versione risulti errata
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        //Creazione tabella domande
        String sql = "CREATE TABLE domande (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "domanda TEXT NOT NULL)";
        //Creazione tabella risposte
        String sql1 = "CREATE TABLE risposte (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "risposta TEXT NOT NULL)";
        /*
        Metodi della libreria SQLiteDatabase, che vanno ad eseguire le operazioni di creazione
        fatte in precedenza
         */
        db.execSQL(sql);
        db.execSQL(sql1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
