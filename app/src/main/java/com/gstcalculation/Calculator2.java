package com.gstcalculation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Calculator2 extends AppCompatActivity {

    TextView btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btnDot;
    TextView btnDiv, btnAdd, btnSub, btnMul, btnEquals;
    TextView btnClear, btnDel;
    TextView btnBOpen, btnBClose;
    TextView curOperand;
    String curOpText;


    boolean operatorRecState,operandRecState;
    boolean dotEnabled;
    boolean enableOpenBracket;


    ControlListener controlListener;
    NumericListener numericListener;
    OperatorListener operatorListener;

    int bracketCount;

    @Bind(R.id.history)
    TextView history;
    @Bind(R.id.total_gst)
    TextView total_gst;
    @Bind(R.id.total_text)
    TextView total_text;


    @Bind(R.id.gst_5)
    TextView gst_5;
    @Bind(R.id.gst_12)
    TextView gst_12;
    @Bind(R.id.gst_18)
    TextView gst_18;
    @Bind(R.id.gst_28)
    TextView gst_28;
    @Bind(R.id.gst_m5)
    TextView gst_m5;
    @Bind(R.id.gst_m12)
    TextView gst_m12;
    @Bind(R.id.gst_m18)
    TextView gst_m18;
    @Bind(R.id.gst_m28)
    TextView gst_m28;


    @Bind(R.id.cgst)
    TextView cgst;
    @Bind(R.id.sgst)
    TextView sgst;
    @Bind(R.id.gst)
    TextView gst;
    @Bind(R.id.cgst_text)
    TextView cgst_text;
    @Bind(R.id.sgst_text)
    TextView sgst_text;
    @Bind(R.id.gst_text)
    TextView gst_text;
    @Bind(R.id.total_with_gst)
    TextView total_with_gst;

    @Bind(R.id.gst_detail)
    LinearLayout gst_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        findViews();
        setListeners();
        clearValues();
        gstCalculation();
        total_gst.setText("");
        gst_detail.setVisibility(View.INVISIBLE);

    }

    void findViews() {

        curOperand = (TextView) findViewById(R.id.inputTextView);
        btn0 = (TextView) findViewById(R.id.zero);
        btn1 = (TextView) findViewById(R.id.one);
        btn2 = (TextView) findViewById(R.id.two);
        btn3 = (TextView) findViewById(R.id.three);
        btn4 = (TextView) findViewById(R.id.four);
        btn5 = (TextView) findViewById(R.id.five);
        btn6 = (TextView) findViewById(R.id.six);
        btn7 = (TextView) findViewById(R.id.seven);
        btn8 = (TextView) findViewById(R.id.eight);
        btn9 = (TextView) findViewById(R.id.nine);
        btnDot = (TextView) findViewById(R.id.point);

        btnMul = (TextView) findViewById(R.id.multi);
        btnDiv = (TextView) findViewById(R.id.divide);
        btnSub = (TextView) findViewById(R.id.minus);
        btnAdd = (TextView) findViewById(R.id.plus);
        btnEquals = (TextView) findViewById(R.id.equal);


        btnClear = (TextView) findViewById(R.id.clear);
        btnDel = (TextView) findViewById(R.id.del);

        btnBOpen = (TextView) findViewById(R.id.bracketOpen);
        btnBClose = (TextView) findViewById(R.id.bracketClose);

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
        total_gst.setText("");
        gst_detail.setVisibility(View.INVISIBLE);
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
                gst_detail.setVisibility(View.INVISIBLE);
                //Toast.makeText(Calculator2.this, "Equal pressed", Toast.LENGTH_SHORT).show();
                if(isEvaluable()) {
                    EvalExp.Result res = EvalExp.evalString(curOpText);
                    try {
                        if (!res.isInvalid) {
                            curOpText = res.toString();
                            operatorRecState = true;

                        }
                        else {
                            clearValues();
                            curOperand.setText("error");
                            return;
                        }
                    }catch (Exception e){

                    }

                }

                else Toast.makeText(Calculator2.this, "Not evaluable", Toast.LENGTH_SHORT).show();
                performRefresh=true ;


            }
            if(performRefresh) refreshView();
        }
    }





    void gstCalculation() {
        gst_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst(5);
            }
        });

        gst_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst(12);

            }
        });

        gst_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst(18);

            }
        });

        gst_28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst(28);

            }
        });

        gst_m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstMinus(5);
            }
        });

        gst_m12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstMinus(12);

            }
        });

        gst_m18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstMinus(18);

            }
        });

        gst_m28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstMinus(28);

            }
        });

        gst_m28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gstMinus(28);

            }
        });
    }



    void gst(double percentage) {
        try {
            if (curOperand.getText().toString().isEmpty()) {
                return;
            }
            gst_detail.setVisibility(View.VISIBLE);
            Double d = Double.valueOf(curOperand.getText().toString());
            double g = (d * percentage) / 100;
            double c = g / 2;
            double s = g / 2;

            double half = percentage / 2;
            cgst_text.setText("CGST [ " + half + "% ]");
            sgst_text.setText("SGST [ " + half + "% ]\"");
            gst_text.setText("GST [ " + percentage + "% ]");

            cgst.setText("" + c);
            sgst.setText("" + s);
            gst.setText("" + g);
            total_text.setText("TOTAL " + d + " + " + g);
            total_with_gst.setText("" + +(Double.valueOf(curOperand.getText().toString()) + g));
            total_gst.setText("" + g);

        } catch (Exception e) {

        }
    }

    void gstMinus(double percentage) {
        try {
            if (curOperand.getText().toString().isEmpty()) {
                return;
            }
            gst_detail.setVisibility(View.VISIBLE);
            Double d = Double.valueOf(curOperand.getText().toString());

            double g = -(d - (d / (100 + percentage)) * 100);
            double c = g / 2;
            double s = g / 2;

            double half = percentage / 2;
            cgst_text.setText("CGST [ " + half + "% ]");
            sgst_text.setText("SGST [ " + half + "% ]");
            gst_text.setText("GST [ " + percentage + "% ]");

            cgst.setText("" + new DecimalFormat("#.##").format(c));
            sgst.setText("" + new DecimalFormat("#.##").format(s));
            gst.setText("" + new DecimalFormat("#.##").format(g));
            total_text.setText("TOTAL " + new DecimalFormat("#.##").format(d) + " " + new DecimalFormat("#.##").format(g));
            total_with_gst.setText(" " + new DecimalFormat("#.##").format(Double.valueOf(curOperand.getText().toString()) + g));
            total_gst.setText("" + g);

        } catch (Exception e) {

        }
    }



}
