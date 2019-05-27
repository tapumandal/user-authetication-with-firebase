package userauthetication.tapumandal.me.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedData extends Activity {

    public void set(String infoType, String key, String value){
        SharedPreferences sharedPre = getSharedPreferences(infoType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public String get(String infoType, String key){
        SharedPreferences sharedPre = getSharedPreferences(infoType, Context.MODE_PRIVATE);

        return sharedPre.getString(key, "");
    }
}
