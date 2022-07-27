package com.c.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShallowCloneDTO implements Cloneable{

    private int num;

    private ShallowInner shallowInner;


    /**
     * @author gengzhihao
     * @date 2022/7/27 10:25
     * @description 必须实现Cloneable接口并重写clone方法，但对引用类型的成员变量无法复制。
     * 需该成员变量也实现Cloneable并重写clone，才能实现引用类型成员变量的深复制。
     * @param null
     * @return
     **/
    @Override
    public ShallowCloneDTO clone() throws CloneNotSupportedException {

        return (ShallowCloneDTO) super.clone();
    }

}
