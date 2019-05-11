package com.soapsnake.pattern.structuralPatterns.bridge;

public class CurrentAccount implements Account {
    @Override
    public Account openAccount() {
        System.out.println("OPENED: CURRENT ACCOUNT ");
        return new CurrentAccount();
    }

    @Override
    public void accountType() {
        System.out.println("##It is a CURRENT Account##");
    }
}
