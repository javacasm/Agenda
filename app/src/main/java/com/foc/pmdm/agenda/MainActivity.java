package com.foc.pmdm.agenda;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler accesoDB=new DatabaseHandler(this);
        accesoDB.addContacto(new Contacto(1,"Pepe","+345454545"));
        accesoDB.addContacto(new Contacto(1,"Juan","+454545"));
        accesoDB.addContacto(new Contacto(1,"Antonio","+3454545"));
        accesoDB.addContacto(new Contacto(1,"Luis","+3454545"));

        List<Contacto> lista=accesoDB.getAll();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
