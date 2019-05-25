package com.paypal.formatter.Impl;

import com.paypal.formatter.pojos.Constants;
import com.paypal.formatter.Iface.Wrapper;
import com.paypal.formatter.Iface.WrapperSelector;

import static com.paypal.formatter.pojos.Constants.TYPES;


public class WrapperSelectorImpl implements WrapperSelector {


    @Override
    public Wrapper selectWrapper(TYPES type) {
        switch (type) {
            case LINK:
                return new LinkWrapper();
            case ENTITY:
                return new EntityWrapper(Constants.entityPrefix, Constants.entitySuffix);
            case USERNAME:
                return new UsernameWrapper();
            case NONE:
            default:
                return new EntityWrapper(Constants.nonePrefix, Constants.noneSuffix);
        }
    }
}
