package com.example.nick.kingcaddy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Nick on 10/21/2016.
 */
public class Golf_Round implements Parcelable {
    private ArrayList<Player> players;
    private String date;
    private String name;
    private String course;

    public Golf_Round() {

        date = "1/1/2016";
    }
    /* will have more functionality for round data eventually when implementing the activity for viewing previous rounds (Scorecard activity) */

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(date);
        out.writeTypedList(players);

    }

    public static final Parcelable.Creator<Golf_Round> CREATOR = new Parcelable.Creator<Golf_Round>() {
        public Golf_Round createFromParcel(Parcel in) {
            return new Golf_Round(in);
        }

        public Golf_Round[] newArray(int size) {
            return new Golf_Round[size];
        }
    };
    public Golf_Round(Parcel in) {
        date = in.readString();
        in.readTypedList(players, Player.CREATOR);
    }
}
