package userauthetication.tapumandal.me.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class SharedData{

    public void set(String infoType, String key, String value, Context context){
        SharedPreferences sharedPre = context.getSharedPreferences(infoType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public String get(String infoType, String key, Context context){
        SharedPreferences sharedPre = context.getSharedPreferences(infoType, Context.MODE_PRIVATE);

        return sharedPre.getString(key, "");
    }
}
