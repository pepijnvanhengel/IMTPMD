package fitdevelopment.studiebarometer.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fitdevelopment.studiebarometer.Course;
import fitdevelopment.studiebarometer.R;

/**
 * Created by Billy on 13-4-2016.
 */
public class CourseListAdapter extends ArrayAdapter<Course> {

    public CourseListAdapter(Context context, int resource, List<Course> objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null ) {
            vh = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.view_content_row, parent, false);
            vh.name = (TextView) convertView.findViewById(R.id.subject_name);
            vh.code = (TextView) convertView.findViewById(R.id.subject_code);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Course cm = getItem(position);
        vh.name.setText(cm.name);
        vh.code.setText(cm.ects);
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView code;

    }
}
