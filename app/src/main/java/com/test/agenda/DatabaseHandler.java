package com.test.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javacasm on 04/03/2015.
 * Sencilla aplicación agenda
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="ContactosDB";
    private static final String TABLA_CONTACTOS="Contactos";

    private static final String CAMPO_ID="id";
    private static final String CAMPO_NAME="name";
    private static final String CAMPO_PHONE="phone";

    private Context context;
    public DatabaseHandler(Context _context)
    {
        super(_context,DATABASE_NAME,null,DATABASE_VERSION,null);
        this.context=_context;
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

    public Contacto getContactoById(int _id)
    {
        String strSQL=" SELECT * FROM "+TABLA_CONTACTOS+" id = '?'";
        String []argumentos={Integer.toString(_id)};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(strSQL,argumentos);
        Contacto contact=null;
        if (cursor.getCount()!=1)
        {
            Toast.makeText(this.context,R.string.error,Toast.LENGTH_LONG);
        }
        else
        {
            contact=new Contacto(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        }
        return contact;
    }

    // Recuperamos un contacto por nombre
    public Contacto getContactoByName(String sName)
    {
        String strSQL=" SELECT * FROM "+TABLA_CONTACTOS+" "+CAMPO_NAME+ " = '?'";
        String []argumentos={sName};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(strSQL,argumentos);
        Contacto contact=null;
        if (cursor.getCount()!=1)
        {  Toast.makeText(this.context,R.string.error,Toast.LENGTH_LONG);       }
        else
        {  contact=new Contacto(cursor.getInt(0),cursor.getString(1),cursor.getString(2));        }

        return contact;
    }

    // Recuperamos todos los contactos
    public List<Contacto> getAll()
    {
        List<Contacto> lista=new ArrayList<>();
        String strSQL=" SELECT * FROM "+TABLA_CONTACTOS;

        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(strSQL,null);
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
