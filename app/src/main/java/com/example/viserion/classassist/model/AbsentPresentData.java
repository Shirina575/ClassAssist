package com.example.viserion.classassist.model;

import java.io.Serializable;

public class AbsentPresentData implements Serializable {

    private int index;
    private int present;
    private int absent;
    private int percentage;

    public AbsentPresentData(int present, int absent, int persentage) {
        this.setAbsent(absent);
        this.setPresent(present);
        this.setPersentage(persentage);
    }

    public AbsentPresentData(int index, int present, int absent, int percentage) {
        this.setIndex(index);
        this.setPresent(present);
        this.setAbsent(absent);
        this.setPersentage(percentage);
    }

    public AbsentPresentData(int present, int absent) {
        this.setPresent(present);
        this.setAbsent(absent);
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getPersentage() {
        return percentage;
    }

    public void setPersentage(int persentage) {
        this.percentage = persentage;
    }


}
