package com.dl7.studydemo.annotatation;

/**
 * Created by long on 2016/4/20.
 */
public class AnnotationUtil {

    @FieldInfo(value = {1, 2} )
    public String fieldInfo = "FiledInfo";

    public int i = 100;

    @MethodInfo(author = "long",
            data = "message",
            version = 2)
    public static String getMethodInfo() {
        return AnnotationUtil.class.getSimpleName();
    }
}
