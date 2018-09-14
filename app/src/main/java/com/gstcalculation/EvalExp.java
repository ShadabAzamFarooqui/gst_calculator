package com.gstcalculation;

import java.util.Stack;

public class EvalExp {


    static Stack<Operand> operandStack;
    static Stack<Operator> operatorStack;


    static Result evalString(String exp) {
        char[] expA;
        float result = 0 ;

        exp = "(" + exp + ")";
        expA = exp.toCharArray();

        operandStack = new Stack<Operand>();
        operatorStack = new Stack<Operator>();

        int i=0,j=0;

        while(i<exp.length())
        {
            if(expA[i] == '(') {
                operatorStack.push(new Operator('('));
                //log("Open Par");
                i++;
            }

            else if(expA[i] == ')') {
                Operator op;
                //log("close par");

                while((op = operatorStack.pop()).val != '(')
                {
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    if(op.val=='/' && op2.val==0){
                        return new Result("div by 0");
                    }
                    float res = performOp(op1,op,op2);



                    operandStack.push(new Operand(res));
                    //Deb.showStackStates(operandStack,operatorStack);
                    result = res;
                }
                i++;
            }

            else if(isDig(expA[i])) {
                String num="0";

                //log("digit");
                do {

                    num = num.concat("" + expA[i]);

                    ++i;

                }while(isDig(expA[i]) || expA[i]=='.');

                float operand;
                operand = Float.parseFloat(num);

                operandStack.push(new Operand(operand));

            }

            else {
                //log("Operator found1 : " + expA[i]);
                if(operatorStack.empty() || operatorStack.peek().val == '(')
                {
                    operatorStack.push(new Operator(expA[i]));
                    //log("Push operator1 :: " + expA[i]);
                    i++;
                }

                else {
                    if(operatorStack.peek().getPrior() < Operator.getPriority(expA[i])) {
                        operatorStack.push(new Operator(expA[i]));
                        //log("Push operator2 :: " + expA[i]);
                        i++;
                    }

                    else
                    {
                        Operator op;
                        while((op = operatorStack.peek()).getPrior() >= Operator.getPriority(expA[i])) {

                            Operand op2= operandStack.pop();
                            Operand op1=null;
                            try {
                                 op1 = operandStack.pop();
                            }catch (Exception e){
                                op1=new Operand(0.0f);
                            }

                            op = operatorStack.pop();
                            float res;

                            if(op.val=='/' && op2.val==0){
                                return new Result("div by 0");
                            }

                            res = performOp(op1,op,op2);



                            operandStack.push(new Operand(res));
                            //log("Push value :: " + res);

                            result = res;
                        }
                        operatorStack.push(new Operator(expA[i]));
                        //Deb.showStackStates(operandStack,operatorStack);
                        i++;
                    }
                }


            }

        }

        return new Result(result);
    }


    static float performOp(Operand op1, Operator op, Operand op2) {

        char oper = op.val;
        float oper1 = op1.val;
        float oper2 = op2.val;

        //evalExp.log("performing :: " + oper1 + oper + oper2);
        switch(oper) {

            case '*': return oper1*oper2;
            case '/': return oper1/oper2;
            case '+': return oper1+oper2;
            case '-': return oper1-oper2;
        }


        return 0;
    }

    static void log(String a)
    {
        System.out.println("Log : " + a);
    }

    static boolean isDig(char a) {

        if(a>='0' && a<='9') return true;

        else return false;

    }

    static boolean isParenthesis(char a) {

        if(a == '(') return true;
        return false;
    }


    public static class Result {
        boolean isInvalid;
        String error;
        float value;

        public Result(float a) {
            value = a;
            isInvalid = false;
            error="";
        }

        public Result(String errMsg) {
            isInvalid=true;
            error = errMsg;
            value=-1;
        }

        @Override
        public String toString() {
            return Float.toString(value);
        }
    }
}

class Operator {

    char val;
    Operator(char a) {

        val = a;
    }

    int getPrior(){
        int prior=-1;

        if(val == '-') prior = 7;

        else if(val == '+') prior = 7;

        else if (val == '*') prior = 9;

        else if(val == '/') prior = 9;

        return prior;
    }

    static int getPriority(char val) {
        int prior = -1;
        if(val == '-') prior = 7;

        else if(val == '+') prior = 7;

        else if (val == '*') prior = 9;

        else if(val == '/') prior = 9;

        return prior;
    }

    public String toString() {
        return "" + val;
    }



}

class Operand {
    float val;

    Operand(float a) {
        val = a;
    }

    public String toString() {
        return Float.toString(val);
    }

}

//class Deb {

//static void showStackStates(Stack<Operand> a, Stack<Operator> b ) {
//System.out.println("Operand Stack State : " + a);


//System.out.println("\nOperator Stack State : " + b);

//}
//}
