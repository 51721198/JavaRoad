package com.soapsnake.Atm.domain;

/**
 *
 * Created on 2020-05-28
 */
public abstract class Deposit extends Transaction {
    private double amount;

    public abstract double getAmount();
}