package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
/* loaded from: classes2.dex */
public abstract class ReflectionInflater<T> extends SimpleInflater<T> {
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<?>> constructorMap = new HashMap<>();
    private final Context context;
    private String defaultPackage;
    private final Object[] tempConstructorArgs;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReflectionInflater(Context context) {
        super(context.getResources());
        this.tempConstructorArgs = new Object[2];
        this.context = context;
    }

    public final T createItem(String str, String str2, AttributeSet attributeSet) {
        String concat = (str2 == null || str.indexOf(46) != -1) ? str : str2.concat(str);
        HashMap<String, Constructor<?>> hashMap = constructorMap;
        Constructor<?> constructor = hashMap.get(concat);
        if (constructor == null) {
            try {
                constructor = this.context.getClassLoader().loadClass(concat).getConstructor(CONSTRUCTOR_SIGNATURE);
                constructor.setAccessible(true);
                hashMap.put(str, constructor);
            } catch (Exception e) {
                throw new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + concat, e);
            }
        }
        Object[] objArr = this.tempConstructorArgs;
        objArr[0] = this.context;
        objArr[1] = attributeSet;
        T t = (T) constructor.newInstance(objArr);
        Object[] objArr2 = this.tempConstructorArgs;
        objArr2[0] = null;
        objArr2[1] = null;
        return t;
    }

    @Override // com.google.android.setupdesign.items.SimpleInflater
    protected T onCreateItem(String str, AttributeSet attributeSet) {
        return createItem(str, this.defaultPackage, attributeSet);
    }

    public void setDefaultPackage(String str) {
        this.defaultPackage = str;
    }
}
