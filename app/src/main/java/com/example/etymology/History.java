package com.example.etymology;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        DataBase db;
        listView=(ListView)findViewById(R.id.listView);
         db=new DataBase(this);
     Cursor data=  db.getData();
        ArrayList<String> list=new ArrayList<>();
        if(data.getCount()==0)
            Toast.makeText( History.this,"no words to show",Toast.LENGTH_LONG);
        else
        {
            while(data.moveToNext())
            {
                list.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(listAdapter);
            }
        }


    }
}
