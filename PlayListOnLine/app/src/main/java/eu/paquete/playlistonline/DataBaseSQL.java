package eu.paquete.playlistonline;

/**
 * @author Marco Rodriguez
 */


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class DataBaseSQL extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public DataBaseSQL(Context context) {
        super(context, "audiovideo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table media (id integer primary key autoincrement not null, titulo text, url text, prioridad int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Aqui lo que haremos es actualizar las tablas si cambiamos la version. Podemos volverla a crear (DROP), o actualizar (ALTER)
        db.execSQL("DROP TABLE IF EXISTS media");
    }

    //Metodo para insertar un audio o video
    public void insertarFavorito(String titulo, String url, int prioridad) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO media (titulo, url, prioridad) VALUES ('" + titulo + "', '" + url + "',"+prioridad+")");

    }

    //Metodo para borrar un favorito
    public void borrarFavorito(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM media WHERE id=" + id);
    }
    //Creamos este método por si tengo que usar el dato del numero de favoritos en la tabla
    public int numeroDeFavoritos() {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "media");

        return num;
    }

    //Metodo para listar todos los favoritos que haya en la BBDD
    public ArrayList<String> listarTodosLosFavoritos() {
        ArrayList<String> filas = new ArrayList<String>();
        Cursor res = null;
        String contenido = "";
        String numEstrellas="";
        int prioridad=0;
        if (numeroDeFavoritos() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM media ORDER BY prioridad ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                prioridad=res.getInt(res.getColumnIndex("prioridad"));

                if (prioridad==1){
                    numEstrellas="*****";
                }
                else if (prioridad==2){
                    numEstrellas="****";
                }
                else if (prioridad==3){
                    numEstrellas="***";
                }
                else if (prioridad==4){
                    numEstrellas="**";
                }
                else {
                    numEstrellas="*";
                }
                contenido = res.getInt(res.getColumnIndex("id")) + ". " + res.getString(res.getColumnIndex("titulo"))+" "+numEstrellas;
                System.out.println("-->"+contenido);

                //System.out.println(prioridad);
                filas.add(contenido);
                res.moveToNext();
            }
        }
        return filas;
    }

    public Registro listarUnRegistro(int id) {
        Registro n = null;
        Cursor res = null;

        if (numeroDeFavoritos() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM media WHERE id="+id, null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                
                n = new Registro(res.getInt(res.getColumnIndexOrThrow("id")),  res.getString(res.getColumnIndexOrThrow("titulo")), res.getString(res.getColumnIndexOrThrow("url")));
                res.moveToNext();
            }
        }
        return n;
    }


    //Por ultimo, cerramos la BBDD
    public void close() {
        db.close();
    }
}
