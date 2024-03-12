package com.superrecipesapp.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.superrecipesapp.app.Models.OrdersListModel;
import com.superrecipesapp.app.OrdersListActivity;
import com.superrecipesapp.app.R;

import java.util.ArrayList;

public class OrdersListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<OrdersListModel> list;

    public OrdersListAdapter(Context applicationContext, ArrayList<OrdersListModel> list) {
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.orders_card, null);
        TextView title, price, description;
        ImageView thumbnail;
        title = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        description = view.findViewById(R.id.description);
        thumbnail = view.findViewById(R.id.thumbnail);
        String titleStrArr[] = this.list.get(i).getTitle().split(" ");
        String titleStr = "";
        for(String text:titleStrArr)
        {
            titleStr += text.toUpperCase().charAt(0) + text.substring(1) + " ";
        }
        title.setText(titleStr);
        price.setText(Integer.toString(this.list.get(i).getPrice()));
        description.setText(this.list.get(i).getDescription().substring(0, 30)+".......");
        thumbnail.setImageResource(this.list.get(i).getThumbnail());
        return view;
    }
}
