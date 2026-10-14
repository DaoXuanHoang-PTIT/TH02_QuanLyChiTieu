package com.example.thuchanh02;

import java.io.Serializable;
import java.util.UUID;

public class Expense implements Serializable {
    private String id;
    private String name;
    private long amount;
    private String category;
    private String date;
    private String note;

    public Expense(String name, long amount, String category, String date, String note) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public long getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getNote() { return note; }
}
