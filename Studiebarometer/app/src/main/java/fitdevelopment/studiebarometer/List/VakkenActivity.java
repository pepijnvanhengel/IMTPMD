package fitdevelopment.studiebarometer.List;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import fitdevelopment.studiebarometer.Course;
import fitdevelopment.studiebarometer.DetailActivity;
import fitdevelopment.studiebarometer.GSON.GsonRequest;
import fitdevelopment.studiebarometer.GSON.VolleyHelper;
import fitdevelopment.studiebarometer.GegevensActivity;
import fitdevelopment.studiebarometer.MainActivity;
import fitdevelopment.studiebarometer.R;
import fitdevelopment.studiebarometer.database.DatabaseHelper;
import fitdevelopment.studiebarometer.database.DatabaseInfo;

public class VakkenActivity extends AppCompatActivity {

    private ListView mListView;
    private CourseListAdapter mAdapter;
    private List<Course> course = new ArrayList<>();
    public static int test = 0;// NEED A METHOD TO FILL THIS. RETRIEVE THE DATA FROM JSON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        test =1;

        requestSubjects();

        //terug naar vorige activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    // ALLES WAT JE NODGI HEBT OM EEN REQUEST TE MAKEN
    private void requestSubjects(){
        Type type = new TypeToken<List<Course>>(){}.getType();
        GsonRequest<List<Course>> request = new GsonRequest<List<Course>>(
                "http://fuujokan.nl/subject_lijst.json", type, null,
                new Response.Listener<List<Course>>() {
                    @Override
                    public void onResponse(List<Course> response) { processRequestSucces(response); }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){ processRequestError(error);     }
        }
        );
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }






    private void processRequestSucces(List<Course> subjects ){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        ContentValues values = new ContentValues();
        for (Course c : subjects){
            values.put(DatabaseInfo.CourseColumn.NAME,c.getCourseName());
            values.put(DatabaseInfo.CourseColumn.ECTS,c.getEcts());
            values.put(DatabaseInfo.CourseColumn.GRADE,c.getGrade());
            values.put(DatabaseInfo.CourseColumn.PERIOD,c.getPeriod());
            dbHelper.insert(DatabaseInfo.CourseTables.Course, null, values);
        }
        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                 Intent intent = new Intent(VakkenActivity.this, DetailActivity.class);
                                                 intent.putExtra("position", position);
                                                 startActivity(intent);
                                             }
                                         }
        );
        Cursor rs = dbHelper.query(DatabaseInfo.CourseTables.Course, new String[]{"*"}, null, null, null, null, null);

        for (Course c: subjects) {
            rs.moveToNext();
            String name = (String) rs.getString(rs.getColumnIndex("name"));
            String ects = (String) rs.getString(rs.getColumnIndex("ects"));
            String grade = (String) rs.getString(rs.getColumnIndex("grade"));
            String period = (String) rs.getString(rs.getColumnIndex("period"));
            course.add(new Course(name, ects, grade, period));
        }



        mAdapter = new CourseListAdapter(VakkenActivity.this, 0, course);
        mListView.setAdapter(mAdapter);


    }

    private void processRequestError(VolleyError error){

    }

    //terug naar vorige activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, MainActivity.class));
                return super.onOptionsItemSelected(item);
        }
}

