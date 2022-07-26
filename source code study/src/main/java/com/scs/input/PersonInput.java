package com.scs.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput implements Serializable {

    private static final long serialVersionUID = 1016875142483649602L;

    private String food;

}
