package com.example.day02_exam;

import android.app.Application;

import com.example.day02_exam.db.DaoMaster;
import com.example.day02_exam.db.DaoSession;

public class App extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper name = new DaoMaster.DevOpenHelper(this, "name");
        daoSession = new DaoMaster(name.getWritableDb()).newSession();
    }
}
