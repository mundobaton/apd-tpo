package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.exception.SerializationException;
import edu.uade.apd.tpo.repository.stub.BaseStub;

import java.lang.reflect.Field;

public class SerializationUtils {

    static <T extends BaseStub, R> T toStub(R r, Class<T> clazz) {
        return clone(r, clazz);
    }

    static <T, R extends BaseStub> T fromStub(R r, Class<T> clazz) {
        return clone(r, clazz);
    }

    private static <T, R> T clone(R r, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            for (Field f : t.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Field f2 = r.getClass().getDeclaredField(f.getName());
                    f2.setAccessible(true);
                    if (!f2.getType().isPrimitive() && !f2.getType().getSimpleName().equals("String")) {
                        f.set(t, clone(f2.get(r), f.getType()));
                    } else {
                        f.set(t, f2.get(r));
                    }
                } catch (NoSuchFieldException e) {
                    //Continue with others fields
                }

            }
        } catch (Exception e) {
            throw new SerializationException("Error mapping object", e);
        }
        return t;
    }

}
