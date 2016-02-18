package com.example.stef.response_time_assesment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Stef on 17/2/2016. ***NOT USED ANYMORE***
 */
public class EvaluateDialogFragment extends DialogFragment {

    public String checked="";
    public EvaluateDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EvaluateDialogFragment newInstance(String title) {
        EvaluateDialogFragment frag = new EvaluateDialogFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container);
/*
        final RadioGroup rg = (RadioGroup) view.findViewById(R.id.radioEvaluate);
        //rg.setOnCheckedChangeListener();

        final RadioButton badbutton = (RadioButton) view.findViewById(R.id.radioBad);
        final RadioButton mediocrebutton = (RadioButton) view.findViewById(R.id.radioMediocre);
        final RadioButton goodbutton = (RadioButton) view.findViewById(R.id.radioGood);

        badbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked="bad";
            }
        });

        mediocrebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked="mediocre";
            }
        });

        goodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked="good";
            }
        });

        final Button okbutton = (Button) view.findViewById(R.id.Okbutton);

        okbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checked!="" ){
                    final TextView mytextView2 = (TextView) getParentFragment().getView().findViewById(R.id.mytextView);
                    mytextView2.setText("");

                    final Button evaluate_button2 = (Button) getParentFragment().getView().findViewById(R.id.evaluate_button);
                    evaluate_button2.setClickable(false);
                    evaluate_button2.setVisibility(getView().INVISIBLE);

                    dismiss();
                }
            }
        });
*/
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
