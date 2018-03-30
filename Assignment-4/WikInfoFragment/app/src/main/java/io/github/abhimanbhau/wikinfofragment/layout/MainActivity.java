package io.github.abhimanbhau.wikinfofragment.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.github.abhimanbhau.wikinfofragment.R;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String selectedGrp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        String[] listGroups = new String[]{"Actors", "Smartphones", "Cars"};
        final String[] listActors = new String[]{"Robert Downey Jr.", "Chris Pratt", "Harrison Ford", "Chris Evans"};
        final String[] listCars = new String[]{"Mercedes", "Lexus", "Toyota", "Honda", "Aston Martin"};
        final String[] listMobiles = new String[]{"Samsung", "HTC", "LG", "Nokia", "iPhone"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new String[]
                        {
                                "Big Cats",
                                "macOS Versions",
                                "Comedy Youtubers"
                        });

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SubListActivity.class);
                switch (i) {
                    case 0:
                        intent.putExtra("group", 0);
                        break;
                    case 1:
                        intent.putExtra("group", 1);
                        break;
                    case 2:
                        intent.putExtra("group", 2);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}