package com.ren.rank.nametags;

public enum Ranks {

    //Set the ranks and their order lists
    OWNER("§d&lOwner", 'a'),
    ADMIN("&e&lADMIN", 'b'),
    HELPER("§9§lHELPER", 'c'),
    BUILDER("§b§lBUILDER", 'd'),
    NEWCOMER("§7§lNEWCOMER", 'e');

    String display;
    char orderSymbol;

    //Enum Constructor
    Ranks(String display, char orderSymbol) {
        this.display = display;
        this.orderSymbol = orderSymbol;
    }

    //Just some getters
    public String getDisplay() {return display;}
    public char getOrderSymbol() {return orderSymbol;}
}
