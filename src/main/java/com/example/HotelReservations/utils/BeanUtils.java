package com.example.HotelReservations.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

/*
 * Класс для копирования данных из одного объекта в другой,
 * если поля source не равны null
 */


@UtilityClass
public class BeanUtils {

    @SneakyThrows
    public void copyNonNullProperties(Object source, Object destination){
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            Object value = field.get(source);

            if (value != null){
                field.set(destination, value);
            }
        }
    }

}
