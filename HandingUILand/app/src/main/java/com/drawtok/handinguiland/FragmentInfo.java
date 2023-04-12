package com.drawtok.handinguiland;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentInfo extends Fragment {

    TextView tvID, tvName, tvBirthYear, tvAddress;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info_student, container, false);
        AnhXa();
        return view;
    }

    public void setInfo(Student student)
    {
        tvID.setText("ID: "+student.getIdStudent());
        tvName.setText("Họ tên: "+student.getName());
        tvBirthYear.setText("Năm sinh: "+student.getBirthYear());
        tvAddress.setText("Địa chỉ: "+student.getAddress());
    }

    private void AnhXa()
    {
        tvID        = view.findViewById(R.id.tv_info_id);
        tvName      = view.findViewById(R.id.tv_info_name);
        tvBirthYear = view.findViewById(R.id.tv_info_birthYear);
        tvAddress   = view.findViewById(R.id.tv_info_address);
    }
}
