package com.example.thaouet.tasklistcustomadapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTaskActivity extends AppCompatActivity {



    private EditText taskTxt;
    private EditText dateTxt;
    private EditText heureTxt;
    private int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        taskTxt = (EditText)findViewById(R.id.editTextLibelle);
        dateTxt =(EditText)findViewById(R.id.editTextDate);
        heureTxt=(EditText)findViewById(R.id.editTextHeure);

        Bundle bundle = getIntent().getExtras();

        if(bundle!= null )
        {   taskTxt.setText(bundle.getString("libelle"));
            dateTxt.setText(bundle.getString("date"));
            heureTxt.setText(bundle.getString("heure"));

            position= bundle.getInt("position");

        }





    }

    public void ValiderClick (View view)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("libelle",taskTxt.getText().toString());
        returnIntent.putExtra("date",dateTxt.getText().toString());
        returnIntent.putExtra("heure",heureTxt.getText().toString());
        returnIntent.putExtra("position",position);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }

    public void AnnulerClick (View view)
    {

        setResult(Activity.RESULT_CANCELED);

        finish();
    }

}
