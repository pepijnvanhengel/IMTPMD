package fitdevelopment.studiebarometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AdviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //terug naar vorige activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int studiepunten = ResultatenActivity.currentEcts;
        int periode = MainActivity.periode;


        if (periode == 1) {
            setTitle(studiepunten + " ECTS in periode " + periode);
            setContentView(R.layout.advies_periode);
        }


        else if (periode == 2) {
            setTitle(studiepunten + " ECTS in periode " + periode);
            setContentView(R.layout.advies_periode);

            if (studiepunten >= 13){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt alle punten van de eerste periode gehaald! Zorg dat je in de tweede periode zo door blijft gaan. Je kan je propedeuse nog halen dit jaar.");
            }
            else if (studiepunten < 13 && studiepunten > 2){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt (nog) niet alle studiepunten gehaald van de eerste periode. Je kan nog wel over naar het volgende jaar." +
                        " Zet een tandje bij of overweeg bij andere opleidingen te kijken.");
            }
            else {
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je kan al niet meer over naar het volgende jaar. Doe je best om de rest van de punten nog te halen of zorg dat je " +
                        "je op tijd uitschrijft en eventueel op zoek gaat naar een andere opleiding.");
            }
        }


        else if (periode == 3) {
            setTitle(studiepunten + " ECTS in periode " + periode);
            setContentView(R.layout.advies_periode);
            if (studiepunten >= 29){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt alle punten van de eerste en de tweede periode gehaald! Zorg dat je in de derde periode zo door blijft gaan. Je kan je propedeuse nog halen dit jaar.");
            }
            else if (studiepunten < 29 && studiepunten >= 19) {
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet alle studiepunten gehaald van de eerste en tweede periode. " +
                        "Je kan nog wel over naar het volgende jaar.");
            }
            else if (studiepunten < 19 && studiepunten > 8 ){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet alle studiepunten gehaald van de eerste en tweede periode. " +
                        "Je mag niet meer over naar het tweede jaar. Overweeg harder te werken met studievertraging of " +
                        "te orienteren naar een andere opleiding.");
            }
            else {
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet genoeg studiepunten gehaald in de eerste en tweede periode. Je kan geen 40 punten meer halen. " +
                        "Je mag niet meer over naar het tweede jaar en je mag ook niet blijven zitten. Het wordt aangeraden uit te schrijven en " +
                        "te orienteren naar een andere opleiding.");
            }
        }

        //periode 4
        else {
            setTitle(studiepunten + " ECTS in periode " + periode);
            setContentView(R.layout.advies_periode);

            if (studiepunten == 60){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Gefeliciteerd! Je hebt je propedeuse gehaald.");
            }
            else if (studiepunten >= 43){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt alle punten van de eerste, tweede en derde periode gehaald! Zorg dat je in de derde periode zo door blijft gaan. Je kan je propedeuse nog halen dit jaar.");
            }
            else if (studiepunten < 43 && studiepunten >= 33) {
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet alle studiepunten gehaald van de eerste, tweede en derde periode. " +
                        "Je kan nog wel over naar het volgende jaar.");
            }
            else if (studiepunten < 33 && studiepunten > 22 ){
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet alle studiepunten gehaald in de eerste, tweede en derde periode. " +
                        "Je kan mag niet meer direct over naar het tweede jaar. Overweeg harder te werken met studievertraging of " +
                        "te orienteren naar een andere opleiding.");
            }
            else {
                TextView advies = (TextView)findViewById(R.id.advies);
                advies.setText("Je hebt niet genoeg studiepunten gehaald in de eerste, tweede en derde periode. Je kan geen 40 punten meer halen. " +
                        "Je kan niet meer over naar het tweede jaar en je mag ook niet blijven zitten. Het wordt aangeraden uit te schrijven en " +
                        "te orienteren naar een andere opleiding.");
            }

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
