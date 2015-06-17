package com.example.root.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class TipSuggesterActivity extends Activity {

    TextView tipCalculatedLbl;
    RatingBar rbTip;
    String tipValue_BckStr = "";
    String tipValueStr = "";
    String nbr_persStr = "";
    String mnt_factStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_suggester);

        //Extract the bundle from the intent to use variables
        Bundle bundle = getIntent().getExtras();
        //Extract each value from the bundle for usage
        tipValueStr = bundle.getString("TIP");
        tipValue_BckStr = tipValueStr;
        nbr_persStr = bundle.getString("NBR_PERS");
        mnt_factStr = bundle.getString("MNT_FACT");

        rbTip = (RatingBar) findViewById(R.id.serviceRatingBar);
        rbTip.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                double tipValue = 10 + (ratingBar.getRating() * 2);
                tipCalculatedLbl = (TextView) findViewById(R.id.tipValueLbl);
                tipValueStr = String.valueOf(tipValue);
                tipCalculatedLbl.setText(String.valueOf(tipValue));

            }
        });

        Button bckButton = (Button)findViewById(R.id.suggest_cancel);
        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainAct = new Intent(TipSuggesterActivity.this, MainActivity.class);
                Bundle bndl = new Bundle();
                bndl.putString("TIP", tipValue_BckStr);
                bndl.putString("NBR_PERS", nbr_persStr);
                bndl.putString("MNT_FACT", mnt_factStr);
                mainAct.putExtras(bndl);
                startActivity(mainAct);
            }
        });

        Button confirmButton = (Button)findViewById(R.id.confirm_suggest_tip);
        confirmButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainAct = new Intent(TipSuggesterActivity.this, MainActivity.class);
                Bundle bndl = new Bundle();
                bndl.putString("TIP", tipValueStr);
                bndl.putString("NBR_PERS", nbr_persStr);
                bndl.putString("MNT_FACT", mnt_factStr);
                mainAct.putExtras(bndl);
                startActivity(mainAct);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_suggester, menu);
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
