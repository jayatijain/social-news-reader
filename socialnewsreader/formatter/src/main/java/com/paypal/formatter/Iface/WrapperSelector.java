package com.paypal.formatter.Iface;

import static com.paypal.formatter.pojos.Constants.TYPES;

public interface WrapperSelector {

    Wrapper selectWrapper(TYPES type);
}
