package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.exception.SerializationException;
import edu.uade.apd.tpo.repository.stub.BaseStub;

import java.io.Serializable;
import java.lang.reflect.Field;

public class SerializationUtils {

    static <T extends BaseStub, R> T toStub(R r, Class<T> clazz) {
        return clone(r, clazz);
    }

    static <T, R extends BaseStub> T fromStub(R r, Class<T> clazz) {
        return clone(r, clazz);
    }

    private static <T, R> T clone(R r, Class<T> clazz) {
        T t;
        try {
            t = clazz.newInstance();
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                Field f2 = r.getClass().getDeclaredField(f.getName());
                f2.setAccessible(true);
                f.set(t, f2.get(r));
            }
        } catch (Exception e) {
            throw new SerializationException("Error mapping object", e);
        }
        return t;
    }

}
