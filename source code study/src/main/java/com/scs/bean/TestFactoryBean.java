package com.scs.bean;

import org.springframework.beans.factory.FactoryBean;

public class TestFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
