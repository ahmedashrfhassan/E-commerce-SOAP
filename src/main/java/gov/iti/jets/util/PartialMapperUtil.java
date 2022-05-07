package gov.iti.jets.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PartialMapperUtil {
    private final static PartialMapperUtil INSTANCE=new PartialMapperUtil();
    private PartialMapperUtil() {
    }

    public static PartialMapperUtil getINSTANCE() {
        return INSTANCE;
    }

    public <T> void mapVolData(T sourceObj,T destObj) {
        Class<?> clazz = sourceObj.getClass();
        for (Method getMethod : clazz.getMethods()) {
            if (getMethod.getName().startsWith("get")) {
                try {
                    Object invokedObj = getMethod.invoke(sourceObj);
                    if (invokedObj != null) {
                        Method setMethod = destObj.getClass().getMethod(getMethod.getName().replace("get", "set"), getMethod.getReturnType());
                        setMethod.invoke(destObj, invokedObj);
                    }

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    //not found set
                }
            }
        }
    }
}
