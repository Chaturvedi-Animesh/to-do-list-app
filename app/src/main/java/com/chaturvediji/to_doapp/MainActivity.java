package com.chaturvediji.to_doapp;

import android.content.Context;
import android.support.v13.view.DragStartHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemET;
    private Button btn;
    private ListView itemList;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closekeyboard();
        setContentView(R.layout.activity_main);
        itemET= findViewById(R.id.item_edit_text);
        btn=findViewById(R.id.add_btn);
        itemList=findViewById(R.id.items_list);
        btn.setOnClickListener(this);
        items=FileHelper.readData(this);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        closekeyboard();
    }

    private void closekeyboard() {
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered=itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");
                FileHelper.writeData(items,this);
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();


                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items,this);
        Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show();
    }
}
