package com.gstcalculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.zero)
    TextView zero;
    @Bind(R.id.one)
    TextView one;
    @Bind(R.id.two)
    TextView two;
    @Bind(R.id.three)
    TextView three;
    @Bind(R.id.four)
    TextView four;
    @Bind(R.id.five)
    TextView five;
    @Bind(R.id.six)
    TextView six;
    @Bind(R.id.seven)
    TextView seven;
    @Bind(R.id.eight)
    TextView eight;
    @Bind(R.id.nine)
    TextView nine;
    @Bind(R.id.point)
    TextView point;

    @Bind(R.id.plus)
    TextView plus;
    @Bind(R.id.minus)
    TextView minus;
    @Bind(R.id.multi)
    TextView multi;
    @Bind(R.id.devide)
    TextView devide;
    @Bind(R.id.mod)
    TextView mod;

    @Bind(R.id.plus_minus)
    TextView plus_minus;

    @Bind(R.id.clear)
    TextView clear;
    @Bind(R.id.del)
    TextView delete;
    @Bind(R.id.history)
    TextView history;
    @Bind(R.id.total_gst)
    TextView total_gst;
    @Bind(R.id.total_text)
    TextView total_text;

    @Bind(R.id.bracket)
    TextView bracket;


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
    @Bind(R.id.second)
    TextView second;
    @Bind(R.id.op)
    TextView op;


    @Bind(R.id.equal)
    TextView equal;
    @Bind(R.id.gst_detail)
    LinearLayout gst_detail;


    @Bind(R.id.inputTextView)
    TextView inputTextView;

    String temp;
    String operator;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        gst_detail.setVisibility(View.INVISIBLE);
        initControlListener();
        gstCalculation();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                try {
                    String s = inputTextView.getText().toString().substring(0, inputTextView.getText().toString().length() - 1);
                    inputTextView.setText(s);
                } catch (Exception e) {

                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                inputTextView.setText("");
                temp = null;
                second.setText("");
                op.setText("");
                total_gst.setText("");
            }
        });

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
            if (inputTextView.getText().toString().isEmpty()) {
                return;
            }
            gst_detail.setVisibility(View.VISIBLE);
            Double d = Double.valueOf(inputTextView.getText().toString());
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
            total_with_gst.setText("" + +(Double.valueOf(inputTextView.getText().toString()) + g));
            total_gst.setText("" + g);

        } catch (Exception e) {

        }
    }

    void gstMinus(double percentage) {
        try {
            if (inputTextView.getText().toString().isEmpty()) {
                return;
            }
            gst_detail.setVisibility(View.VISIBLE);
            Double d = Double.valueOf(inputTextView.getText().toString());

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
            total_with_gst.setText(" " + new DecimalFormat("#.##").format(Double.valueOf(inputTextView.getText().toString()) + g));
            total_gst.setText("" + g);

        } catch (Exception e) {

        }
    }

    private void initControlListener() {
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("9");
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pointIndex = inputTextView.getText().toString().lastIndexOf(".");
                if (pointIndex == -1) {
                    inputTextView.setText(inputTextView.getText().toString().concat("."));
                    return;
                }
                onNumberButtonClicked(".");
            }
        });

        plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double number = Double.parseDouble(inputTextView.getText().toString());
                    if (number > 0) {
                        inputTextView.setText("-" + inputTextView.getText().toString());
                    } else {
                        inputTextView.setText(inputTextView.getText().toString().replace("-", ""));
                    }
                } catch (Exception e) {

                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearButtonClicked();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("-");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("+");
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("X");
            }
        });
        devide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("/");
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("%");
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                second.setText("");
                op.setText("");
                onEqualButtonClicked();
            }
        });

    }

    private void onEqualButtonClicked() {
        double res = 0;
        try {
            double number = Double.valueOf(temp);
            double number2 = Double.valueOf(inputTextView.getText().toString());
            switch (operator) {
                case "+":
                    res = number + number2;
                    break;
                case "/":
                    res = number / number2;
                    break;
                case "-":
                    res = number - number2;
                    break;
                case "X":
                    res = number * number2;
                    break;
                case "%":
                    res = number % number2;
                    break;
            }
            result = String.valueOf(res);
            inputTextView.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void onEqualButtonClicked2(String operator) {
        double res = 0;
        try {
            double number2 = Double.valueOf(inputTextView.getText().toString());
            double number = Double.valueOf(second.getText().toString());
            switch (operator) {
                case "+":
                    res = number + number2;
                    break;
                case "/":
                    res = number / number2;
                    break;
                case "-":
                    res = number - number2;
                    break;
                case "X":
                    res = number * number2;
                    break;
                case "%":
                    res = number % number2;
                    break;
            }
            result = String.valueOf(res);
            inputTextView.setText(result);
            op.setText("");
            second.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onOperatorButtonClicked(String operator) {
        gst_detail.setVisibility(View.INVISIBLE);
        total_gst.setText("");
        this.operator = operator;
        onEqualButtonClicked2(op.getText().toString());
        op.setText(operator);
        if (inputTextView.getText().toString().isEmpty()) {
            return;
        }
        temp = inputTextView.getText().toString();
        onClearButtonClicked();

    }

    private void onClearButtonClicked() {
        result = "";
        second.setText(inputTextView.getText().toString());
        inputTextView.setText("");

    }

    private void onNumberButtonClicked(String pos) {
        result = inputTextView.getText().toString();
        result = result + pos;
        inputTextView.setText(result);
    }

    void ab() {
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                inputTextView.setText(inputTextView.getText().toString().concat("0"));
                onNumberButtonClicked("0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                    inputTextView.setText(inputTextView.getText().toString().concat("1"));
                onNumberButtonClicked("1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                    inputTextView.setText(inputTextView.getText().toString().concat("2"));
                onNumberButtonClicked("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                inputTextView.setText(inputTextView.getText().toString().concat("3"));
                onNumberButtonClicked("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                inputTextView.setText(inputTextView.getText().toString().concat("4"));
                onNumberButtonClicked("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                inputTextView.setText(inputTextView.getText().toString().concat("5"));

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                inputTextView.setText(inputTextView.getText().toString().concat("6"));
                onNumberButtonClicked("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
//                inputTextView.setText(inputTextView.getText().toString().concat("7"));
                onNumberButtonClicked("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                inputTextView.setText(inputTextView.getText().toString().concat("8"));
                onNumberButtonClicked("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                inputTextView.setText(inputTextView.getText().toString().concat("5/"));
                onNumberButtonClicked("9");
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gst_detail.setVisibility(View.INVISIBLE);
                int pointIndex = inputTextView.getText().toString().lastIndexOf(".");
                if (pointIndex == -1) {
                    inputTextView.setText(inputTextView.getText().toString().concat("."));
                    return;
                }
//                if (plusIndex > pointIndex || minusIndex > pointIndex || multiIndex > pointIndex || devideIndex > pointIndex) {
//                    inputTextView.setText(inputTextView.getText().toString().concat("."));
//                }
            }
        });
//
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String subString = inputTextView.getText().toString().substring(inputTextView.getText().toString().length() - 1);
                    if (!subString.equals("+") && !subString.equals("-") && !subString.equals("*") && !subString.equals("/")) {
                        inputTextView.setText(inputTextView.getText().toString().concat("+"));
                    }
                    inputTextView.getText().toString().substring(0, inputTextView.getText().toString().indexOf("+"));

                } catch (Exception e) {

                }

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String subString = inputTextView.getText().toString().substring(inputTextView.getText().toString().length() - 1);
                    if (!subString.equals("+") && !subString.equals("-") && !subString.equals("*") && !subString.equals("/")) {
                        inputTextView.setText(inputTextView.getText().toString().concat("-"));
                    }

                } catch (Exception e) {

                }

            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String subString = inputTextView.getText().toString().substring(inputTextView.getText().toString().length() - 1);
                    if (!subString.equals("+") && !subString.equals("-") && !subString.equals("*") && !subString.equals("/")) {
                        inputTextView.setText(inputTextView.getText().toString().concat("*"));
                    }
                } catch (Exception e) {

                }
            }
        });

        devide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String subString = inputTextView.getText().toString().substring(inputTextView.getText().toString().length() - 1);
                    if (!subString.equals("+") && !subString.equals("-") && !subString.equals("*") && !subString.equals("/")) {
                        inputTextView.setText(inputTextView.getText().toString().concat("/"));
                    }
                } catch (Exception e) {

                }
            }
        });

        bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = inputTextView.getText().toString();
                int i = s.lastIndexOf("(");
                int j = s.lastIndexOf(")");
                if (i > j) {
                    if (!inputTextView.getText().toString().substring(inputTextView.getText().toString().length() - 1).equals("(")) {
                        inputTextView.setText(inputTextView.getText().toString().concat(")"));
                    }
                } else if (i < j) {
                    inputTextView.setText(inputTextView.getText().toString().concat("("));
                } else {
                    inputTextView.setText(inputTextView.getText().toString().concat("("));
                }

            }
        });
    }

}
