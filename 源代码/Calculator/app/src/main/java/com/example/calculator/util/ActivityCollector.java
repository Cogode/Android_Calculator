package com.example.calculator.util;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityCollector {
    private static ArrayList<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for(int i = 0; i < activities.size(); i ++) {
            activities.get(i).finish();
        }
        activities.clear();
    }

    public static ArrayList<Activity> activities() {
        return activities;
    }
}
