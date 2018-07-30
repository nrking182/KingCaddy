
package com.example.nick.kingcaddy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Scorecard extends AppCompatActivity {
    ArrayList<Player> players = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        players = getIntent().getParcelableArrayListExtra("players");
        TableLayout ll = (TableLayout) findViewById(R.id.score_table);

        TableRow tr_header = new TableRow(this);
        TableRow.LayoutParams header_lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        tr_header.setLayoutParams(header_lp);
        TextView header_name = new TextView(this);
        header_name.setText("Name");
        header_name.setPadding(0,0,220,0);
        header_name.setTypeface(null, Typeface.BOLD);
        TextView header_score = new TextView(this);
        header_score.setText("Score");
        header_score.setTypeface(null, Typeface.BOLD);
        tr_header.addView(header_name);
        tr_header.addView(header_score);
        ll.addView(tr_header,0);

        for (int i = 0; i < players.size(); i++){
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView name = new TextView(this);
            name.setText(players.get(i).name);
            TextView score = new TextView(this);
            score.setText(String.valueOf(players.get(i).calculateTotalScore()));

            row.addView(name);
            row.addView(score);
            ll.addView(row, i+1);
        }

    }

}
