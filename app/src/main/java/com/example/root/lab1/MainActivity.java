package com.example.root.lab1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    Button btnConfirm;

    int DEFAULT_NBR_PERS = 1;
    EditText montantTf;
    EditText pourbTf;
    EditText nbrPersTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        montantTf = (EditText)findViewById(R.id.MontantFactureTxtfield_main);
        pourbTf= (EditText)findViewById(R.id.pourboireTxtfield_main);
        nbrPersTf = (EditText)findViewById(R.id.nbrPersonneTxtfield_main);

        nbrPersTf.setText("" + DEFAULT_NBR_PERS);

        btnConfirm = (Button)findViewById(R.id.buttonConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String montantStr = montantTf.getText().toString();
                String pourbStr = pourbTf.getText().toString();
                String nbrPersStr = nbrPersTf.getText().toString();

                if(montantStr.equals("") || pourbStr.equals("") || nbrPersStr.equals("")){

                }
                else {
                    Intent sommaire = new Intent(MainActivity.this, sommaire.class);
                    sommaire.putExtra("valeurs_main", montantStr + "-" + pourbStr + "-" + nbrPersStr);
                    startActivity(sommaire);
                }
            }
        });
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
