package edu.uncc.assignment06.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MoodsResponse {
    public String status;
    public ArrayList<Mood> moods = new ArrayList<>();

    public MoodsResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Mood> getMoods() {
        return moods;
    }

    public void setMoods(ArrayList<Mood> moods) {
        this.moods = moods;
    }
}
