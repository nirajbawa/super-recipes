package com.superrecipesapp.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.superrecipesapp.app.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarMain.getRoot());

        Intent intent = getIntent();

        DBHelper helper = new DBHelper(this);


        if (intent.getIntExtra("type", 0) == 1) {
            binding.title.setText(intent.getStringExtra("title"));
            binding.description.setText(intent.getStringExtra("description"));
            binding.price.setText("$ " + Integer.toString(intent.getIntExtra("price", 0)));
            binding.thumbnail.setImageResource(intent.getIntExtra("thumbnail", 0));


            binding.send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        boolean isInserted = helper.insertOrders(
                                binding.name.getText().toString(),
                                binding.phone.getText().toString(),
                                intent.getIntExtra("price", 0),
                                intent.getIntExtra("thumbnail", 0),
                                intent.getStringExtra("description"),
                                intent.getStringExtra("title")
                        );
                        if (isInserted) {
                            Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("myError", e.toString());
                    }
                }
            });
        } else {
            Cursor cursor = helper.getOrderById(intent.getIntExtra("id", 0));
            binding.title.setText(cursor.getString(cursor.getColumnIndex("title")));
            binding.description.setText(cursor.getString(cursor.getColumnIndex("description")));
            binding.price.setText("$ "+Integer.toString(cursor.getInt(cursor.getColumnIndex("price"))));
            binding.thumbnail.setImageResource(cursor.getInt(cursor.getColumnIndex("thumbnail")));
            binding.name.setText(cursor.getString(cursor.getColumnIndex("name")));
            binding.phone.setText(cursor.getString(cursor.getColumnIndex("phone")));

            binding.send.setText("Update Order");
            binding.send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOrders(
                            binding.name.getText().toString(),
                            binding.phone.getText().toString(),
                            intent.getIntExtra("id", 0)
                    );
                    if(isUpdated)
                    {
                        Toast.makeText(getApplicationContext(),"Order Updated.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}