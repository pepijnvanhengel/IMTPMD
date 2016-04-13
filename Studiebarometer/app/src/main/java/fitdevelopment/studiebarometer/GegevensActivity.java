package fitdevelopment.studiebarometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class GegevensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gegevens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //terug naar vorige activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void opslaan (View v){

        EditText naam = (EditText)findViewById(R.id.naam);
        String textNaam = naam.getText().toString();

        EditText student = (EditText)findViewById(R.id.nummer);
        String textNummer = student.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("naamstudent", textNaam);
        intent.putExtra("studentnr", textNummer);
        startActivity(intent);
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
