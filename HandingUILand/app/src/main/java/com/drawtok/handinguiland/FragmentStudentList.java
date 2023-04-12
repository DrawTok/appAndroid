package com.drawtok.handinguiland;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentStudentList extends ListFragment {

    ArrayList<Student> arrayStudent;
    StudentAdapter adapter;
    TransmissionDataStd transmissionDataStd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        transmissionDataStd = (TransmissionDataStd) getActivity();
        arrayStudent = new ArrayList<>();
        addListStudent();
        adapter = new StudentAdapter(getActivity(), R.layout.row_student, arrayStudent);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    private void addListStudent() {

        arrayStudent.add(new Student("A100", "Nguyễn Văn A", 2003, "Hà Nội"));
        arrayStudent.add(new Student("A101", "Lê Văn C", 2003, "Hà Nội"));
        arrayStudent.add(new Student("A102", "Nguyễn Thị D", 2003, "Quảng Ninh"));
        arrayStudent.add(new Student("A103", "Ngô Văn A", 2003, "Hải Phòng"));
        arrayStudent.add(new Student("A201", "Trần Anh C", 2004, "Cao Bằng"));
        arrayStudent.add(new Student("A202", "Nguyễn Văn B", 2004, "Vĩnh Phúc"));
        arrayStudent.add(new Student("A203", "Trần Văn A", 2004, "Hà Giang"));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        transmissionDataStd.TransmissionData(arrayStudent.get(position));

    }
}
