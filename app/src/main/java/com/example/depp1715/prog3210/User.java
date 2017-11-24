package com.example.depp1715.prog3210;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.app.Notification;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by depp1715 on 11/22/2017.
 */
@Entity
public class User {
    @PrimaryKey
    public final int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedPassword = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        this.id = id;
        this.username = username;
        this.password = bytesToHex(encodedPassword);
    }

    public int LoginUser(String username, String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedPassword = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        if (this.username.equals(username) && this.password.equals(bytesToHex(encodedPassword))) {
            return this.id;
        }
        return -1;
    }

    //src: http://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
