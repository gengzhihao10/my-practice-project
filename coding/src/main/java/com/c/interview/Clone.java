package com.c.interview;

import com.c.dto.DeepCloneDTO;
import com.c.dto.DeepInner;
import com.c.dto.ShallowCloneDTO;
import com.c.dto.ShallowInner;
import com.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class Clone{


    /**
     * @author gengzhihao
     * @date 2022/7/27 10:20
     * @description 测试
     * @param
     * @return
     **/
    public static void main(String[] args) throws CloneNotSupportedException {

        ShallowInner shallowInner = new ShallowInner();
        shallowInner.setName("汤姆");

        ShallowCloneDTO shallow = new ShallowCloneDTO();
        shallow.setNum(1);
        shallow.setShallowInner(shallowInner);
        log.info("浅复制 改变前 shallow {}", JsonUtils.objectToJson(shallow));
        ShallowCloneDTO shallowClone = shallow.clone();
        log.info("浅复制 改变前 克隆 {}", JsonUtils.objectToJson(shallowClone));

        shallowClone.getShallowInner().setName("老王");
        log.info("浅复制 改变后 shallow {}", JsonUtils.objectToJson(shallow));
        log.info("浅复制 改变后 克隆 {}", JsonUtils.objectToJson(shallowClone));

        log.info("----------------分割线--------------");

        DeepInner deepInner = new DeepInner();
        deepInner.setName("杰瑞");

        DeepCloneDTO deep = new DeepCloneDTO();
        deep.setNum(2);
        deep.setDeepInner(deepInner);
        log.info("深复制 改变前 deep{}", JsonUtils.objectToJson(deep));
        DeepCloneDTO deepClone = deep.clone();
        log.info("深复制 改变前 克隆{}", JsonUtils.objectToJson(deepClone));

        deepClone.getDeepInner().setName("老王");
        log.info("深复制 改变后 deep{}", JsonUtils.objectToJson(deep));
        log.info("深复制 改变后 克隆{}", JsonUtils.objectToJson(deepClone));

    }
}
