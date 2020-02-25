package com.example.lucie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViews extends AppCompatActivity {
ListView listViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_views);
        listViews=findViewById(R.id.lucy);
        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Kisoro");
        arrayList.add("Kapchorwa");
        arrayList.add("Gulu");
        arrayList.add("Ibanda");
        arrayList.add("mbarara");
        arrayList.add("Lira");
        arrayList.add("Mpigi");
        arrayList.add("Kabale");
        arrayList.add("Isingiro");
        arrayList.add("Fortportal");
        arrayList.add("Kamwenge");
        arrayList.add("Kariro");
        arrayList.add("Kitugmu");
        arrayList.add("Soroti");
        arrayList.add("Lira");
        arrayList.add("Mpigi");
        arrayList.add("Kabale");
        arrayList.add("Isingiro");
        arrayList.add("Fortportal");
        arrayList.add("Kamwenge");
        arrayList.add("Gulu");
        arrayList.add("Ibanda");
        arrayList.add("mbarara");
        arrayList.add("Lira");
        arrayList.add("Mpigi");


        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listViews.setAdapter(arrayAdapter);
        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
}
