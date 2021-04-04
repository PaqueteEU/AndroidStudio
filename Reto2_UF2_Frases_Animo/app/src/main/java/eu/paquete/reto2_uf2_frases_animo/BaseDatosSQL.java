package eu.paquete.reto2_uf2_frases_animo;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class BaseDatosSQL extends SQLiteOpenHelper{
    protected SQLiteDatabase db;

    public BaseDatosSQL(Context context) {
        super(context, "frasesanimo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table frasesanimo (id integer primary key autoincrement not null, frase text)");
        /*
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('El progreso es directamente proporcional a las ganas y pasión que le pongas')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Si quieres algo en tu vida, deberías intentarlo aunque el resultado probable sea el fracaso')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Los buenos programadores no son aquellos que nunca fracasan, son aquello que nunca abandonan')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Al atascarte con tu código, puedes gritar, puedes llorar, pero... NUNCA PUEDES RENDIRTE')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Cuando os digan que no podéis, no sabéis o no valéis para programar, contestad: Siéntate y observa como lo hago')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Planificación, procedimiento (buenas prácticas) y perseverancia, lo tengo tatuado en mi memoria')");
        db.execSQL("INSERT INTO frasesanimo (frase) VALUES ('Boolean estoyVivo=true;\n while(estoyVivo)\n{ \nSystem.out.println(Yo puedo)')");
        */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Aqui lo que haremos es actualizar las tablas si cambiamos la verios. Podemos volverla a crear (DROP), o actualizar (ALTER)
        db.execSQL("DROP TABLE IF EXISTS notas");

    }

     public int numeroDeFrases() {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "frasesanimo");

        return num;
    }

    //Siento haber usado otro metodo, pero me sonaba haberlo leido que se podia hacer un ramdon en el SELECT y lo he implementado asi
    public String getRamdomFrase(){
        String ramdomFrase = "";
        Cursor res = null;
        if (numeroDeFrases() > 0){
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM frasesanimo ORDER BY RANDOM() LIMIT 1", null);
            res.moveToFirst();
            ramdomFrase = res.getString(res.getColumnIndex("frase"));
            res.moveToNext();
        }
        System.out.println("-->"+ramdomFrase);
        return ramdomFrase;
    }




    public void close() {
        db.close();
    }
}

