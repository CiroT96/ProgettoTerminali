package it.uniparthenope.studenti.ciro.testa001.scoretoprogramtm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
/*
Nel DBManager avviene la vera e propria gestione del database,cioè vado a creare metodi che permettono
di inserire, eliminare ed effettuare query sul database.
 */
public class DbManager {
    /*
    Dichiaro un variabile che permette, dopo aver istanziato l'oggetto, di accedere ai metodi di
    MioDatabaseHelper.
     */
    private MioDatabaseHelper dbhelper;
    public DbManager(Context ctx){
        dbhelper = new MioDatabaseHelper(ctx);//Istanzio l'oggetto.
    }
    /*
    Il metodo SAVE, effettua in pratica le funzionalità dell'operazione INSERT in Sql.
     */
    public void save(String dom,String ris){
        SQLiteDatabase db = dbhelper.getWritableDatabase();//Indico al database di passare in solo scrittura
        ContentValues cv = new ContentValues();//Contenitori di valori.
        ContentValues cv1 = new ContentValues();
        //Mediante i metodi del Content Values, inserisco i valori nel contenitore.
        cv.put("domanda", dom);
        cv1.put("risposta",ris);
        try{
            /*
            In primis, eseguo le operazioni di inserimento gestendone le eccezioni nel caso in cui
            uno o entrambi gli inserimenti falliscono.
             */
            db.insert("domande", null, cv);
            db.insert("risposte",null, cv1);
        }catch (SQLiteException sqle){
            //Gestisco le eccezioni.
        }
    }
    /*
    Il metodo DELETE, prendi in input l'id dell'elemento da eliminiare nel database, quindi in pratica
    effettua l'operaizione di DELETE in Sql.
     */
    public boolean delete(long id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();//Indico al database di passare in solo scrittura.
        try{
            /*
            Eseguo l'eliminazione dell'elemento dalla tabella, passando la tabella, l'id, ed in più controllo
            che l'id non sia una stringa nulla. Anche in questo cosa gestisco le eccezioni controllando
            che la DELETE non fallisca.
             */
            if(db.delete("domande", "_id", new String[]{Long.toString(id)})>0)
                return true;
            return false;
        }catch (SQLiteException sqle){
            return false;
        }
    }
    /*
    Query & Query1 sono entrame operazioni di SELECT, cioè compiono il compito di effettuare le query
    all'interno del database. La prima si occupa di gestire le query nella tabella domande, la seconda
    si occupa di gestire le query nella tabella risposte. In particolare in questo caso, si fa uso dei cursori.
    I cursori indicano in maniere randomica le righe all'interno del database, quindi devo essere gestiti mediante
    oppurtuni metodi. In entrambi casi dopo la query, i metodi restituiscono il cursore che dovrà essere tradotto
    per accedervi al contenuto.
     */
    public Cursor query(){
        Cursor crs;
        try{
            SQLiteDatabase db=dbhelper.getReadableDatabase();////Indico al database di passare in solo lettura
            crs=db.query("domande", null, null, null, null, null, null, null);
        } catch (SQLiteException sqle){
            return null;
        }
        return crs;
    }
    public Cursor query1(){
        Cursor crs;
        try{
            SQLiteDatabase db=dbhelper.getReadableDatabase();//Indico al database di passare in solo lettura
            crs=db.query("risposte", null, null, null, null, null, null, null);
        } catch (SQLiteException sqle){
            return null;
        }
        return crs;
    }
}
