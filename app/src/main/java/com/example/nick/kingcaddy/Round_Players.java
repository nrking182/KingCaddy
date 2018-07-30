package com.example.nick.kingcaddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class Round_Players extends AppCompatActivity {
    ArrayList<String> selected_items = new ArrayList<>();
    ArrayList<Player> round_players = new ArrayList<Player>();
    ArrayList<Player> all_players = new ArrayList<Player>();
    Golf_Round new_round = new Golf_Round();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        all_players = getIntent().getParcelableArrayListExtra("key");
        setContentView(R.layout.activity_round__players);
        Intent intent = getIntent();
        ListView ch1 = (ListView) findViewById(R.id.checkable_list);
        Button b1 = (Button) findViewById(R.id.ok_button);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayList<String> items = new ArrayList<String>();
        for(Player temp : all_players){
            items.add(temp.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.rowlayout,R.id.txt_lan,items);
        ch1.setAdapter(adapter);
        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selected_items.contains(selectedItem)) {
                    selected_items.remove(selectedItem);
                } else
                    selected_items.add(selectedItem);
            }
        });
    }

        public void Start_Round(View v){
            round_players.clear();
            for(Player temp : all_players) {
                if (selected_items.contains(temp.getName())) {
                    round_players.add(temp);
                }
            }
            new_round.setPlayers(round_players);
            new_round.setDate(Calendar.getInstance().getTime().toString());
            Intent i = new Intent(this,Score_Activity.class);
            i.putParcelableArrayListExtra("key",round_players);
            startActivity(i);
    }


}

