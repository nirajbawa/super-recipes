package com.superrecipesapp.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.superrecipesapp.app.Adapters.OrdersListAdapter;
import com.superrecipesapp.app.Models.OrdersListModel;

import java.util.ArrayList;
import java.util.Objects;

public class OrdersListActivity extends AppCompatActivity {
    ListView ordersListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        Button btnViewOrders = new Button(this);

        btnViewOrders.setWidth(500);
        btnViewOrders.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        btnViewOrders.setText("My Orders");
        btnViewOrders.setBackgroundColor(Color.TRANSPARENT);
        btnViewOrders.setPadding(270, 0, 0, 0);
        toolbar.addView(btnViewOrders);

        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);

        ordersListView = findViewById(R.id.ordersList);

        ArrayList<OrdersListModel> ordersList = new ArrayList<>();
        ordersList.add(new OrdersListModel("Spicy Chicken Tacos", "Grilled chicken seasoned with a blend of spices, topped with fresh salsa, shredded lettuce, and a zesty chipotle sauce, all wrapped in warm corn tortillas.",  9, R.drawable.spicy_chicken_tacos));
        ordersList.add(new OrdersListModel("Margherita Pizza", "Traditional thin-crust pizza with ripe tomatoes, fresh mozzarella cheese, and basil leaves, drizzled with extra virgin olive oil.", 12, R.drawable.margherita_pizza));
        ordersList.add(new OrdersListModel("Vegetarian Pad Thai", "Stir-fried rice noodles with tofu, bean sprouts, peanuts, and a tangy tamarind sauce, garnished with cilantro and lime wedges.", 11, R.drawable.vegetarian_pad_hai));
        ordersList.add(new OrdersListModel("Classic Caesar Salad", "Crisp romaine lettuce tossed with garlic croutons, parmesan cheese, and creamy Caesar dressing.", 8, R.drawable.classic_caesar_salad));
        ordersList.add(new OrdersListModel("BBQ Pulled Pork Sandwich", "Slow-cooked pulled pork smothered in tangy barbecue sauce, served on a toasted bun with coleslaw on the side.", 10, R.drawable.bbq_pulled_pork_sandwich));
        ordersList.add(new OrdersListModel("Shrimp Scampi Pasta", "Succulent shrimp sautéed in garlic-infused olive oil, tossed with linguine, cherry tomatoes, and fresh parsley.", 14, R.drawable.shrimp_scampi_pasta));

        ordersList.add(new OrdersListModel("Puran Poli", "A sweet flatbread stuffed with a mixture of chana dal, jaggery, and cardamom. It's a traditional festive delicacy in Maharashtra.", 15, R.drawable.puran_poli));
        ordersList.add(new OrdersListModel("Misal Pav", "A spicy curry made with sprouted moth beans, topped with farsan (crispy savory mixture) and served with pav (bread roll).", 10, R.drawable.misal_pav));
        ordersList.add(new OrdersListModel("Vada Pav", " Mumbai's iconic street food, consisting of a deep-fried potato patty (vada) sandwiched in a pav with chutneys and spices.", 9, R.drawable.vada_pav));
        ordersList.add(new OrdersListModel("Poha", "Flattened rice cooked with mustard seeds, curry leaves, turmeric, and topped with onions, peas, and coriander.", 15, R.drawable.poha));
        ordersList.add(new OrdersListModel("Solkadhi", "A refreshing drink made with coconut milk, kokum, and spices. It is commonly served as a digestive after meals.", 12, R.drawable.solkadhi));
        ordersList.add(new OrdersListModel("Bhakarwadi", "Spiral-shaped, deep-fried snack with a spicy and tangy filling made of gram flour, coconut, and sesame seeds.", 7, R.drawable.bhakarwadi));
        ordersList.add(new OrdersListModel("Kothimbir Vadi", "Savory cakes made with coriander leaves, gram flour, and spices, steamed or fried until golden brown.", 5, R.drawable.kothimbir_vadi));




        OrdersListAdapter ordersListAdapter = new OrdersListAdapter(getApplicationContext(), ordersList);
        ordersListView.setAdapter(ordersListAdapter);
        ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrdersListModel item = ordersList.get(i);
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("thumbnail", item.getThumbnail());
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

    }

}