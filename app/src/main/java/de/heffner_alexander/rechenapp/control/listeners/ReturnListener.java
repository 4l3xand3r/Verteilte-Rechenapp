package de.heffner_alexander.rechenapp.control.listeners;

import android.view.View;

import de.heffner_alexander.rechenapp.control.Controller;

public class ReturnListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Controller.returnToStart();
    }
}
