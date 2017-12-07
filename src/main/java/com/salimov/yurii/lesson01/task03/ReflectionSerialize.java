package com.salimov.yurii.lesson01.task03;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectionSerialize implements Serialize {

    @Override
    public void serialize(final String path, final Object obj) throws Exception {
        try (OutputStream stream = new FileOutputStream(path);
             DataOutputStream output = new DataOutputStream(stream)) {
            final Class<?> csl = obj.getClass();
            final Field[] fields = csl.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    final Class<?> type = field.getType();
                    if (type == String.class) {
                        output.writeUTF((String) field.get(obj));
                    } else if (type == int.class) {
                        output.writeInt(field.getInt(obj));
                    } else if (type == double.class) {
                        output.writeDouble(field.getDouble(obj));
                    } else if (type == long.class) {
                        output.writeLong(field.getLong(obj));
                    } else if (type == boolean.class) {
                        output.writeBoolean(field.getBoolean(obj));
                    }
                }
            }
        }
    }

    @Override
    public Object deserialize(final String path, final Class<?> cls) throws Exception {
        final Constructor[] constructor = cls.getConstructors();
        for (Constructor c : constructor) {
            c.setAccessible(true);
        }
        final Object obj = cls.newInstance();
        try (InputStream stream = new FileInputStream(path);
             DataInputStream input = new DataInputStream(stream)) {
            final Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    final Class<?> type = field.getType();
                    if (type == String.class) {
                        field.set(obj, input.readUTF());
                    } else if (type == int.class) {
                        field.setInt(obj, input.readInt());
                    } else if (type == double.class) {
                        field.setDouble(obj, input.readDouble());
                    } else if (type == long.class) {
                        field.setLong(obj, input.readLong());
                    } else if (type == boolean.class) {
                        field.setBoolean(obj, input.readBoolean());
                    }
                }
            }
        }
        return obj;
    }
}
