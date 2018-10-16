package com.databinding.realapp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.databinding.realapp.R;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {

    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.three)
    Button three;
    @BindView(R.id.four)
    Button four;
    @BindView(R.id.five)
    Button five;
    @BindView(R.id.six)
    Button six;
    @BindView(R.id.seven)
    Button seven;
    @BindView(R.id.eight)
    Button eight;
    @BindView(R.id.nine)
    Button nine;
    @BindView(R.id.zero)
    Button zero;
    @BindView(R.id.point)
    Button point;

    @BindView(R.id.plus)
    Button plus;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id.multiply)
    Button multiply;
    @BindView(R.id.divide)
    Button divide;
    @BindView(R.id.equal)
    Button equalTo;
    @BindView(R.id.result)
    TextView result;

    char [] ops={'+','-','X','/'};
    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this,view);

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(result.getText()+getString(R.string.four));

            }
        });*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick(R.id.one)
    public void onOneClick()
    {
        result.setText(result.getText()+getString(R.string.one));
    }

    @OnClick(R.id.two)
    public void onTwoClick()
    {
        result.setText(result.getText()+getString(R.string.two));
    }

    @OnClick(R.id.three)
    public void onThreeClick()
    {
        result.setText(result.getText()+getString(R.string.three));
    }

    @OnClick(R.id.four)
    public void onFourClick()
    {
        result.setText(result.getText()+getString(R.string.four));
    }

    @OnClick(R.id.five)
    public void onFiveClick()
    {
        result.setText(result.getText()+getString(R.string.five));
    }

    @OnClick(R.id.six)
    public void onSixClick()
    {
        result.setText(result.getText()+getString(R.string.six));
    }


    @OnClick(R.id.seven)
    public void onSevenClick()
    {
        result.setText(result.getText()+getString(R.string.seven));
    }

    @OnClick(R.id.eight)
    public void onEightClick()
    {
        result.setText(result.getText()+getString(R.string.eight));
    }


    @OnClick(R.id.nine)
    public void onNineClick()
    {
        result.setText(result.getText()+getString(R.string.nine));
    }

    @OnClick(R.id.zero)
    public void onZeroClick()
    {
        result.setText(result.getText()+getString(R.string.zero));
    }

    @OnClick(R.id.plus)
    public void onPlusClick()
    {
        result.setText(result.getText()+getString(R.string.plus));
    }

    @OnClick(R.id.minus)
    public void onMinusClick()
    {
        result.setText(result.getText()+getString(R.string.minus));
    }


    @OnClick(R.id.multiply)
    public void onMultiplyClick()
    {
        result.setText(result.getText()+getString(R.string.multiply));
    }

    @OnClick(R.id.divide)
    public void onDivideClick()
    {
        result.setText(result.getText()+getString(R.string.divide));
    }


    @OnClick(R.id.equal)
    public void onEqualClick()
    {
        double output=calculate(result.getText().toString());

        result.setText(output+"");
    }


    private double calculate(String regExp)
    {
        Stack<Double> values=new Stack<>();
        Stack<Character> ops=new Stack<>();
        char tokens[]=regExp.toCharArray();
        int i=0;
        while (i<tokens.length) {

            if(tokens[i]>='0'&&tokens[i]<='9')
            {
                StringBuffer stringBuffer=new StringBuffer();
                while (i<tokens.length && tokens[i]>='0'&&tokens[i]<='9')
                {
                    stringBuffer.append(tokens[i++]);
                }
                values.push(Double.parseDouble(stringBuffer.toString()));
            }
            else if(tokens[i]=='+'||tokens[i]=='-'||tokens[i]=='X'||tokens[i]=='/')
            {
                ops.push(tokens[i++]);
            }
        }
        while (!ops.empty())
        {
            values.push(applyOp(ops.pop(),values.pop(),values.pop()));
        }
        return values.pop();

    }

    @OnClick(R.id.clr)
    public void onClearClick()
    {
        result.setText("");
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == 'X' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double applyOp(char op, double b, double a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'X':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
