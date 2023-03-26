package com.damlacim.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.damlacim.calculator.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {
    private String operand1 = "";
    private String operator = "";
    private String operand2 = "";
    private FragmentCalculatorBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi();
    }

    private void calculator() {
        double result = 0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case "-":
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case "*":
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case "/":
                result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;
        }
        operand1 = "";
        operand2 = "";
        operator = "";

        if (binding.tvDisplay.getText().toString().contains(".")) {
            binding.tvResult.setText(String.valueOf(result));
        } else {
            binding.tvResult.setText(String.valueOf((int) result));
        }
    }

    private void setOperand1(String number) {
        if (operator.isEmpty()) {
            operand1 += number;
        } else {
            operand2 += number;
        }
        updateDisplay();
    }

    private void setOperator(String operator) {
        if (!this.operator.isEmpty()) {
            calculator();
        }
        this.operator = operator;
        updateDisplay();
    }

    private void clear() {
        if (!operand2.isEmpty()) {
            operand2 = "";
        } else if (!operator.isEmpty()) {
            operator = "";
        } else if (!operand1.isEmpty()) {
            operand1 = "";
        }
        updateDisplay();
    }


    private void setupUi() {
        binding.btnOne.setOnClickListener(v -> setOperand1("1"));
        binding.btnTwo.setOnClickListener(v -> setOperand1("2"));
        binding.btnThree.setOnClickListener(v -> setOperand1("3"));
        binding.btnFour.setOnClickListener(v -> setOperand1("4"));
        binding.btnFive.setOnClickListener(v -> setOperand1("5"));
        binding.btnSix.setOnClickListener(v -> setOperand1("6"));
        binding.btnSeven.setOnClickListener(v -> setOperand1("7"));
        binding.btnEight.setOnClickListener(v -> setOperand1("8"));
        binding.btnNine.setOnClickListener(v -> setOperand1("9"));
        binding.btnZero.setOnClickListener(v -> setOperand1("0"));
        binding.btnPlus.setOnClickListener(v -> setOperator("+"));
        binding.btnMinus.setOnClickListener(v -> setOperator("-"));

        binding.btnMultiply.setOnClickListener(v -> setOperator("*"));
        binding.btnDivide.setOnClickListener(v -> setOperator("/"));
        binding.btnClear.setOnClickListener(v -> clear());
        binding.btnEqual.setOnClickListener(v -> calculator());
        binding.btnPlusMinus.setOnClickListener(v -> setPlusOrMinus());
        binding.btnDot.setOnClickListener(v -> setDot());

    }

    private void updateDisplay() {
        String display = operand1;
        if (!operator.isEmpty()) {
            display += " " + operator + " " + operand2;
        }
        binding.tvDisplay.setText(display);
    }

    private void setDot() {
        if (operator.isEmpty() && !operand1.contains(".")) {
            operand1 += ".";
        } else if (!operator.isEmpty() && !operand2.contains(".")) {
            operand2 += ".";
        }
        updateDisplay();
    }

    private void setPlusOrMinus() {
        if (operator.isEmpty() && operand1.contains("-")) {
            operand1 = operand1.replace("-", "");
        } else if (!operator.isEmpty() && operand2.contains("-")) {
            operand2 = operand2.replace("-", "");
        } else if (!operator.isEmpty() && !operand2.contains("-")) {
            operand2 = "-" + operand2;
        } else if (operator.isEmpty() && !operand1.contains("-")) {
            operand1 = "-" + operand1;
        }

        updateDisplay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}