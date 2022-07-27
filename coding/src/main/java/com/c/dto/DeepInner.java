package com.c.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gengzhihao
 * @date 2022/7/27 10:12
 * @description 深复制类的成员变量类
**/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeepInner implements Cloneable{

    private String name;

    @Override
    public DeepInner clone() throws CloneNotSupportedException {
        return (DeepInner)super.clone();
    }
}
