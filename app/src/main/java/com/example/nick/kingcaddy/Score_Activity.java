package com.example.nick.kingcaddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Score_Activity extends AppCompatActivity {
    ArrayList<Player> players = new ArrayList<Player>();

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<Player> listDataHeader;
    private HashMap<Player,List<Golf_Hole>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_);
        players = getIntent().getParcelableArrayListExtra("key");


        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.addAll(players);

        for(int i = 0; i < players.size(); i++) {
            listHash.put(listDataHeader.get(i), players.get(i).getHoles());
        }
    }

    public void Scorecard(View v){
        Intent intent = new Intent(this, Scorecard.class);
        intent.putParcelableArrayListExtra("players", (ArrayList<Player>) listDataHeader);
        startActivity(intent);
    }

}
