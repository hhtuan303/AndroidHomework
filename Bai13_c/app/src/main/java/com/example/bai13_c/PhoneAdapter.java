package com.example.bai13_c;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PhoneAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> listPhone;

    public PhoneAdapter(Activity context, int idLayout, ArrayList<Phone> listPhone) {
        super(context, idLayout, listPhone);
        this.context = context;
        this.idLayout = idLayout;
        this.listPhone = listPhone;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        Phone phone = listPhone.get(position);
        ImageView imgphone = convertView.findViewById(R.id.imageView);
        imgphone.setImageResource(phone.getImage());
        TextView txtPhone = convertView.findViewById(R.id.text_view_name);
        txtPhone.setText(phone.getName());
        return convertView;
    }
}
