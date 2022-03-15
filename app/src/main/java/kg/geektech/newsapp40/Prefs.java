package kg.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.navigation.Navigator;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

    }
//    public void savePhone(){
//        preferences.edit().putBoolean("phoneNumber",true).apply();
//    }

    public void saveBoardState() {
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("board_state", false);
    }

    public void saveName(String saveName) {
        preferences.edit().putString("Name", saveName).apply();
    }

    public String getName() {
        return preferences.getString("Name", "");
    }

    public void clear() {
        preferences.edit().remove("Name").apply();
        preferences.edit().remove("ava").apply();
    }
    public void saveImage (String image) {preferences.edit().putString("ava",image.toString()).apply();}
    public  String getImage(){return preferences.getString("ava","");}

}
