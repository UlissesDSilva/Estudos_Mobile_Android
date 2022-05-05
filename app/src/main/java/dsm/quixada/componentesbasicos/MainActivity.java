package dsm.quixada.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    ToggleButton tggEnable;
    Button btnRegister;
    ArrayList<String> listStudente = new ArrayList<String>();
    AutoCompleteTextView autoCompleteCourse;
    ArrayAdapter<String> adpCourses, adpCampus;
    Spinner spinnerCampus;
    String campusSelected, hourSelected;
    RadioButton abManha, cdManha, abTarde, cdTarde;
    RadioGroup groupHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String courses[] = {"Sistemas de Informação", "Ciência da Computação", "Engenharia da Computação", "Engenharia de Software", "Design Digital"};
        String campus[] = {"Fortaleza", "Russas", "Sobral", "Quixadá"};

        adpCourses = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, courses);
        adpCampus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, campus);

        editName = findViewById(R.id.editName);
        autoCompleteCourse = findViewById(R.id.autoCompleteCourse);
        tggEnable = findViewById(R.id.btnTggEnable);
        btnRegister = findViewById(R.id.btnRegister);
        spinnerCampus = findViewById(R.id.spinnerCampus);
        groupHours = findViewById(R.id.radioHorario);
        abManha = findViewById(R.id.manhaAB);
        cdManha = findViewById(R.id.manhaCD);
        abTarde = findViewById(R.id.tardeAB);
        cdTarde = findViewById(R.id.tardeCD);

        editName.setEnabled(false);
        autoCompleteCourse.setEnabled(false);
        groupHours.clearCheck();

        autoCompleteCourse.setThreshold(1);
        autoCompleteCourse.setAdapter(adpCourses);

        spinnerCampus.setAdapter(adpCampus);
        spinnerCampus.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                    int position = spinnerCampus.getSelectedItemPosition();
                    campusSelected = campus[position];
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            }
        );

        enableFieldRegister();
        checkedHours();
    }

    public void activityHome(View view){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    public void activityList(View view) {
        Intent listActivity = new Intent(this, ListActivity.class);
        listActivity.putStringArrayListExtra("listStudent", listStudente);
        startActivity(listActivity);
    }

    public void enableFieldRegister(){
        tggEnable.setOnCheckedChangeListener((buttonView, isCheked) -> {
            if (tggEnable.isChecked()){
                editName.setEnabled(true);
                autoCompleteCourse.setEnabled(true);
            } else {
                editName.setEnabled(false);
                autoCompleteCourse.setEnabled(false);
            }

        });
    }

    public void cadastrarAluno(View view) {
        String aluno = editName.getText().toString() + "\n" +
                autoCompleteCourse.getText().toString() + "\n" +
                campusSelected + "\n" +
                hourSelected;
        listStudente.add(aluno);
        editName.setText("");
        autoCompleteCourse.setText("");
        groupHours.clearCheck();
    }

    public void checkedHours() {
        groupHours.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    hourSelected = rb.getText().toString();
                }
            }
        });
    }

    public  void clearCheckedHours(View view) {
        editName.setText("");
        autoCompleteCourse.setText("");
        groupHours.clearCheck();
    }
}