package com.example.thaouet.tasklistcustomadapter;

/**
 * Created by TAHAR on 21/04/2017.
 */

public class Task {
    private long id;
    private String libelle;
    private String dateTask;
    private String heureTask;
    private Boolean checkTask;


    public Task(String l, String d, String h, Boolean c)
    {
        libelle= l;
        dateTask = d;
        heureTask = h;
        checkTask = c;
    }


    public Task(int i,String l, String d, String h, Boolean c)
    {
        id=i;
        libelle= l;
        dateTask = d;
        heureTask = h;
        checkTask = c;
    }
    public Task()
    {
        checkTask = false;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDateTask() {
        return dateTask;
    }

    public void setDateTask(String dateTask) {
        this.dateTask = dateTask;
    }

    public String getHeureTask() {
        return heureTask;
    }

    public void setHeureTask(String heureTask) {
        this.heureTask = heureTask;
    }

    public Boolean getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(Boolean checkTask) {
        this.checkTask = checkTask;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Task{" +
                "libelle='" + libelle + '\'' +
                ", dateTask='" + dateTask + '\'' +
                ", heureTask='" + heureTask + '\'' +
                ", checkTask=" + checkTask +
                '}';
    }


}
