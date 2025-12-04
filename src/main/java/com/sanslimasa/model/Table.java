package com.sanslimasa.model;

public class Table {
    private int number;
    private boolean active;

    public Table() {}

    public Table(int number, boolean active) {
        this.number = number;
        this.active = active;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
