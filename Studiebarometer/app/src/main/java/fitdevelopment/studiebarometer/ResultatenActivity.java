package fitdevelopment.studiebarometer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import fitdevelopment.studiebarometer.List.VakkenActivity;
import fitdevelopment.studiebarometer.database.DatabaseHelper;
import fitdevelopment.studiebarometer.database.DatabaseInfo;


public class ResultatenActivity extends AppCompatActivity {

    private PieChart mChart;
    public static final int MAX_ECTS = 60;
    public static int currentEcts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultaten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //terug naar vorige activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);




        /*Button fab = (Button) findViewById(R.id.plusTweeTest);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentEcts < MAX_ECTS) {
                    setData(currentEcts += 2);
                } else {
                    setData(currentEcts = 0);
                }
            }
        });*/

        Button fab = (Button) findViewById(R.id.plusTweeTest);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultatenActivity.this, AdviesActivity.class));
            }
        });



        for (int i = 1; i < 20; i++)
        {
            DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
            Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.Course, new String[]{"*"}, null, null, null, null, null);
            rs.move(i);

            String cijfer = (String) rs.getString(rs.getColumnIndex("grade"));
            String ects = (String) rs.getString(rs.getColumnIndex("ects"));
            String name = (String) rs.getString(rs.getColumnIndex("name"));

            System.out.println("Naam:"+name);
            System.out.println("CijferVoorVak:"+cijfer);
            System.out.println("Ects bij dit vak:"+ects);

            int punten = Integer.parseInt(ects);
            int deelcijfer = Integer.parseInt(cijfer);

                if (deelcijfer >= 1 && currentEcts < 60)
                {
                    currentEcts += punten;
                    System.out.println("CurrentEcts:"+currentEcts);
                }

        }

        setData(currentEcts);

    }

    private void setData(int aantal) {
        currentEcts = aantal;
        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new Entry(aantal, 0));
        xValues.add("Behaalde ECTS");

        yValues.add(new Entry(60 - currentEcts, 1));
        xValues.add("Resterende ECTS");

        //  http://www.materialui.co/colors
        ArrayList<Integer> colors = new ArrayList<>();
        if (currentEcts <10) {
            colors.add(Color.rgb(244,81,30));
        } else if (currentEcts < 40){
            colors.add(Color.rgb(235,0,0));
        } else if  (currentEcts < 50) {
            colors.add(Color.rgb(253,216,53));
        } else {
            colors.add(Color.rgb(67,160,71));
        }
        colors.add(Color.rgb(255, 0, 0));

        PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
        dataSet.setColors(colors);

        PieData data = new PieData(xValues, dataSet);
        mChart.setData(data); // bind dataset aan chart.
        mChart.invalidate();  // Aanroepen van een redraw
        Log.d("aantal =", "" + currentEcts);
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
