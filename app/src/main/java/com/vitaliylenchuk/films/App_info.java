package com.vitaliylenchuk.films;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class App_info extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_info)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
