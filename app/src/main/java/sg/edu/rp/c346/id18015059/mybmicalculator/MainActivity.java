package sg.edu.rp.c346.id18015059.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText Weight;
    private EditText Height;
    private TextView date;
    private TextView status;
    private String BMIresult;
    private TextView resultBMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Weight = (EditText)findViewById(R.id.WeightInput);
        Height = (EditText)findViewById(R.id.HeightInput);
        date = (TextView)findViewById(R.id.calulcateddate);
        status = (TextView)findViewById(R.id.healthstatus);
        resultBMI = (TextView)findViewById(R.id.bmiresult);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String W = Weight.getText().toString();
        String H = Height.getText().toString();

        float weightVal= Float.parseFloat(W);
        float HeightVal= Float.parseFloat(H);
        float BMI = weightVal / (HeightVal * HeightVal);

        if (BMI < 18.5){
            BMIresult = "You are underweight";
        }else if (BMI >= 18.5 && BMI <= 24.9){
            BMIresult = "Your BMI is normal";
        }else if (BMI >= 25 && BMI <= 29.9){
            BMIresult = "You are overweight";
        }else{
            BMIresult = "You are obese";
        }

        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Date", datetime);
        prefEdit.putString("Status",BMIresult);
        prefEdit.putFloat("BMI", BMI);

        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String retDate = prefs.getString("Date", "");
        String HealthStatus = prefs.getString("Status","");
        float bmi = prefs.getFloat("BMI",0);

        resultBMI.setText("Last Calculated BMI: "+ bmi);
        status.setText(HealthStatus);
        date.setText("Last Calculated Date:  "+ retDate);

    }

    public void calculateBTN (View view){
        String W = Weight.getText().toString();
        String H = Height.getText().toString();

        float weightVal= Float.parseFloat(W);
        float HeightVal= Float.parseFloat(H);
        float BMI = weightVal / (HeightVal * HeightVal);

        if (BMI < 18.5){
            BMIresult = "You are underweight";
        }else if (BMI >= 18.5 && BMI <= 24.9){
            BMIresult = "Your BMI is normal";
        }else if (BMI >= 25 && BMI <= 29.9){
            BMIresult = "You are overweight";
        }else{
            BMIresult = "You are obese";
        }

        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        resultBMI.setText("Last Calculated BMI: "+ BMI);
        date.setText("Last calculated date: " + datetime);
        status.setText(BMIresult);


    }

    public void resetBTN (View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().clear().apply();
    }
}