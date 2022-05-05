package dsm.quixada.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    Button btnPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnPopupMenu = findViewById(R.id.btnPopupMenu);

        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(AboutActivity.this, btnPopupMenu);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString() == "Inicio") {
                            activityHome();
                            return true;
                        }else {
                            activityList();
                            return true;
                        }
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    public void activityHome(){
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    public void activityList(){
        Intent listActivity = new Intent(this, ListActivity.class);
        startActivity(listActivity);
    }
}