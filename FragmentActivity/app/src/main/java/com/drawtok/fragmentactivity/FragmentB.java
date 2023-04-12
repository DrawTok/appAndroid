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

public class FragmentB extends Fragment {

    EditText edtB;
    Button btnB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);
        edtB = view.findViewById(R.id.edt_fragmentB);
        btnB = view.findViewById(R.id.btn_fragmentB);
        btnB.setOnClickListener(v->
        {
            TextView tvFragmentA = getActivity().findViewById(R.id.tv_fragment_a);
            tvFragmentA.setText(edtB.getText().toString().trim());
        });

        return view;
    }
}
