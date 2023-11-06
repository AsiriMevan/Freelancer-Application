package com.example.demopockanchana.utill;

import java.util.UUID;

public class BusinessKeyGenerator {

    public static String getUUID()
    {
        return String.format("%s", UUID.randomUUID().toString());
    }
}
