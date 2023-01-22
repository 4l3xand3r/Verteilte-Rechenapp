package de.heffner_alexander.rechenapp.classes;

import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.interfaces.IFormelRechner;
import kotlin.Pair;

public class Rechner implements IFormelRechner {

    private final List<Pair<Double, Double>> data = new LinkedList<>();

    @Override
    public boolean calculateFunction(String formula, int start, int end, double stepSize) {
        try {
            double dFirstNumber = 0;
            double dSecondNumber = 0;
            boolean bFirstNumberSet = false;
            String sOperation = "";

            for (double i = start; i <= end; i += stepSize) {
                String tempFormula = formula.replace("x", String.valueOf(i));

                char[] chars = tempFormula.toCharArray();
                StringBuilder numberStorage = new StringBuilder();

                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        numberStorage.append(c);
                    } else if (c == '.') {
                        numberStorage.append(c);
                    } else {
                        if (!bFirstNumberSet) {
                            dFirstNumber = Double.parseDouble(numberStorage.toString());
                            numberStorage = new StringBuilder();
                            bFirstNumberSet = true;
                        } else {
                            dSecondNumber = Double.parseDouble(numberStorage.toString());
                            numberStorage = new StringBuilder();
                            switch (sOperation) {
                                case "+":

                                    dFirstNumber += dSecondNumber;

                                    break;

                                case "-":

                                    dFirstNumber -= dSecondNumber;

                                    break;

                                case "*":

                                    dFirstNumber *= dSecondNumber;

                                    break;

                                case "/":

                                    dFirstNumber /= dSecondNumber;

                                    break;

                                case "^":

                                    dFirstNumber = Math.pow(dFirstNumber, dSecondNumber);

                                    break;

                                default:
                                    return false;
                            }
                        }

                        sOperation = String.valueOf(c);
                    }
                }
                dSecondNumber = Double.parseDouble(numberStorage.toString());
                switch (sOperation) {
                    case "+":

                        dFirstNumber += dSecondNumber;

                        break;

                    case "-":

                        dFirstNumber -= dSecondNumber;

                        break;

                    case "*":

                        dFirstNumber *= dSecondNumber;

                        break;

                    case "/":

                        dFirstNumber /= dSecondNumber;

                        break;

                    case "^":

                        dFirstNumber = Math.pow(dFirstNumber, dSecondNumber);

                        break;

                    default:
                        return false;
                }
                data.add(new Pair<>(i, dFirstNumber));
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Pair<Double, Double>> fetchDataSet() {
        return data;
    }
}
