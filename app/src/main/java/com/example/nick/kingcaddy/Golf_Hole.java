package com.example.nick.kingcaddy;

import android.os.Parcel;
import android.os.Parcelable;

public class Golf_Hole implements Parcelable{
    private int par;
    private int score;
    private int holeNumber;

    public Golf_Hole(){
        par = 3;
        score = 0;
    }

    public Golf_Hole(int holeNumber){
        this.holeNumber = holeNumber;
        this.par = 3;
        this.score = 0;
    }

    public Golf_Hole(Parcel in){
        this.par = in.readInt();
        this.score = in.readInt();
        this.holeNumber = in.readInt();
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(){
        this.score++;
    }

    public void decrementScore(){
        this.score--;
    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(par);
        dest.writeInt(score);
        dest.writeInt(holeNumber);
    }

    public static final Creator<Golf_Hole> CREATOR = new Parcelable.Creator<Golf_Hole>() {
        @Override
        public Golf_Hole createFromParcel(Parcel source) {
            return new Golf_Hole(source);
        }

        @Override
        public Golf_Hole[] newArray(int size) {
            return new Golf_Hole[size];
        }
    };
}
