package eu.paquete.notas;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatosSQLite extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public BaseDatosSQLite(Context context) {
        super(context, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement not null, title text, telefono integer, lugar text, cliente text, url text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Aqui lo que haremos es actualizar las tablas si cambiamos la verios. Podemos volverla a crear (DROP), o actualizar (ALTER)
        db.execSQL("DROP TABLE IF EXISTS notas");

    }
    //Creamos el metodo para insertar una nueva nota, pasando todos los atributos necesarios
    public void insertarNota(String title, int telefono, String lugar, String cliente, String url) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (title, telefono,lugar,cliente,url) VALUES ('" + title + "', " + telefono + ",'" + lugar + "','" + cliente + "','" + url + "')");

    }

    public void borrarTodasNotas() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void borrarNota(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id=" + id);
    }
    public void actualizarNota(int id, String title, int telefono, String lugar, String cliente, String url) {
        db = this.getWritableDatabase();
        db.execSQL("UPDATE notas SET title='"+title+"', telefono="+telefono+", lugar='"+lugar+"', cliente='"+cliente+"', url='"+url+"' WHERE id=" + id);
    }

    public int numeroDeNotas() {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "notas");

        return num;
    }

    public ArrayList<String> listarTodasNotas() {
        ArrayList<String> filas = new ArrayList<String>();
        Cursor res = null;
        String contenido = "";
        if (numeroDeNotas() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas ORDER BY id ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                contenido = res.getInt(res.getColumnIndex("id"))+ " .- " +res.getString(res.getColumnIndex("title"));
                //System.out.println("--> "+contenido);
                filas.add(contenido);
                res.moveToNext();
            }
        }
        //System.out.println("--> Contenidos de filas: "+filas);
        return filas;
    }

    public Nota listarUnaNota(int id) {
        Nota n = null;
        Cursor res = null;
        String contenido = "";
        if (numeroDeNotas() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas WHERE id="+id+" ORDER BY id ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                //contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
                n = new Nota(res.getInt(res.getColumnIndex("id")),res.getString(res.getColumnIndex("title")),res.getInt(res.getColumnIndex("telefono")),res.getString(res.getColumnIndex("lugar")),res.getString(res.getColumnIndex("cliente")),res.getString(res.getColumnIndex("url")));
                res.moveToNext();
            }
        }
        return n;
    }

    public void close() {
        db.close();
    }
}
