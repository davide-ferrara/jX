package com.davideferrara.jx.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.davideferrara.jx.exceptions.ProfileException;

public abstract class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Questa è una classe di utility e non può essere instanziata!");
    }

    public static <T> T getValueFromOptional(Optional<T> optional, String errorMessage){
        return optional.orElseThrow(() -> new ProfileException(errorMessage));
    }

    public static Map<String, String> mapFromValues(List<String> values){
        if (values == null || values.size() % 2 != 0) {
            throw new IllegalArgumentException("La lista deve contenere un numero pari di elementi!");
        }
        Map<String, String> map = new HashMap<>();
        for(int i=0; i<values.size()-1; i++){
            map.put(values.get(i), values.get(i+1));
        }
        return map;
    }
}
