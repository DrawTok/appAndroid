package com.drawtok.webservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    MainActivity context;
    int layout;
    List<SinhVien> sinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder
    {
        TextView tvHoten, tvNamSinh, tvDiachi;
        ImageView imvEdit, imvDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tvHoten = convertView.findViewById(R.id.tv_hoten);
            holder.tvNamSinh = convertView.findViewById(R.id.tv_namsinh);
            holder.tvDiachi = convertView.findViewById(R.id.tv_diachi);
            holder.imvEdit = convertView.findViewById(R.id.imv_edit);
            holder.imvDelete = convertView.findViewById(R.id.imv_delete);

            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = sinhVienList.get(position);

        holder.tvHoten.setText(sinhVien.getHoten());
        holder.tvNamSinh.setText(sinhVien.getNamsinh()+"");
        holder.tvDiachi.setText(sinhVien.getDiachi());

        //bắt sự kiện xóa và sửa
        holder.imvEdit.setOnClickListener(v->
        {
            Intent intent = new Intent(context, UpdateSVActivity.class);
            intent.putExtra("dataSV", sinhVien);
            context.startActivity(intent);
        });

        holder.imvDelete.setOnClickListener(v->
        {
            XacNhanXoa(sinhVien.getHoten(), sinhVien.getId());
        });

        return convertView;
    }

    private void XacNhanXoa(String ten, int id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa sinh viên " + ten + " không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteStudent(id);
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
