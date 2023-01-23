package de.heffner_alexander.rechenapp.control;

import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.interfaces.IFormelRechner;
import kotlin.Pair;

public class Rechner implements IFormelRechner {

    private final List<Pair<Double, Double>> data = new LinkedList<>();

    @Override
    public boolean calculateFunction(String formula, double start, double end, double stepSize) {
        List<String> operators = new LinkedList<>();
        List<Double> numbers = new LinkedList<>();
        StringBuilder tempNumber = new StringBuilder();

        for (double i = start; i <= end; i += stepSize) {
            char[] formulaChars = formula.replaceAll("x", String.valueOf(i)).toCharArray();
            for (char c : formulaChars) {
                if (Character.isDigit(c)) tempNumber.append(c);
                else if (c == '.') tempNumber.append(c);
                else {
                    numbers.add(Double.parseDouble(tempNumber.toString()));
                    tempNumber.delete(0, tempNumber.length());
                    operators.add(String.valueOf(c));
                }
            }
            numbers.add(Double.parseDouble(tempNumber.toString()));

            LinkedList<Integer> firstCalcIndexList = new LinkedList<>();
            LinkedList<Integer> secondCalcIndexList = new LinkedList<>();

            for (int j = 0; j < operators.size(); j++) {
                String operator = operators.get(j);
                if (operator.equals("*") || operator.equals("/") || operator.equals("^")) {
                    firstCalcIndexList.add(j);
                } else {
                    secondCalcIndexList.add(j);
                }
            }

            List<Integer> calculationOrder = new LinkedList<>(firstCalcIndexList);
            calculationOrder.addAll(secondCalcIndexList);

            double result = 0.0;

            while (!calculationOrder.isEmpty()) {
                int index = calculationOrder.get(0);
                String operator = operators.get(index);
                double numberOne = numbers.get(index);
                double numberTwo = numbers.get(index+1);

                switch (operator) {
                    case "+":
                        result = numberOne + numberTwo;
                        break;
                    case "-":
                        result = numberOne - numberTwo;
                        break;
                    case "*":
                        result = numberOne * numberTwo;
                        break;
                    case "/":
                        result = numberOne / numberTwo;
                        break;
                    case "^":
                        result = Math.pow(numberOne, numberTwo);
                        break;
                    default:
                        return false;
                }
                numbers.set(index, result);
                numbers.remove(index+1);

                operators.remove(index);
                calculationOrder.remove(0);

                int orderIndex = 0;
                for (int order : calculationOrder) {
                    if (order > index) {
                        calculationOrder.set(orderIndex, order-1);
                    }
                    orderIndex++;
                }
            }
            data.add(new Pair<>(i, result));
        }
        return true;
    }

    @Override
    public List<Pair<Double, Double>> fetchDataSet() {
        return data;
    }
}
