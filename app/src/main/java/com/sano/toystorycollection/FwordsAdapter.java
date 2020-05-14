package com.sano.toystorycollection;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class FwordsAdapter extends ArrayAdapter {
    private int  mcolorCode;
    public FwordsAdapter(@NonNull Context context, int resource, ArrayList<FWordsDT> FWords_obj, int ColorCOde) {
        super(context, 0,  FWords_obj);
        mcolorCode = ColorCOde;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,parent,false);
        }
        FWordsDT  current_obj = (FWordsDT) getItem(position);

        // gettings View For ConverView
        TextView dWord = (TextView) convertView.findViewById(R.id.layout_wordview);
        TextView TWord = (TextView) convertView.findViewById(R.id.layout_TWord);
        ImageView viewImage = (ImageView) convertView.findViewById(R.id.layout_imageView);

        // Setting the View to the obj elements
        dWord.setText(current_obj.getdWord());
        TWord.setText(current_obj.gettWord());
        // condetional
        if(current_obj.getImageview_num() == -1)
            viewImage.setVisibility(View.GONE);
        else
            viewImage.setImageResource(current_obj.getImageview_num());

        int color = ContextCompat.getColor(getContext(),mcolorCode);
        convertView.findViewById(R.id.left_View).setBackgroundColor(color);

        return  convertView;
    }
}
