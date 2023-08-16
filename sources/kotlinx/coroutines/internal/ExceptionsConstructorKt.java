package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
/* compiled from: ExceptionsConstructor.kt */
/* loaded from: classes2.dex */
public final class ExceptionsConstructorKt {
    private static final CtorCache ctorCache;
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    static {
        CtorCache ctorCache2;
        try {
            ctorCache2 = FastServiceLoaderKt.getANDROID_DETECTED() ? WeakMapCtorCache.INSTANCE : ClassValueCtorCache.INSTANCE;
        } catch (Throwable unused) {
            ctorCache2 = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = ctorCache2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends Throwable> E tryCopyException(E exception) {
        Object m2159constructorimpl;
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (exception instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.Companion;
                m2159constructorimpl = Result.m2159constructorimpl(((CopyableThrowable) exception).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m2163isFailureimpl(m2159constructorimpl)) {
                m2159constructorimpl = null;
            }
            return (E) m2159constructorimpl;
        }
        return (E) ctorCache.get(exception.getClass()).invoke(exception);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E extends Throwable> Function1<Throwable, Throwable> createConstructor(Class<E> cls) {
        List<Constructor> sortedWith;
        ExceptionsConstructorKt$createConstructor$nullResult$1 exceptionsConstructorKt$createConstructor$nullResult$1 = new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return null;
            }
        };
        if (throwableFields != fieldsCountOrDefault(cls, 0)) {
            return exceptionsConstructorKt$createConstructor$nullResult$1;
        }
        Constructor<?>[] constructors = cls.getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "clz.constructors");
        sortedWith = ArraysKt___ArraysKt.sortedWith(constructors, new Comparator() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((Constructor) t2).getParameterTypes().length), Integer.valueOf(((Constructor) t).getParameterTypes().length));
                return compareValues;
            }
        });
        for (Constructor constructor : sortedWith) {
            Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
            Function1<Throwable, Throwable> createSafeConstructor = createSafeConstructor(constructor);
            if (createSafeConstructor != null) {
                return createSafeConstructor;
            }
        }
        return exceptionsConstructorKt$createConstructor$nullResult$1;
    }

    private static final Function1<Throwable, Throwable> createSafeConstructor(final Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length != 0) {
            if (length != 1) {
                if (length == 2 && Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                    return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Throwable invoke(Throwable e) {
                            Object m2159constructorimpl;
                            Intrinsics.checkNotNullParameter(e, "e");
                            try {
                                Result.Companion companion = Result.Companion;
                                Object newInstance = constructor.newInstance(e.getMessage(), e);
                                Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                                m2159constructorimpl = Result.m2159constructorimpl((Throwable) newInstance);
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.Companion;
                                m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m2163isFailureimpl(m2159constructorimpl)) {
                                m2159constructorimpl = null;
                            }
                            return (Throwable) m2159constructorimpl;
                        }
                    };
                }
                return null;
            }
            Class<?> cls = parameterTypes[0];
            if (Intrinsics.areEqual(cls, Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable e) {
                        Object m2159constructorimpl;
                        Intrinsics.checkNotNullParameter(e, "e");
                        try {
                            Result.Companion companion = Result.Companion;
                            Object newInstance = constructor.newInstance(e);
                            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                            m2159constructorimpl = Result.m2159constructorimpl((Throwable) newInstance);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m2163isFailureimpl(m2159constructorimpl)) {
                            m2159constructorimpl = null;
                        }
                        return (Throwable) m2159constructorimpl;
                    }
                };
            }
            if (Intrinsics.areEqual(cls, String.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable e) {
                        Object m2159constructorimpl;
                        Intrinsics.checkNotNullParameter(e, "e");
                        try {
                            Result.Companion companion = Result.Companion;
                            Object newInstance = constructor.newInstance(e.getMessage());
                            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                            Throwable th = (Throwable) newInstance;
                            th.initCause(e);
                            m2159constructorimpl = Result.m2159constructorimpl(th);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.Companion;
                            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m2163isFailureimpl(m2159constructorimpl)) {
                            m2159constructorimpl = null;
                        }
                        return (Throwable) m2159constructorimpl;
                    }
                };
            }
            return null;
        }
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable e) {
                Object m2159constructorimpl;
                Intrinsics.checkNotNullParameter(e, "e");
                try {
                    Result.Companion companion = Result.Companion;
                    Object newInstance = constructor.newInstance(new Object[0]);
                    Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
                    Throwable th = (Throwable) newInstance;
                    th.initCause(e);
                    m2159constructorimpl = Result.m2159constructorimpl(th);
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.Companion;
                    m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m2163isFailureimpl(m2159constructorimpl)) {
                    m2159constructorimpl = null;
                }
                return (Throwable) m2159constructorimpl;
            }
        };
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int i) {
        Integer m2159constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m2159constructorimpl = Result.m2159constructorimpl(ResultKt.createFailure(th));
        }
        Integer valueOf = Integer.valueOf(i);
        if (Result.m2163isFailureimpl(m2159constructorimpl)) {
            m2159constructorimpl = valueOf;
        }
        return ((Number) m2159constructorimpl).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class<?> cls, int i) {
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
            int i2 = 0;
            for (Field field : declaredFields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    i2++;
                }
            }
            i += i2;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i;
    }
}
