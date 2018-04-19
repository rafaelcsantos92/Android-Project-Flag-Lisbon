package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;

import movies.flag.pt.moviesapp.R;

/**
 * Created by rafae_000 on 16/01/2018.
 */

public class SplashScreen extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SplashScreen.this, FirstScreen.class);
                startActivity(intent);
                finish();
            }
        });
        thread.start();
    }
}
