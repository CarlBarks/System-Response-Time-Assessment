package com.example.stef.response_time_assesment;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Stef on 11/2/2016.
 */
public class FirstScreenFragment extends Fragment {

    public int counter=0;
    public String checked="";
    int[] myList = {10,50,100,200,300,400,500,700,1000,2000}; //Time intervals for delay in miliseconds

    public FirstScreenFragment(){
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_screen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        shuffleArray(myList); //shuffle our time interval array for random delays

        //Initialise button and add listener
        final Button show_button = (Button)this.getActivity().findViewById(R.id.show_button);
        show_button.setClickable(true);

        final TextView mytextView = (TextView)getView().findViewById(R.id.mytextView);

        final RadioGroup rg = (RadioGroup) getView().findViewById(R.id.radioEvaluate);
        final RadioButton badbutton = (RadioButton) getView().findViewById(R.id.radioBad);
        final RadioButton mediocrebutton = (RadioButton) getView().findViewById(R.id.radioMediocre);
        final RadioButton goodbutton = (RadioButton) getView().findViewById(R.id.radioGood);

        //Hide the evaluation radio buttons
        rg.setVisibility(getView().INVISIBLE);
        rg.setClickable(false);

        /*When show button is pressed*/
        show_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new showTextTask().execute();
                // evaluate_button.setClickable(true);
                //evaluate_button.setVisibility(getView().VISIBLE);
            }
        });

        /*** Listeners for all 3 radio buttons ***/

        badbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new handleEvaluationTask().execute("Bad");
            }
        });

        mediocrebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new handleEvaluationTask().execute("Mediocre");
            }
        });

        goodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new handleEvaluationTask().execute("Good");
            }
        });
    }

    /*method for shuffling our int[] array*/
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    /*AsyncTask responsible for handling the UI changes and writing data to .txt
     *when radio Buttons are pressed      */
    private class handleEvaluationTask extends AsyncTask<String, Void, Integer> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        @Override
        protected Integer doInBackground(String... params) {
            checked = params[0];
            WriteToFile();
            counter++;
            if (counter>=10) {
                shuffleArray(myList);
            }
            return 1;
        }

        /** The system calls this to perform work in the UI thread **/
        protected void onPostExecute(Integer i) {
            if (checked.equals("Bad")){
                final RadioButton badbutton = (RadioButton) getView().findViewById(R.id.radioBad);
                badbutton.setChecked(false);

            }else if (checked.equals("Mediocre")){
                final RadioButton mediocrebutton = (RadioButton) getView().findViewById(R.id.radioMediocre);
                mediocrebutton.setChecked(false);

            }else if(checked.equals("Good")){
                final RadioButton goodbutton = (RadioButton) getView().findViewById(R.id.radioGood);
                goodbutton.setChecked(false);
            }

            final TextView mytextView = (TextView)getView().findViewById(R.id.mytextView);
            mytextView.setText(R.string.oath);

            final RadioGroup rg = (RadioGroup) getView().findViewById(R.id.radioEvaluate);
            rg.setVisibility(getView().VISIBLE);
            rg.setClickable(true);

            final Button show_button = (Button)getView().findViewById(R.id.show_button);
            show_button.setClickable(true);

            rg.setVisibility(getView().INVISIBLE);
            rg.setClickable(false);
            mytextView.setText("");
            if (counter>=10) {
                Context context = getActivity().getApplicationContext();
                CharSequence text = "10 Trials Completed! Restarting!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                counter=0;
            }
        }
    }

    /*AsyncTask responsible for handling the UI changes
     *when the 'Show Text' Button is pressed      */
    private class showTextTask extends AsyncTask<Void, Void, Integer> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        @Override
        protected Integer doInBackground(Void... params) {
            SystemClock.sleep(myList[counter]);//Time delay
            return 1;
        }

        /** The system calls this to perform work in the UI thread **/
        protected void onPostExecute(Integer i) {
            final TextView mytextView = (TextView)getView().findViewById(R.id.mytextView);
            mytextView.setText(R.string.oath);

            final RadioGroup rg = (RadioGroup) getView().findViewById(R.id.radioEvaluate);
            rg.setVisibility(getView().VISIBLE);
            rg.setClickable(true);

            final Button show_button = (Button)getView().findViewById(R.id.show_button);
            show_button.setClickable(false);
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /*Method responsible for writing data to .txt file*/
    public void WriteToFile() {
        if (isExternalStorageWritable()) {
            try {
                File newFolder = new File(Environment.getExternalStorageDirectory(), "ResponseTimeAssessment");
                if (!newFolder.exists()) {
                    newFolder.mkdir();
                }
                try {
                    File file = new File(newFolder, "Data" + ".txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    try {
                        FileOutputStream fOut = new FileOutputStream(file, true);
                        OutputStreamWriter myOutWriter =
                                new OutputStreamWriter(fOut);
                        myOutWriter.append(counter+" Delay: "+myList[counter]+" Assessment: "+checked+"\n");
                        myOutWriter.close();
                        fOut.close();
                    } catch (Exception ex) {
                        System.out.println("ex: " + ex);
                    }

                } catch (Exception e) {
                    System.out.println("e: " + e);
                }
            } catch (Exception e) {
                System.out.println("e: " + e);
            }

        }
    }

}