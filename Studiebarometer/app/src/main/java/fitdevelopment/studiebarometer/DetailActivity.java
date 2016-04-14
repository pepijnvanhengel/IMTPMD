package fitdevelopment.studiebarometer;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fitdevelopment.studiebarometer.database.DatabaseHelper;
import fitdevelopment.studiebarometer.database.DatabaseInfo;


public class DetailActivity extends AppCompatActivity {

    public static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        TextView textViewNaam = (TextView)findViewById(R.id.textViewName);
        textViewNaam.setText("Course: "+name);

        TextView textViewEcts = (TextView)findViewById(R.id.textViewEcts);
        textViewEcts.setText("ECTS: "+ects);

        TextView textViewGrade = (TextView)findViewById(R.id.editTextGrade);
        textViewGrade.setText(grade);

        TextView textViewPeriod = (TextView)findViewById(R.id.textViewPeriod);
        textViewPeriod.setText("Periode: "+period);

    }
    public void opslaan_cijfer (View v){
        EditText cijfer = (EditText) findViewById(R.id.editTextGrade);
        String setCijfer = cijfer.getText().toString();

        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.CourseColumn.GRADE, setCijfer);
        dbHelper.insert(DatabaseInfo.CourseTables.Course, null, values);



    }

}
