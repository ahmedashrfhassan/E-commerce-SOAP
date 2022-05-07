package gov.iti.jets.util;

import gov.iti.jets.api.exceptions.MyNoSuchFieldException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class FieldExtractorUtil {
    private static final FieldExtractorUtil INSTANCE = new FieldExtractorUtil();

    private FieldExtractorUtil() {
    }

    public static FieldExtractorUtil getINSTANCE() {
        return INSTANCE;
    }

    public List<Field> getFields(Class<?> clazz, List<String> fields) {
        return fields.stream().map(name -> getField(clazz, name)).collect(Collectors.toList());
    }

    private Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new MyNoSuchFieldException(e.getMessage() + " is not field in " + clazz.getSimpleName());
        }
    }
}
