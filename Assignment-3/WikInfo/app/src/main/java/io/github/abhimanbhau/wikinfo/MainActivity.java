package io.github.abhimanbhau.wikinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] objects = {"Cat", "Dog", "Intel", "Android", "Apple", "Google",
                "Main_Page", "**Custom**   BETA"};
        ListView lstObjects = findViewById(R.id.lstItems);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lstObjects.setAdapter(adapter);
        for (String s:
             objects) {
            arrayList.add(s);
        }

        lstObjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        startActivity(new Intent(getApplicationContext(),
                                InfoViewActivity.class)
                        .putExtra("URL", "https://en.wikipedia.org/wiki/"
                                + arrayList.get(i)));
                        break;

                    case 7:
                        final EditText txtUrl = new EditText(getApplicationContext());
                        txtUrl.setHint("INPUT SEARCH");
                        txtUrl.setTextColor(Color.BLUE);
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Custom search")
                                .setMessage("Enter Search Term")
                                .setView(txtUrl)
                                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        startActivity(
                                                new Intent(getApplicationContext(),
                                                        InfoViewActivity.class).putExtra("URL",
                                                        "https://en.wikipedia.org/wiki/" +
                                                                txtUrl.getText().toString()));
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                                .show();
                }
            }
        });
    }
}
