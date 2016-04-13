package fitdevelopment.studiebarometer;

/**
 * Created by Billy on 13-4-2016.
 */
import java.io.Serializable;
public class Course implements Serializable {

    public String name;
    public String ects;
    public String grade;
    public String period;


    public Course(String courseName, String ects, String grade, String period){
        this.name = courseName;
        this.ects = ects;
        this.grade = grade;
        this.period = period;
    }

    public void setCourseName(String courseName){
        this.name = courseName;
    }

    public void setEcts(String ects){
        this.ects = ects;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public void setPeriod(String period){
        this.period = period;
    }

    public String getCourseName(){
        return this.name;
    }

    public String getEcts(){
        return this.ects;
    }

    public String getGrade(){
        return this.grade;
    }

    public String getPeriod(){
        return this.period;
    }

}