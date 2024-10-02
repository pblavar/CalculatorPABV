package com.example.calculatorpabv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDisplay1, tvDisplay2;
    private double value1 = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;
    private boolean isNewOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los botones
        Button btnResult = findViewById(R.id.result);
        Button btnAllClear = findViewById(R.id.allclear);
        Button btnClear = findViewById(R.id.clear);
        Button btnOne = findViewById(R.id.one);
        Button btnTwo = findViewById(R.id.two);
        Button btnThree = findViewById(R.id.three);
        Button btnFour = findViewById(R.id.four);
        Button btnFive = findViewById(R.id.five);
        Button btnSix = findViewById(R.id.six);
        Button btnSeven = findViewById(R.id.seven);
        Button btnEight = findViewById(R.id.eight);
        Button btnNine = findViewById(R.id.nine);
        Button btnZero = findViewById(R.id.zero);
        Button btnPoint = findViewById(R.id.point);
        Button btnPlus = findViewById(R.id.plus);
        Button btnMinus = findViewById(R.id.minus);
        Button btnMultiplication = findViewById(R.id.multiplication);
        Button btnDivision = findViewById(R.id.division);

        tvDisplay1 = findViewById(R.id.display1);
        tvDisplay2 = findViewById(R.id.display2);

        // Establecemos OnClickListener para todos los botones
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiplication.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btnAllClear.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId(); // Obtener el ID del botón presionado

        if (id == R.id.one) {
            appendNumber("1");
        } else if (id == R.id.two) {
            appendNumber("2");
        } else if (id == R.id.three) {
            appendNumber("3");
        } else if (id == R.id.four) {
            appendNumber("4");
        } else if (id == R.id.five) {
            appendNumber("5");
        } else if (id == R.id.six) {
            appendNumber("6");
        } else if (id == R.id.seven) {
            appendNumber("7");
        } else if (id == R.id.eight) {
            appendNumber("8");
        } else if (id == R.id.nine) {
            appendNumber("9");
        } else if (id == R.id.zero) {
            appendNumber("0");
        } else if (id == R.id.point) {
            appendNumber(".");
        } else if (id == R.id.plus) {
            operatorPressed("+");
        } else if (id == R.id.minus) {
            operatorPressed("-");
        } else if (id == R.id.multiplication) {
            operatorPressed("*");
        } else if (id == R.id.division) {
            operatorPressed("/");
        } else if (id == R.id.result) {
            calculateResult();
        } else if (id == R.id.allclear) {
            clearAll();
        } else if (id == R.id.clear) {
            tvDisplay2.setText("0");
        }
    }

    // Método para añadir números al display2
    private void appendNumber(String number) {
        if (isNewOperation) {
            tvDisplay2.setText(number);
            isNewOperation = false;
            isOperatorPressed = false;
        } else if (isOperatorPressed) {
            tvDisplay2.setText(number);
            isOperatorPressed = false;
        } else {
            String currentText = tvDisplay2.getText().toString();
            if (currentText.equals("0")) {
                tvDisplay2.setText(number); // Reemplazamos si es un 0 inicial
            } else {
                tvDisplay2.append(number); // Añadimos el nuevo número
            }
        }
    }

    // Método que maneja la pulsación de los operadores
    private void operatorPressed(String operatorPressed) {
        if (!operator.isEmpty()) {
            calculatePartialResult(); // Calculamos el resultado parcial
        } else {
            value1 = Double.parseDouble(tvDisplay2.getText().toString()); // Guardamos el primer valor
        }
        operator = operatorPressed; // Guardamos el operador actual
        tvDisplay1.setText(tvDisplay2.getText().toString() + " " + operator); // Mostramos en display1
        isOperatorPressed = true; // Indicamos que un operador fue presionado
    }

    // Método para calcular el resultado
    private void calculateResult() {
        calculatePartialResult();
        operator = ""; // Limpiamos el operador
        isNewOperation = true; // Permitimos iniciar una nueva operación
        tvDisplay1.setText("");
    }

    // Método para calcular el resultado parcial cuando se presiona un operador después de otro
    private void calculatePartialResult() {
        double value2 = Double.parseDouble(tvDisplay2.getText().toString());
        double result;
        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                if (value2 != 0) {
                    result = value1 / value2;
                } else {
                    result = 0; // Para evitar división por 0
                }
                break;
            default:
                result = 0;
        }
        value1 = result; // Actualizamos value1 con el resultado parcial
        tvDisplay2.setText(String.valueOf(result)); // Mostrar el resultado parcial en display2
    }

    // Método para limpiar
    private void clearAll() {
        tvDisplay1.setText("");
        tvDisplay2.setText("0");
        value1 = 0;
        operator = "";
        isOperatorPressed = false;
        isNewOperation = false;
    }
}
