package com.scs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scs.input.PersonInput;
import com.scs.output.PersonOutput;

public interface PersonService {

    PersonOutput eat(PersonInput personInput) throws JsonProcessingException;
}
