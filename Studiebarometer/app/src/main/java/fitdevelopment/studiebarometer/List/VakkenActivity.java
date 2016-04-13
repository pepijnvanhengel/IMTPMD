package fitdevelopment.studiebarometer.List;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import fitdevelopment.studiebarometer.Course;
import fitdevelopment.studiebarometer.R;

public class VakkenActivity extends AppCompatActivity {

    private ListView mListView;
    private CourseListAdapter mAdapter;
    private List<Course> courseModels = new ArrayList<>();    // NEED A METHOD TO FILL THIS. RETRIEVE THE DATA FROM JSON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vakken);


        mListView = (ListView) findViewById(R.id.my_list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                 Toast t = Toast.makeText(VakkenActivity.this,"Click" + position, Toast.LENGTH_LONG);
                                                 t.show();
                                             }
                                         }
        );
        courseModels.add(new Course("IKPMD", "3", "10", "2"));             // DUMMY DATA
        courseModels.add(new Course("IPMT2", "6", "10", "2"));             // DUMMY DATA
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));
        courseModels.add(new Course("IPROMED", "8", "10", "2"));

        mAdapter = new CourseListAdapter(VakkenActivity.this, 0, courseModels);
        mListView.setAdapter(mAdapter);
    }
}
