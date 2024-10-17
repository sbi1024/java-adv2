package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BasicData helloInstance = new BasicData();
        helloInstance.call();

        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello";
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class);
        Object resultValue = method1.invoke(helloInstance, "h1");
    }
}
