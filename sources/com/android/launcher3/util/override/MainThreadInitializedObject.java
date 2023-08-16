package com.android.launcher3.util.override;
/* loaded from: classes.dex */
public class MainThreadInitializedObject<T> {
    private final ObjectProvider<T> mProvider;
    private T mValue;

    /* loaded from: classes.dex */
    public interface ObjectProvider<T> {
    }

    public MainThreadInitializedObject(ObjectProvider<T> objectProvider) {
        this.mProvider = objectProvider;
    }

    public void initializeForTesting(T t) {
        this.mValue = t;
    }

    public static <T> MainThreadInitializedObject<T> forOverride(final Class<T> cls, final int i) {
        return new MainThreadInitializedObject<>(new ObjectProvider() { // from class: com.android.launcher3.util.override.MainThreadInitializedObject$$ExternalSyntheticLambda0
        });
    }
}
