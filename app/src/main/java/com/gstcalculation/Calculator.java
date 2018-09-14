package com.gstcalculation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btnDot;
    Button btnDiv, btnAdd, btnSub, btnMul, btnEquals;
    Button btnClear, btnDel;
    Button btnBOpen, btnBClose;

    TextView curOperand;
    String curOpText;


    boolean operatorRecState,operandRecState;
    boolean dotEnabled;
    boolean enableOpenBracket;


    ControlListener controlListener;
    NumericListener numericListener;
    OperatorListener operatorListener;

    int bracketCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViews();
        setListeners();

        clearValues();

    }

    void findViews() {

        curOperand = (TextView) findViewById(R.id.curOperand);

        btn0 = (Button) findViewById(R.id.btnZero);
        btn1 = (Button) findViewById(R.id.btnOne);
        btn2 = (Button) findViewById(R.id.btnTwo);
        btn3 = (Button) findViewById(R.id.btnThree);
        btn4 = (Button) findViewById(R.id.btnFour);
        btn5 = (Button) findViewById(R.id.btnFive);
        btn6 = (Button) findViewById(R.id.btnSix);
        btn7 = (Button) findViewById(R.id.btnSeven);
        btn8 = (Button) findViewById(R.id.btnEight);
        btn9 = (Button) findViewById(R.id.btnNine);
        btnDot = (Button) findViewById(R.id.btnDot);

        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEquals = (Button) findViewById(R.id.btnEqual);


        btnClear = (Button) findViewById(R.id.btnClear);
        btnDel = (Button) findViewById(R.id.btnDel);

        btnBOpen = (Button) findViewById(R.id.btnBOpen);
        btnBClose = (Button) findViewById(R.id.btnBClose);

    }

    void setListeners() {
        controlListener = new ControlListener();
        numericListener = new NumericListener();
        operatorListener = new OperatorListener();

        btn0.setOnClickListener(numericListener);
        btn1.setOnClickListener(numericListener);
        btn2.setOnClickListener(numericListener);
        btn3.setOnClickListener(numericListener);
        btn4.setOnClickListener(numericListener);
        btn5.setOnClickListener(numericListener);
        btn6.setOnClickListener(numericListener);
        btn7.setOnClickListener(numericListener);
        btn8.setOnClickListener(numericListener);
        btn9.setOnClickListener(numericListener);
        btnDot.setOnClickListener(numericListener);

        btnAdd.setOnClickListener(operatorListener);
        btnSub.setOnClickListener(operatorListener);
        btnMul.setOnClickListener(operatorListener);
        btnDiv.setOnClickListener(operatorListener);
        btnBOpen.setOnClickListener(operatorListener);
        btnBClose.setOnClickListener(operatorListener);

        btnEquals.setOnClickListener(controlListener);
        btnClear.setOnClickListener(controlListener);
        btnDel.setOnClickListener(controlListener);

    }

    void clearValues() {
        curOpText = "";
        dotEnabled=true;
        bracketCount=0;
        operandRecState = true;
        operatorRecState = false;
        enableOpenBracket = true;
        refreshView();

    }

    final void refreshView() {

        curOperand.setText(curOpText);

    }




    boolean isEvaluable() {
        boolean flag = true;
        if(curOpText.equals("")) return false;
        if(bracketCount>0) return false;
        if(!operatorRecState) return false;

        return true;
    }


    class NumericListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String append = "a";

            //Toast.makeText(Calculator.this, "Numeric Keypad", Toast.LENGTH_SHORT).show();

            if (v == btn0) {
                if (!curOpText.equals("0"))
                    append = "0";
                else return;
            } else if (v == btn1) {
                append = "1";
            } else if (v == btn2) append = "2";
            else if (v == btn3) append = "3";
            else if (v == btn4) append = "4";
            else if (v == btn5) append = "5";
            else if (v == btn6) append = "6";
            else if (v == btn7) append = "7";
            else if (v == btn8) append = "8";
            else if (v == btn9) append = "9";
            else if (v == btnDot) {
                if(dotEnabled) {
                    if(curOpText.equals("")) append="0.";
                    else if(!EvalExp.isDig(curOpText.charAt((curOpText.length())-1))) append="0.";
                    else append = ".";
                    dotEnabled=false;
                }
                else return;
            }


            curOpText = curOpText.concat(append);
            operatorRecState = true;
            enableOpenBracket = false;
            refreshView();
        }
    }

    class OperatorListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tempOperator="+";

            if (v == btnAdd) {
                tempOperator = "+";
                enableOpenBracket=true;
            }

            else if (v == btnSub) {

                if(curOpText.equals("")) { //in case user attempts to enter a negative value
                    curOpText="-";
                    refreshView();
                    return;
                }

                else tempOperator = "-";

                enableOpenBracket=true;
            }

            else if (v == btnMul) {
                tempOperator = "*";
                enableOpenBracket=true;
                //Toast.makeText(Calculator.this, "Multiply selected", Toast.LENGTH_SHORT).show();
            }

            else if (v == btnDiv) {
                tempOperator = "/";
                enableOpenBracket=true;
            }

            else if (v == btnBOpen && enableOpenBracket) {
                tempOperator ="(";
                bracketCount++;
                enableOpenBracket=true;
                curOpText = curOpText.concat(tempOperator);
                refreshView();
                return;

            }

            else if( v == btnBClose && bracketCount>0) {
                tempOperator=")";
                bracketCount--;
                curOpText = curOpText.concat(tempOperator);
                operatorRecState=true;
                refreshView();
                return;

            }
            else return;

            if(operatorRecState) {
                curOpText = curOpText.concat(tempOperator);
                operatorRecState=false;
                dotEnabled=true;

                refreshView();
            }
        }
    }

    class ControlListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            boolean performRefresh=true;
            if(v==btnClear) {
                clearValues();
            }

            if(v==btnDel) {
                if (curOpText.length() > 0) {
                    if(!EvalExp.isDig(curOpText.charAt(curOpText.length()-1))) operatorRecState=true;
                    curOpText = curOpText.substring(0, curOpText.length() - 1);
                    if(curOpText.equals("")) operatorRecState=false;
                    else if(!EvalExp.isDig(curOpText.charAt(curOpText.length()-1)))
                        operatorRecState=false;
                }
            }

            if(v==btnEquals) {
                //Toast.makeText(Calculator2.this, "Equal pressed", Toast.LENGTH_SHORT).show();
                if(isEvaluable()) {
                    EvalExp.Result res = EvalExp.evalString(curOpText);
                    if (!res.isInvalid) {
                        curOpText = res.toString();
                        operatorRecState = true;

                    }
                    else {
                        clearValues();
                        curOperand.setText("error");
                        return;
                    }
                }

                else Toast.makeText(Calculator.this, "Not evaluable", Toast.LENGTH_SHORT).show();
                performRefresh=true ;


            }
            if(performRefresh) refreshView();
        }
    }



}
