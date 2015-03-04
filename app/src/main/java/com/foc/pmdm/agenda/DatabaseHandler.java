package com.foc.pmdm.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javacasm on 04/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="ContactosDB";
    private static final String TABLA_CONTACTOS="Contactos";

    private static final String CAMPO_ID="id";
    private static final String CAMPO_NAME="name";
    private static final String CAMPO_PHONE="phone";


    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlCreacion= "CREATE TABLE "+ TABLA_CONTACTOS +
                " ( " +CAMPO_ID+ " INTEGER PRIMARY KEY, "+
                CAMPO_NAME + " TEXT, "+
                CAMPO_PHONE + " TEXT )";
           db.execSQL(sqlCreacion);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sqlBorrado= "DROP TABLE "+TABLA_CONTACTOS;
        db.execSQL(sqlBorrado);
        onCreate(db);
    }

    void addContacto(Contacto contact)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(CAMPO_NAME,contact.name);
        values.put(CAMPO_PHONE,contact.phone);

        db.insert(TABLA_CONTACTOS,null,values);
        db.close();

    }

    public List<Contacto> getAll()
    {
        List<Contacto> lista=new ArrayList<Contacto>();
        String strSQL=" SELECT * FROM "+TABLA_CONTACTOS+" NOMBRE like '?'";
        String []argumentos={"A%"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(strSQL,argumentos);
        do{
            int id=cursor.getInt(0);
            String nombre=cursor.getString(1);
            String phone=cursor.getString(2);
            Contacto contacto=new Contacto(id,nombre,phone);
            lista.add(contacto);
        } while(cursor.moveToNext());

        return lista;
    }
}
