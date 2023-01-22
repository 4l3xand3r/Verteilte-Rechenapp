package de.heffner_alexander.rechenapp.control.listeners;

import android.view.View;
import android.widget.TextView;

import de.heffner_alexander.rechenapp.control.Controller;

public class StartCalculationListener implements View.OnClickListener {

    private final TextView formula;
    private final TextView start;
    private final TextView end;
    private final TextView stepSize;

    public StartCalculationListener(TextView formula, TextView start, TextView end,
                                    TextView stepSize) {
        this.formula = formula;
        this.start = start;
        this.end = end;
        this.stepSize = stepSize;
    }

    @Override
    public void onClick(View view) {
        if ((!formula.getText().toString().isEmpty()) &&
                (!start.getText().toString().isEmpty()) &&
                (!end.getText().toString().isEmpty())
        ) {
            Controller.jumpToLoading();

            Controller.sendCalculations(
                    formula.getText().toString(),
                    Double.parseDouble(start.getText().toString()),
                    Double.parseDouble(end.getText().toString()),
                    Double.parseDouble(stepSize.getText().toString())
            );
        }
    }
}
