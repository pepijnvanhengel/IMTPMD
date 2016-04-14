package fitdevelopment.studiebarometer;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import fitdevelopment.studiebarometer.database.DatabaseHelper;
import fitdevelopment.studiebarometer.database.DatabaseInfo;


public class DetailActivity extends AppCompatActivity {

    public static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        position = getIntent().getExtras().getInt("position");
        System.out.println(position);
        position++;

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

        Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.Course, new String[]{"*"}, null, null, null, null, null);

        rs.move(position);


        String name = (String) rs.getString(rs.getColumnIndex("name"));
        String ects = (String) rs.getString(rs.getColumnIndex("ects"));
        String grade = (String) rs.getString(rs.getColumnIndex("grade"));
        String period = (String) rs.getString(rs.getColumnIndex("period"));

        setTitle("Course: "+name);

        TextView textViewEcts = (TextView)findViewById(R.id.textViewEcts);
        textViewEcts.setText("ECTS: "+ects);

        TextView textViewGrade = (TextView)findViewById(R.id.editTextGrade);
        textViewGrade.setText(grade);

        TextView textViewPeriod = (TextView)findViewById(R.id.textViewPeriod);
        textViewPeriod.setText("Periode: "+period);

        //terug naar vorige activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void opslaan_cijfer (View v){
        EditText cijfer = (EditText) findViewById(R.id.editTextGrade);
        String setCijfer = cijfer.getText().toString();

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.CourseColumn.GRADE, setCijfer);
        dbHelper.insert(DatabaseInfo.CourseTables.Course, null, values);

        System.out.println("Nieuw cijfer: " + setCijfer);



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
