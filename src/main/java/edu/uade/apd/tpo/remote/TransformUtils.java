package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.exception.SerializationException;
import edu.uade.apd.tpo.repository.stub.BaseStub;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformUtils {

    public static <T, R> T to(R r, Class<T> clazz) {
        return clone(r, clazz);
    }

    private static <T, R> T clone(R r, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            for (Field f : getFields(t.getClass()).values()) {
                f.setAccessible(true);

                Field f2 = getFields(r.getClass()).get(f.getName());
                f2.setAccessible(true);
                Object fieldValue = f2.get(r);
                if (fieldValue != null) {
                    if (!f2.getType().isPrimitive() && !f2.getType().getSimpleName().equals("String")) {
                        f.set(t, clone(fieldValue, f.getType()));
                    } else {
                        f.set(t, fieldValue);
                    }
                }
            }
        } catch (InstantiationException ie) {
            //Continue with other objects
        } catch (Exception e) {
            throw new SerializationException("Error mapping object", e);
        }
        return t;
    }

    private static Map<String, Field> getFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields.parallelStream().collect(Collectors.toMap(Field::getName,
                Function.identity()));
    }

}
