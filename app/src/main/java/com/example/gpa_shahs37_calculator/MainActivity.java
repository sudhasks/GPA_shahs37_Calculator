package com.example.gpa_shahs37_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText s1, s2, s3, s4, s5;

    boolean calculateResult = false;
    boolean allValidField = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = (EditText) findViewById(R.id.grade1);
        s2 = (EditText) findViewById(R.id.grade2);
        s3 = (EditText) findViewById(R.id.grade3);
        s4 = (EditText) findViewById(R.id.grade4);
        s5 = (EditText) findViewById(R.id.grade5);




        s1.addTextChangedListener(gpaTextWatcher);
        s2.addTextChangedListener(gpaTextWatcher);
        s3.addTextChangedListener(gpaTextWatcher);
        s4.addTextChangedListener(gpaTextWatcher);
        s5.addTextChangedListener(gpaTextWatcher);

    }


    public void calculateGPA(View view) {
        //validate the content
        allValidField = true;
        validateEmptyField(s1);
        validateEmptyField(s2);
        validateEmptyField(s3);
        validateEmptyField(s4);
        validateEmptyField(s5);

    TextView result = findViewById(R.id.result);

    if (!allValidField){
        result.setText("Please enter each course scores");
        Toast.makeText(this, "Please enter all course scores", Toast.LENGTH_LONG). show();
        return;

    }
    double res = getGPAScore();
        result.setText("Your GPA is " + res);
        ((Button) findViewById(R.id.button1)).setText("");

        s1.setText("");
        s2.setText("");
        s3.setText("");
        s4.setText("");
        s5.setText("");

        s1.setHint("score");
        s2.setHint("score");
        s3.setHint("score");
        s4.setHint("score");
        s5.setHint("score");

        updateBackground(res);
        calculateResult = true;
        Toast.makeText(this, "Successfully GPA calculated", Toast.LENGTH_LONG).show();



    }
   private void validateEmptyField(EditText e) {
        if(e.getText().toString().trim().isEmpty()){
            e.setBackgroundColor(Color.RED);
            allValidField = false;
            return;
        }
        else{
            e.setBackgroundColor(Color.WHITE);

        }
        int i = Integer.valueOf(e.getText().toString());
        if (i < 0 || i > 100) {
            e.setBackgroundColor(Color.RED);
            allValidField = false;
        }


    }

    private double getGPAScore() {
        String g1 = s1.getText().toString();
        String g2 = s2.getText().toString();
        String g3 = s3.getText().toString();
        String g4 = s4.getText().toString();
        String g5 = s5.getText().toString();

        int i1 = Integer.valueOf(g1);
        int i2 = Integer.valueOf(g2);
        int i3 = Integer.valueOf(g3);
        int i4 = Integer.valueOf(g4);
        int i5 = Integer.valueOf(g5);

        return (double) ((i1+i2+i3+i4+i5)/5);
    }
    private void updateBackground(double res) {
        RelativeLayout currentLayout =
                (RelativeLayout) findViewById(R.id.look);
        if(res<60){
            currentLayout.setBackgroundColor(Color.RED);
            //findViewById(R.id.layout).setBackgroundColor(Color.RED);
        }
        else if (res>60 && res<80){
            currentLayout.setBackgroundColor(Color.YELLOW);
            //findViewById(R.id.layout).setBackgroundColor(Color.YELLOW);

        }
        else{
            currentLayout.setBackgroundColor(Color.GREEN);
           // findViewById(R.id.layout).setBackgroundColor(Color.GREEN);
        }

    }




    private TextWatcher gpaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            return;

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            allValidField=true;
            validateEmptyField(s1);
            validateEmptyField(s2);
            validateEmptyField(s3);
            validateEmptyField(s4);
            validateEmptyField(s5);

            if (allValidField && calculateResult){
                ((Button) findViewById(R.id.button1)).setText("Re-Calculate GPA");

            }
            return;

        }
    };


}