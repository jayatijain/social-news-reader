package com.paypal.formatter.Impl;

import com.paypal.formatter.Iface.Wrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class UsernameWrapper implements Wrapper {

    public String wrap(String wrapMe) {
        return "<a href=\"http://twitter.com/" + wrapMe + "\">" + wrapMe + "</a>";
    }

}
