package com.paypal.formatter.Impl;

import com.paypal.formatter.Iface.Wrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Getter
@Setter
public class EntityWrapper implements Wrapper {

    public String prefix;
    public String suffix;

    public String wrap(String wrapMe) {
        return prefix.concat(wrapMe.concat(suffix));
    }

}
