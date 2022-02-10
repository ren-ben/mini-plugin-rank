package com.ren.rank.nametags;

public enum Rank {

    //Set the ranks and their order lists
    OWNER("§d&l[Owner] ", 'a'),
    ADMIN("&e&l[ADMIN] ", 'b'),
    HELPER("§9§l[HELPER] ", 'c'),
    BUILDER("§b§l[BUILDER] ", 'd'),
    NEWCOMER("§7§l[NEWCOMER] ", 'e');

    String display;
    char orderSymbol;

    //Enum Constructor
    Rank(String display, char orderSymbol) {
        this.display = display;
        this.orderSymbol = orderSymbol;
    }

    //Just some getters
    public String getDisplay() {return display;}
    public char getOrderSymbol() {return orderSymbol;}
}
