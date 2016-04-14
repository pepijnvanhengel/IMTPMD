package fitdevelopment.studiebarometer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

import fitdevelopment.studiebarometer.List.VakkenActivity;

public class MainActivity extends AppCompatActivity {

    public static int periode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //haal maand en jaar op
        int jaar = Calendar.getInstance().get(Calendar.YEAR);
        int maand =  Calendar.getInstance().get(Calendar.MONTH);

        //set huidige jaar
        if (maand > 0 && maand < 9){
            String studjaar = jaar - 1 + "/" + jaar;
            TextView studiejaar = (TextView)findViewById(R.id.jaar);
            studiejaar.setText("Jaar: " + studjaar);
        }
        else {
            String studjaar = jaar + "/" + (jaar + 1);
            TextView studiejaar = (TextView)findViewById(R.id.jaar);
            studiejaar.setText("Jaar: " + studjaar);
        }

        //set huidige periode
        if (maand >= 9 && maand <= 11){
            periode = 1;
            TextView semester = (TextView)findViewById(R.id.periode);
            semester.setText("Periode: " + periode);
        }
        else if (maand == 12 || maand == 1){
            periode = 2;
            TextView semester = (TextView)findViewById(R.id.periode);
            semester.setText("Periode: " + periode);
        }
        else if (maand > 1 && maand < 5){
            periode = 3;
            TextView semester = (TextView)findViewById(R.id.periode);
            semester.setText("Periode: " + periode);
        }
        else {
            periode = 4;
            TextView semester = (TextView)findViewById(R.id.periode);
            semester.setText("Periode: " + periode);
        }

        /*Intent intent = getIntent();
        textNaam = getIntent().getExtras().getString("textNaam");
        textNummer = getIntent().getExtras().getString("textNummer");*/

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String textNaam = sharedPreferences.getString("textNaam","");
        String textNummer = sharedPreferences.getString("textNummer","");

        TextView naam = (TextView)findViewById(R.id.naam);
        naam.setText("Naam: " + textNaam);

        TextView nummer = (TextView)findViewById(R.id.nummer);
        nummer.setText("Studentnummer: s" + textNummer);






        TextView studiepunten = (TextView)findViewById(R.id.punten);
        studiepunten.setText("Behaalde studiepunten: " + ResultatenActivity.currentEcts);
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

        if (id == R.id.action_gegevens) {
            startActivity(new Intent(this, GegevensActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void vakken (View v){
        startActivity(new Intent(this, VakkenActivity.class));
    }

    public void resultaten (View v){
        startActivity(new Intent(this, ResultatenActivity.class));
    }
}
