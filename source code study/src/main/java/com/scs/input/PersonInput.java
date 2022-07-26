package com.scs.input;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput implements Serializable {

    private static final long serialVersionUID = 1016875142483649602L;

    private String food;

}
