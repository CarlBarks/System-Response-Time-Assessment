package com.example.stef.response_time_assesment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Call Fragment Manager and initialise screen with FirstScreenFragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstScreenFragment fragment = new FirstScreenFragment();
        fragmentTransaction.add(R.id.start, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mWakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mWakeLock.release();
    }

}
