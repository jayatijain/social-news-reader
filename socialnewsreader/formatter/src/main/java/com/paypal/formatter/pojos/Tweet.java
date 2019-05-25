package com.paypal.formatter.pojos;

import com.paypal.formatter.Iface.Input;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class Tweet implements Input {
    @NotNull
    public long id; // identifier to link Tweet and Processed Pojo containing entity information for tweet
    @NotNull
    public String tweet;

    @Override
    public boolean isValid() {
        return tweet.length() > 0; //other validity conditions come here
    }
}
