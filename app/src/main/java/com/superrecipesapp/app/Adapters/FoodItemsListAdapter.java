package com.superrecipesapp.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.superrecipesapp.app.Models.FoodItemsListModel;
import com.superrecipesapp.app.R;

import java.util.ArrayList;

public class FoodItemsListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<FoodItemsListModel> list;

    public FoodItemsListAdapter(Context applicationContext, ArrayList<FoodItemsListModel> list)
    {
        this.context = applicationContext;
        this.list = list;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.food_cards, null);
        TextView title, price, orderNo;
        ImageView thumbnail;
        title = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        orderNo = view.findViewById(R.id.orderNo);
        thumbnail = view.findViewById(R.id.thumbnail);
        String titleStrArr[] = this.list.get(i).getTitle().split(" ");
        String titleStr = "";
        for(String text:titleStrArr)
        {
            titleStr += text.toUpperCase().charAt(0) + text.substring(1) + " ";
        }
        title.setText(titleStr);
        price.setText("$"+Integer.toString(this.list.get(i).getPrice()));
        orderNo.setText(Integer.toString(this.list.get(i).getOrderNo()));
        thumbnail.setImageResource(this.list.get(i).getThumbnail());
        return view;
    }
}
