package com.sahakian.jsondiff;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.sahakian.jsondiff.model.Result;
import org.junit.jupiter.api.Test;

class DiffTest {

    //language=JSON
    String actual = """
        {
          "squadName": "Super hero squad",
          "homeTown": "Metro City",
          "formed": 2016,
          "secretBase": "Super tower",
          "active": true,
          "members": [
            {
              "name": "Molecule Man",
              "age": 29,
              "secretIdentity": "Dan Jukes",
              "powers": ["Radiation resistance", "Turning tiny", "Radiation blast"]
            }]
            }""";


    //language=JSON
    String old = """
        {
          "squadName": "Super hero squad",
          "homeTown": "Metro City",
          "formed": 2016,
          "secretBase": "Super tower",
          "active": true,
          "members": [
            {
              "name": "Molecule Man",
              "age": 30,
              "secretIdentity": "Dan Jukes",
              "powers": ["Radiation resistance", "Turning tiny", "Radiation blast"]
            },
            {
              "name": "Vzgo",
              "age": 40,
              "secretIdentity": "Dan Jukes"
            }]
            }""";

    @Test
    void diff() {
        List<Result> diff = new Diff().diff(actual, old);
        var x =9;
    }
}