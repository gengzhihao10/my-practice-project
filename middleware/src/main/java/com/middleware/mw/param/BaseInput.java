package com.middleware.mw.param;


import java.io.Serializable;

/**
 * @author gengzhihao
 * @date 2022/10/24 9:33
 * @description 入参出参顶层父类
**/

public abstract class BaseInput implements Serializable {

    private static final long serialVersionUID = -8108503875487739539L;

    public void check(){}
}
