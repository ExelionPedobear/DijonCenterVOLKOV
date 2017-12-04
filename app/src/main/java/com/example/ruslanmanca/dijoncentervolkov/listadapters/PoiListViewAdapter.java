package com.example.ruslanmanca.dijoncentervolkov.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.R;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class PoiListViewAdapter extends ArrayAdapter<Poi> implements View.OnClickListener{
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtType;
        TextView txtName;
        TextView txtAdress;
        ImageView imgIcon;
    }

    public PoiListViewAdapter(ArrayList<Poi> data, Context context) {
        super(context, R.layout.poi_listview_item, data);
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Poi dataModel=(Poi)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Poi dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.poi_listview_item, parent, false);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtAdress = (TextView) convertView.findViewById(R.id.adress);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtType.setText(dataModel.getType());
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtAdress.setText(dataModel.getLocation().getAdress());
        if (dataModel.getType().equals("CINE")){
            viewHolder.imgIcon.setImageResource(R.mipmap.cine_icon);
        }
        if (dataModel.getType().equals("REST")){
            viewHolder.imgIcon.setImageResource(R.mipmap.resto_icon);
        }
        //viewHolder.info.setOnClickListener(this);
        // Return the completed view to render on screen
        return convertView;
    }

    public void updateData(ArrayList<Poi> list){
        this.clear();
        this.addAll(list);
    }
}
