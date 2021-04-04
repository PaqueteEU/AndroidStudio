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
        super(context, "notes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement not null, title text, priority int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Aqui lo que haremos es actualizar las tablas si cambiamos la verios. Podemos volverla a crear (DROP), o actualizar (ALTER)
        db.execSQL("DROP TABLE IF EXISTS notas");

    }

    public void insertarNota(String title, int priority) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (title, priority) VALUES ('" + title + "', "+priority+")");

    }

    public void borrarTodasNotas() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void borrarNota(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id=" + id);
    }
    public void actualizarNota(int id, String title, int priority) {
        db = this.getWritableDatabase();
        db.execSQL("UPDATE notas SET title='"+title+"', priority="+priority+" WHERE id=" + id);
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
            res = db.rawQuery("SELECT * FROM notas ORDER BY priority ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
                //System.out.println("-->"+contenido);
                filas.add(contenido);
                res.moveToNext();
            }
        }
        return filas;
    }

    public Nota listarUnaNota(int id) {
        Nota n = null;
        Cursor res = null;
        String contenido = "";
        if (numeroDeNotas() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas WHERE id="+id+" ORDER BY priority ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                //contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
                n = new Nota(res.getInt(res.getColumnIndex("id")),res.getString(res.getColumnIndex("title")),res.getInt(res.getColumnIndex("priority")));
                res.moveToNext();
            }
        }
        return n;
    }

    public void close() {
        db.close();
    }
}
