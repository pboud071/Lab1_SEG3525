package com.example.root.lab1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SettingsActivity extends Activity {

    Button btnConfirmSettings;
    EditText txtDefaultPercentage;
    Spinner spinDefaultDevise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Spinner dropdown = (Spinner)findViewById(R.id.deviseSpinner);
        String[] items = new String[]{"Dollar($)", "Euro", "Livre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);

        btnConfirmSettings = (Button)findViewById(R.id.btnConfirmSettings);
        btnConfirmSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDefaultPercentage = (EditText) findViewById(R.id.defaultPourcentageTxt);
                spinDefaultDevise = (Spinner) findViewById(R.id.deviseSpinner);
                float defaultPercentage;
                String defaultPercentageString = txtDefaultPercentage.getText().toString();
                if (defaultPercentageString.matches("")) {
                    defaultPercentage = -1;
                } else {
                    defaultPercentage = Float.parseFloat(defaultPercentageString);
                }

                int defaultDeviseIndex = spinDefaultDevise.getSelectedItemPosition();
                String defaultDevise = "$";
                if (defaultDeviseIndex == 0) {
                    defaultDevise = "$";
                } else if (defaultDeviseIndex == 1) {
                    defaultDevise = "Euro";
                } else if (defaultDeviseIndex == 2) {
                    defaultDevise = "Livres";
                }
                /*MainActivity.defaultPercentage = defaultPercentage;
                MainActivity.defaultDevise = defaultDevise;*/
                SharedPreferences.Editor editor = preferences.edit();
                if (defaultPercentage > 100) {
                    Toast.makeText(getApplicationContext(), "Le pourcentage par default doit etre un nombre entre 0 et 100", Toast.LENGTH_LONG).show();
                } else {
                    if(defaultPercentage != -1) {
                        editor.putFloat("defaultPercentage", defaultPercentage);
                    }
                    editor.putString("defaultDevise", defaultDevise);
                    editor.commit();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
