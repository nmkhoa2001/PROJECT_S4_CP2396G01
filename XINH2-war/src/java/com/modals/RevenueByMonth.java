/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modals;

/**
 *
 * @author odieng
 */
public class RevenueByMonth {

    private int month;
    private double value;

    public RevenueByMonth(int month, double value) {
        this.month = month;
        this.value = value;
    }

    public RevenueByMonth() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
