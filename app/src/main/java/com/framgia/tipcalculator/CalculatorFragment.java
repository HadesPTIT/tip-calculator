package com.framgia.tipcalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private TextView mTvResult;
    private TextView mTvExpress;
    private double mResult;

    private int[] mIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
            R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_div, R.id.btn_mul, R.id.btn_add, R.id.btn_sub,
            R.id.btn_dot, R.id.btn_equal, R.id.btn_change_sign, R.id.btn_sp2, R.id.btn_ac};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTvResult = view.findViewById(R.id.tv_result);
        mTvExpress = view.findViewById(R.id.tv_express);
        setupCallback(view);
    }

    private void setupCallback(View view) {
        for (int id : mIds) {
            view.findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_sign: {
                handleChangeSign();
                break;
            }
            case R.id.btn_ac: {
                handleClear();
                break;
            }
            case R.id.btn_equal: {
                handleCalculate();
                break;
            }
            default: {
                handleExpression(v);
            }
        }
    }

    private void handleCalculate() {
        try {
            mResult = Calculate.eval(mTvExpress.getText().toString());
            mTvResult.setText(String.valueOf(mResult));
        } catch (RuntimeException e) {
            mTvResult.setText(R.string.error);
        }

    }

    private void handleExpression(View v) {
        Button b = (Button) v;
        String expression = b.getText().toString();
        mTvExpress.append(expression);
    }

    private void handleClear() {
        mResult = 0.0;
        mTvExpress.setText("");
        mTvResult.setText("0");
    }

    private void handleChangeSign() {
        if (mResult == 0) return;
        mResult = mResult * -1;
        mTvResult.setText(String.valueOf(mResult));
    }


}
