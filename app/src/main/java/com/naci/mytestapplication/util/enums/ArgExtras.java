package com.naci.mytestapplication.util.enums;

public enum ArgExtras {
    ANIME_LIST_EXTRA("ANIME_LIST_EXTRA");

    private String text;

    ArgExtras(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
