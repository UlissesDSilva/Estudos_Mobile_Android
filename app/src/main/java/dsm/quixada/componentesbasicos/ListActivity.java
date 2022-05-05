package dsm.quixada.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> listStudents;
    ArrayAdapter adapter;
    ListView listViewStudent;
    Button btnViewFeedbacks;
    int selectedstudente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnViewFeedbacks = findViewById(R.id.btnViewFeedbacks);

        selectedstudente = -1;

        listStudents = (ArrayList)getIntent().getExtras().getStringArrayList("listStudent");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listStudents);

        listViewStudent = (ListView)findViewById(R.id.listViewStudent);
        listViewStudent.setAdapter(adapter);
        listViewStudent.setSelector(android.R.color.holo_green_light);

        clickItemList();
    }

    public void activityHome(){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    public void activityAbout() {
        Intent aboutActivity = new Intent(this, AboutActivity.class);
        startActivity(aboutActivity);
    }

    public void activityFeed() {
        Intent feedActivity = new Intent(this, FeedbackActivity.class);
        startActivity(feedActivity);
    }

    public void clickItemList() {
        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(ListActivity.this, "" +listStudents.get(position).toString(), Toast.LENGTH_LONG).show();
                selectedstudente = position;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.homeItem:
                activityHome();
                break;
            case R.id.about:
                activityAbout();
                break;
            case R.id.delete:
                removeStudentList();
                break;
        }
        return true;
    }

    public void removeStudentList() {
        if (selectedstudente >= 0) {
            listStudents.remove(selectedstudente);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ListActivity.this, "Selecione um item", Toast.LENGTH_LONG).show();
        }
    }

    public void longClick(View view) {
        btnViewFeedbacks.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                activityFeed();
                return true;
            }
        });
    }
}