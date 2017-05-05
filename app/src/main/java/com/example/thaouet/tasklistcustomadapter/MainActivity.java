package com.example.thaouet.tasklistcustomadapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listViewTaches;
    private ArrayList<Task> listeDesTaches;
    private TaskAdapter adapter;

    private static String couleur_favorite;
    private int max_taches_preference;
    private boolean notification_preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lire les preferences
        loadPref();





        listeDesTaches = new ArrayList<Task>();
        listeDesTaches.add(new Task("BodyPump", "21/04/2017", "18:30", false));
        listeDesTaches.add(new Task("Bootcamp C#", "21/04/2017", "20:30", false));
        listeDesTaches.add(new Task("Cours Programmation", "22/04/2017", "08:00", false));
        listeDesTaches.add(new Task("Faire une Tarte aux Fraises", "22/04/2017", "13:00", false));






        listViewTaches = (ListView) findViewById(R.id.list);
        adapter = new TaskAdapter(this, listeDesTaches,couleur_favorite);

        listViewTaches.setAdapter(adapter);

        listViewTaches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,EditTaskActivity.class);
                Task t = listeDesTaches.get(position);
                i.putExtra("libelle",t.getLibelle());
                i.putExtra("date",t.getDateTask());
                i.putExtra("heure",t.getHeureTask());
                i.putExtra("position",position);
                startActivityForResult(i, 2);
            }
        });
    }


    private void loadPref(){
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        notification_preference = mySharedPreferences.getBoolean("notification_preference", false);
      //  max_taches_preference = mySharedPreferences.getInt("nombre_taches", 0);
        couleur_favorite = mySharedPreferences.getString("liste_couleurs","#FFFFFFFF");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_preferences:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PreferencesActivity.class);
                startActivityForResult(intent, 0);

                return true;
            case R.id.menu_quitter:


                Toast.makeText(this, "Quitter l'application", Toast.LENGTH_SHORT).show();
                finish();
                return true;

            default:
                return true;
        }
    }




    public void addTaskButtonClick (View view)
    {  Intent i = new Intent(this,EditTaskActivity.class);
        startActivityForResult(i, 1);
    }

    public void deleteTaskButtonClick(View view)
    {
        ArrayList<Task> checkedItems = adapter.getChecked();
        Toast.makeText(this,String.valueOf(checkedItems.size()),Toast.LENGTH_LONG).show();
            int itemCount = checkedItems.size();
        for(int i=itemCount-1; i >= 0; i--){
              adapter.remove(checkedItems.get(i));
            }
            adapter.ClearSelection();
            adapter.notifyDataSetChanged();
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==1 && resultCode == Activity.RESULT_OK)
        {
            String lib=data.getStringExtra("libelle");
            String date=data.getStringExtra("date");
            String heure=data.getStringExtra("heure");
            listeDesTaches.add(new Task(lib,date,heure,false));
            adapter.notifyDataSetChanged();
        }

        if(requestCode==2 && resultCode == Activity.RESULT_OK)
        {
            String lib=data.getStringExtra("libelle");
            String date=data.getStringExtra("date");
            String heure=data.getStringExtra("heure");
            int position = data.getIntExtra("position",-1);
           if (position != -1) {
               Task t = listeDesTaches.get(position);
               t.setDateTask(date);
               t.setLibelle(lib);
               t.setHeureTask(heure);
           }
            adapter.notifyDataSetChanged();
        }

        if(requestCode==0 )
        {
            loadPref();
            adapter.couleur= couleur_favorite;
            adapter.notifyDataSetChanged();



        }


    }

}
