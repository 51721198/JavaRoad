package com.soapsnake.soapbook.model;

import com.soapsnake.soapbook.enums.AccountStatus;

/**
 *
 * Created on 2020-06-01
 */
public class Account {
    private String id;
    private String password;
    private AccountStatus status;

    public boolean resetPassword() {
        return false;
    }
}