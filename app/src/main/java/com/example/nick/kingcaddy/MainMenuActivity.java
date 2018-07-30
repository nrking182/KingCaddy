package com.example.nick.kingcaddy;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenuActivity extends AppCompatActivity {



    private List<Player> player_list = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void newRound(View v){
        player_list.clear();
        loadPlayers();
        Intent intent = new Intent(this, Round_Players.class);
        intent.putParcelableArrayListExtra("key", (ArrayList<Player>) player_list);
        startActivity(intent);
    }

    public void add_Player(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enter player name: ");
        final EditText player_name = new EditText(this);
        builder.setView(player_name);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = player_name.getText().toString();

                if(savePlayer(name)) {
                    Toast.makeText(MainMenuActivity.this, "Player " + name + " Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainMenuActivity.this, "Player " + name + " Already Exists", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

        });
        builder.show();
    }

    public void removePlayer(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete player named: ");
        final EditText player_name = new EditText(this);
        builder.setView(player_name);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = player_name.getText().toString();

                if(deletePlayer(name)) {
                    Toast.makeText(MainMenuActivity.this, "Player " + name + " Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainMenuActivity.this, "Something broke lel", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public Boolean savePlayer(String playerName){
        String filename = "playersFile";
        String fileContents = playerName + ", ";

        try{
//            Scanner scanner = new Scanner(new File(filename));
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if(line.contains(fileContents)) {
//                    System.out.println(line);
//                    return false;
//                }
//            }

            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void loadPlayers(){
        try{
            FileInputStream fis = openFileInput("playersFile");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            for(String newPlayer : bufferedReader.readLine().split(", ")){
                Player toAdd = new Player(newPlayer);
                if(!player_list.contains(toAdd))
                    player_list.add(new Player(newPlayer));
            }
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean deletePlayer(String toDelete){
        try {
            String filename = "playersFile";
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line = bufferedReader.readLine();
            line = line.replace(toDelete +", ", "");
            System.out.println(line);
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(line.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
