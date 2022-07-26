package com.scs.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutput implements Serializable {

    private static final long serialVersionUID = 5085668922897630487L;

    private String desc;

}
