package com.salvador.devworms.buscador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    Button btnBuscar;
    TextView txtRuta;
    TextView txtContenido;
    String ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscar=(Button)findViewById(R.id.buscar);
        txtRuta=(TextView)findViewById(R.id.ruta);
        txtContenido=(TextView)findViewById(R.id.contenido);

        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){

                irExplorador();
            }

        });
        try {
            Intent i=getIntent();
            ubicacion=i.getExtras().getString("ubicacion");
        }catch (Exception e){

        }
        if(ubicacion!="")
            abrirArchivo();

    }
    private void abrirArchivo(){
        try{
            txtRuta.setText(ubicacion);
            File f= new File(ubicacion);
            if(f==null)
                txtContenido.setText("archivo no valido");
            FileReader fr = new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String texto=br.readLine();
            String aux="";
            while(texto!=null){
                aux=aux+texto;
                texto=br.readLine();

            }
            txtContenido.setText(aux);
            br.close();


        }catch (Exception e){}
    }
    public void irExplorador(){
        Intent i= new Intent(MainActivity.this,Exp.class);
        startActivity(i);

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
