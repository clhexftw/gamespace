package kotlinx.coroutines.internal;

import android.annotation.SuppressLint;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ExceptionsConstructor.kt */
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
final class ClassValueCtorCache extends CtorCache {
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();
    private static final ClassValueCtorCache$cache$1 cache = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
    };

    private ClassValueCtorCache() {
    }

    @Override // kotlinx.coroutines.internal.CtorCache
    public Function1<Throwable, Throwable> get(Class<? extends Throwable> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = cache.get(key);
        Intrinsics.checkNotNullExpressionValue(obj, "cache.get(key)");
        return (Function1) obj;
    }
}
