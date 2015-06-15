package com.example.root.lab1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class sommaire extends Activity {

    double montant_som;
    double pourb_som;
    int nbrPers_som;

    TextView montantfactTv;
    TextView pourbMontantTv;
    TextView montantTotalTv;
    TextView pourboireParPersTv;
    TextView chaquePersonnePayeTv;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sommaire);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String defaultDevise = preferences.getString("defaultDevise", "$");
        String fullString;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                fullString= null;
            } else {
                fullString= extras.getString("valeurs_main");
            }
        } else {
            fullString= (String) savedInstanceState.getSerializable("valeurs_main");
        }

        String[] values = fullString.split("!");

        montant_som = Double.parseDouble(values[0]);
        pourb_som = (Double.parseDouble(values[1]))/100;
        nbrPers_som = Integer.parseInt(values[2]);

        montantfactTv = (TextView)findViewById(R.id.montantFactureRslt);
        pourbMontantTv = (TextView)findViewById(R.id.montantPourbRslt);
        montantTotalTv = (TextView)findViewById(R.id.montantTotalRslt);
        pourboireParPersTv = (TextView)findViewById(R.id.pourboireParPersRslt);
        chaquePersonnePayeTv = (TextView)findViewById(R.id.chqPersonneRslt);

        //Initialisation des variables de calcul
        double pourbMontant = 0.0;
        double montantTotal = 0.0;
        double pourbParPers = 0.0;
        double chaquePersPaye = 0.0;

        //Montant de la facture
        montantfactTv.setText(String.format("%.2f", montant_som) + defaultDevise);

        //Montant du pourboire
        pourbMontant = montant_som * pourb_som;
        pourbMontantTv.setText(String.format("%.2f", pourbMontant) + defaultDevise);

        //Montant total a payer
        montantTotal = pourbMontant + montant_som;
        montantTotalTv.setText(String.format("%.2f", montantTotal) + defaultDevise);

        //Pourboire par personne
        pourbParPers = pourbMontant/nbrPers_som;
        pourboireParPersTv.setText(String.format("%.2f", pourbParPers) + defaultDevise);

        //Chaque personne paye
        chaquePersPaye = montantTotal/nbrPers_som;
        chaquePersonnePayeTv.setText(String.format("%.2f", chaquePersPaye) + defaultDevise);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sommaire, menu);
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
