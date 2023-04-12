package com.drawtok.fragmentactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentA extends Fragment {

    TextView tvA;
    EditText edtA;
    Button btnA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);
        tvA = view.findViewById(R.id.tv_fragment_a);
        edtA = view.findViewById(R.id.edt_fragmentA);
        btnA = view.findViewById(R.id.btn_fragmentA);
        TextView tvMain = getActivity().findViewById(R.id.tv_mainActivity);
        btnA.setOnClickListener(v->
        {
            tvMain.setText(edtA.getText().toString().trim());
        });

        return view;
    }

    public void GanNoiDung(String s)
    {
        tvA.setText(s);
    }

}
