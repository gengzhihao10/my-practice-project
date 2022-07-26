package com.scs.output;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutput implements Serializable {

    private static final long serialVersionUID = 5085668922897630487L;

    private String desc;

}
