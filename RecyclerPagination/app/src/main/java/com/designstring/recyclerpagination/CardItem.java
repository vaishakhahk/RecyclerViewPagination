package com.designstring.recyclerpagination;

/**
 * Created by vaishakha on 6/10/16.
 */
public class CardItem {


    private String card;

    private String data;

    public CardItem() {

    }

    public CardItem(String card, String data) {
        this.card = card;
        this.data = data;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
