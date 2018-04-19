package movies.flag.pt.moviesapp.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

import movies.flag.pt.moviesapp.R;

/**
 * Created by rafae_000 on 25/01/2018.
 */

public class FirstScreen extends Screen {

    private final String PREFERENCES_NAME = "FlagPreferences";
    private final String FIRST_LOGIN_KEY = "FIRST_LOGIN_KEY";
    ImageButton advFirstScreen;
    TextView textFirstScreen;
    TextView secondText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        boolean isFirstLogin = preferences.getBoolean(FIRST_LOGIN_KEY, true);
        if (isFirstLogin) {
            editor.putBoolean(FIRST_LOGIN_KEY, false);
            editor.commit();
            Toast.makeText(this, getString(R.string.first_screen), Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(FirstScreen.this, MainScreen.class);
            startActivity(intent);
        }
        Log.d("Preferences", "isFirstLogin=" + isFirstLogin);

        String FILENAME = "hello_file.txt";
        String string = "Hello World";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isFirstLogin){
            advFirstScreen = findViewById(R.id.first_screen_advance);
            secondText = findViewById(R.id.first_screen_text_view_secund);
            textFirstScreen = findViewById(R.id.first_screen_text_view);
            advFirstScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FirstScreen.this, MainScreen.class);
                    startActivity(intent);
                }
            });
        }else{
            Intent intent = new Intent(FirstScreen.this, MainScreen.class);
            startActivity(intent);
        }

    }
}
