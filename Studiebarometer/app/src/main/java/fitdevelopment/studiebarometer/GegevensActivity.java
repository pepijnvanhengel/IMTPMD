package fitdevelopment.studiebarometer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class GegevensActivity extends AppCompatActivity {

    private String textNummer;
    private String textNaam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gegevens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void opslaan (View v) {

        SharedPreferences sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText naam = (EditText) findViewById(R.id.naam);
        String textNaam = naam.getText().toString();


        EditText student = (EditText) findViewById(R.id.nummer);
        String textNummer = student.getText().toString();

        editor.putString("textNaam", textNaam);
        editor.putString("textNummer", textNummer);
        editor.commit();

        if (student.getText().toString().trim().equals("")) {
            student.setError( "Studentnummer is vereist!" );
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            /*intent.putExtra("textNaam", textNaam);
            intent.putExtra("textNummer", textNummer);
            System.out.println(textNummer);
            System.out.println(textNaam);*/
            startActivity(intent);

        }
    }


    //terug naar vorige activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
