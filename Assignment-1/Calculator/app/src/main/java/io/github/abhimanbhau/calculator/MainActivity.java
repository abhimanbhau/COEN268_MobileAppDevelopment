package io.github.abhimanbhau.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button plusButton, minusButton, divideButton, multiplyButton, modButton;
    TextView firstNumber, secondNumber, resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        divideButton = findViewById(R.id.divideButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        modButton = findViewById(R.id.modButton);
        resultText = (TextView) findViewById(R.id.resultText);
        firstNumber = (EditText) findViewById(R.id.firstNumber);
        secondNumber = (EditText) findViewById(R.id.secondNumber);

        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        modButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double first = Integer.valueOf(firstNumber.getText().toString());
        double second = Integer.valueOf(secondNumber.getText().toString());
        if (view.getId() == R.id.plusButton) {
            resultText.setText(String.valueOf(first + second));
        }

        if (view.getId() == R.id.minusButton) {
            resultText.setText(String.valueOf(first - second));
        }

        if (view.getId() == R.id.divideButton) {
            if (second == 0) {
                Toast.makeText(this, "DivByZeroExc", Toast.LENGTH_SHORT).show();
                return;
            }
            resultText.setText(String.valueOf(first / second));
        }

        if (view.getId() == R.id.multiplyButton) {
            resultText.setText(String.valueOf(first * second));
        }

        if (view.getId() == R.id.modButton) {
            resultText.setText(String.valueOf(first % second));
        }
    }
}
