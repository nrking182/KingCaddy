package com.example.nick.kingcaddy;


import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nick on 10/8/2016.
 */
public class Player implements Parcelable {
    public String name;
    private List<Golf_Hole> scores = new ArrayList<>();
    private int total_score;
    private int total_par;


    public Player(String name) {
        this.name = name;
        this.total_score = 0;
        this.total_par = 0;
        for(int i = 0; i < 18; i++){
            scores.add(new Golf_Hole(i+1));
        }
    }

    protected Player(Parcel in) {
        name = in.readString();
        if(scores == null){
            for(int i = 0; i < 18; i++){
                scores.add(new Golf_Hole(i+1));
            }
        } else {
            in.readList(scores, Golf_Hole.class.getClassLoader());
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(scores);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setSingleHoleScore(Integer holeNumber,Integer score) {
        this.scores.get(holeNumber).setScore(score);
    }

    public int getSingleHoleScore(int holeNumber) {
        return scores.get(holeNumber).getScore();
    }

    public Golf_Hole getSingleHole(int holeNumber){
        return scores.get(holeNumber);
    }

    public List<Golf_Hole> getHoles(){
        return scores;
    }

    public int calculateTotalScore(){
        for(Golf_Hole gh : scores){
            if(gh.getScore() != 0) {
                total_score += gh.getScore();
                total_par += gh.getPar();
                System.out.println("Score: " + total_score + " Par: " + total_par);
            }
        }
        return total_score - total_par;
    }

    public void addHole(){
        scores.add(new Golf_Hole(scores.size()+1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, scores, total_score);
    }
}
