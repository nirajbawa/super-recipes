package com.superrecipesapp.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.superrecipesapp.app.Adapters.FoodItemsListAdapter;
import com.superrecipesapp.app.Adapters.OrdersListAdapter;
import com.superrecipesapp.app.Models.FoodItemsListModel;
import com.superrecipesapp.app.Models.OrdersListModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView foodItemsListView;
    ArrayList<FoodItemsListModel> foodItemsList;
    DBHelper helper;
    FoodItemsListAdapter foodItemsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        foodItemsListView = findViewById(R.id.foodItemsList);
        helper = new DBHelper(this);


        try {
            foodItemsList = helper.getOrders();
        }
        catch (Exception e)
        {
            foodItemsList = new ArrayList<>();
            Log.d("myError", e.toString());
        }


        foodItemsListAdapter = new FoodItemsListAdapter(getApplicationContext(), foodItemsList);
        foodItemsListView.setAdapter(foodItemsListAdapter);
        foodItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("id", foodItemsList.get(i).getOrderNo());
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        foodItemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int orderid, long l) {
                try{
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete Order")
                            .setMessage("Are you sure to delete this item.")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try{
                                        boolean isDeleted = helper.deleteOrder(foodItemsList.get(orderid).getOrderNo());
                                        if(isDeleted)
                                        {
                                            recreate();
                                            Toast.makeText(MainActivity.this, "Order deleted succssfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        Log.d("myerror", e.toString());
                                    }

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Log.d("myerror", e.toString());
                }


                return false;
            }
        });

    }
}