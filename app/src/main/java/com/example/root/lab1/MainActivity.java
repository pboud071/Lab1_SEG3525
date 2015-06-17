package com.example.root.lab1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button btnConfirm;
    Button btnSettings;
    Button btnTipSuggester;

    int DEFAULT_NBR_PERS = 1;
    EditText montantTf;
    EditText pourbTf;
    EditText nbrPersTf;
    TextView montantFactureTextView;

    AlertDialog alertDialog;
    float defaultPercentage;
    String defaultDevise;

    String montantStr = "";
    String pourbStr = "";
    String nbrPersStr = "";
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montantTf = (EditText)findViewById(R.id.MontantFactureTxtfield_main);
        pourbTf= (EditText)findViewById(R.id.pourboireTxtfield_main);
        nbrPersTf = (EditText)findViewById(R.id.nbrPersonneTxtfield_main);
        montantFactureTextView = (TextView)findViewById(R.id.MontantFactureTxtview_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        defaultPercentage = preferences.getFloat("defaultPercentage", 15);
        defaultDevise = preferences.getString("defaultDevise", "$");

        montantFactureTextView.setText("Montant de la facture ("+defaultDevise+")");
        pourbTf.setText(""+ defaultPercentage);

        nbrPersTf.setText("" + DEFAULT_NBR_PERS);

        btnConfirm = (Button)findViewById(R.id.buttonConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                montantStr = montantTf.getText().toString();
                pourbStr = pourbTf.getText().toString();
                nbrPersStr = nbrPersTf.getText().toString();

                int nbrPers_int = Integer.parseInt(nbrPersStr);

                String alertTitle = "Champ incomplet";
                String alertMsg;

                if(nbrPers_int <= 0) {
                    alertTitle = "Personne ne veut payer ?";
                    alertMsg = "Pour continuer, fournissez une valeur superieur à 0 pour le - Nombre De Personne - payant votre facture.";

                    alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle(alertTitle);
                    alertDialog.setMessage(alertMsg);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });

                    alertDialog.show();
                }
                else if (montantStr.equals("") || pourbStr.equals("") || nbrPersStr.equals("")) {


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

        btnTipSuggester = (Button)findViewById(R.id.buttonTipSuggester);
        btnTipSuggester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                montantStr = montantTf.getText().toString();
                pourbStr = pourbTf.getText().toString();
                nbrPersStr = nbrPersTf.getText().toString();
                Intent tipSuggester = new Intent(MainActivity.this, TipSuggesterActivity.class);
                Bundle bndl = new Bundle();
                bndl.putString("TIP", pourbStr);
                bndl.putString("NBR_PERS", nbrPersStr);
                bndl.putString("MNT_FACT", montantStr);
                tipSuggester.putExtras(bndl);
                startActivity(tipSuggester);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        defaultPercentage = preferences.getFloat("defaultPercentage", 15);
        defaultDevise = preferences.getString("defaultDevise", "$");
        pourbTf= (EditText)findViewById(R.id.pourboireTxtfield_main);
        pourbTf.setText(String.valueOf(defaultPercentage));
        montantFactureTextView.setText("Montant de la facture ("+defaultDevise+")");

        //Extract the bundle from the intent to use variables
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //Extract each value from the bundle for usage
            montantStr = bundle.getString("MNT_FACT");
            pourbStr = bundle.getString("TIP");
            nbrPersStr = bundle.getString("NBR_PERS");

            montantTf.setText(String.valueOf(montantStr));
            pourbTf.setText(String.valueOf(pourbStr));
            nbrPersTf.setText(String.valueOf(nbrPersStr));

        }


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
