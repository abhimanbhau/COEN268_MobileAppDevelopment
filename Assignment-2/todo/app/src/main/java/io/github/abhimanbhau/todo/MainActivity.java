package io.github.abhimanbhau.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddToList;
    ListView lstToDo;
    EditText txtInput;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddToList = findViewById(R.id.btnToDo);
        lstToDo = findViewById(R.id.lstTodo);
        txtInput = findViewById(R.id.txtInput);


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lstToDo.setAdapter(adapter);
        arrayList.add("First"); arrayList.add("Second");

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtInput.getText().toString().length() == 0) return;
                adapter.add(txtInput.getText().toString());
                adapter.notifyDataSetChanged();
                txtInput.setText("");
            }
        });
    }
}
