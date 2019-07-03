package com.example.webserviceusingpicasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserDetails> {
    public UserAdapter(Context context, ArrayList<UserDetails> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);

        ImageView imageView=convertView.findViewById(R.id.img);
        TextView textView=convertView.findViewById(R.id.txt1);
        TextView textView1=convertView.findViewById(R.id.txt2);


        UserDetails userDetails=getItem(position);
        textView.setText(userDetails.getName());
        textView1.append(" "+userDetails.getLikes());
        Picasso.with(getContext()).load(userDetails.getImageURL()).into(imageView);

        return convertView;
    }
}
