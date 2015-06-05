package com.example.root.lab1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button btnConfirm;
    Button btnSettings;

    int DEFAULT_NBR_PERS = 1;
    EditText montantTf;
    EditText pourbTf;
    EditText nbrPersTf;

    AlertDialog alertDialog;

    public static int defaultPercentage = 15;
    public static String defaultDevise = "Dollar($)";

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

                if (montantStr.equals("") || pourbStr.equals("") || nbrPersStr.equals("")) {

                    String alertTitle = "Champ incomplet";
                    String alertMsg;

                    if (nbrPersStr.equals("")) {
                        alertMsg = "Pour continuer, fournissez une valeur pour le - Nombre De Personne - payant votre facture.";

                    } else if (pourbStr.equals("")) {
                        alertMsg = "Pour continuer, fournissez une valeur pour le pourcentage de - Pourboire - désiré sur votre facture.";
                    } else {
                        alertMsg = "Pour continuer, fournissez une valeur pour le - Montant Total - de votre facture.";
                    }


                    alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle(alertTitle);
                    alertDialog.setMessage(alertMsg);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    alertDialog.show();

                } else {
                    Intent sommaire = new Intent(MainActivity.this, sommaire.class);
                    sommaire.putExtra("valeurs_main", montantStr + "!" + pourbStr + "!" + nbrPersStr);
                    startActivity(sommaire);
                }
            }
        });

        btnSettings = (Button)findViewById(R.id.buttonSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        pourbTf= (EditText)findViewById(R.id.pourboireTxtfield_main);
        pourbTf.setText(String.valueOf(defaultPercentage));
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
