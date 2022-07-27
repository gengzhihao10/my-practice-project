package com.c.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeepCloneDTO implements Cloneable {

    /**
     * 基础类型
     */
    private int num;

    /**
     * 引用类型，浅复制无法复制
     */
    private DeepInner deepInner;

    @Override
    public DeepCloneDTO clone() throws CloneNotSupportedException {
        DeepCloneDTO deepCloneDTO;
        deepCloneDTO = (DeepCloneDTO)super.clone();
        //需要对引用类型的成员变量也进行克隆，才能完成深克隆
        deepCloneDTO.setDeepInner(deepInner.clone());
        return deepCloneDTO;
    }
}
