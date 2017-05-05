package com.example.thaouet.tasklistcustomadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by TAHAR on 21/04/2017.
 */

public class TaskAdapter extends BaseAdapter{


    private ArrayList<Task> tasks;
    private Context context;

    public String couleur;

    public TaskAdapter(Context context, ArrayList<Task> tasks,String couleur) {
        this.context = context;
        this.tasks = tasks;
        this.couleur = couleur;
    }
    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.ligne, null);
        }

        TextView txtTache = (TextView)convertView.findViewById(R.id.textViewTask);
        TextView txtDate=(TextView)convertView.findViewById(R.id.textViewDate);
        TextView txtHeure=(TextView)convertView.findViewById(R.id.textViewHeureDebut);
        CheckBox check =(CheckBox) convertView.findViewById(R.id.taskCheck);
        txtTache.setText(tasks.get(position).getLibelle());
        txtTache.setTextColor(Color.parseColor(couleur));

        txtDate.setText(tasks.get(position).getDateTask());
        txtHeure.setText(tasks.get(position).getHeureTask());
        check.setChecked(tasks.get(position).getCheckTask());
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton vw,
                                         boolean isChecked) {

                (tasks.get(position)).setCheckTask(
                        isChecked);

            }
        });
        return convertView;
    }

    public  ArrayList<Task> getChecked() {
        ArrayList<Task> lesTaches = new ArrayList<Task>();
        for (Task p : tasks) {
            if (p.getCheckTask())
                lesTaches.add(p);
        }
        return lesTaches;
    }

    public void  ClearSelection()
    {
        for (Task p: tasks)
            p.setCheckTask(false);
    }

    public void remove(Task i){
        tasks.remove(i);
    }

    @Override
    public boolean isEmpty() {

        return tasks.isEmpty();
    }
}
