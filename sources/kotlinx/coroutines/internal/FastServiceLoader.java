package kotlinx.coroutines.internal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
/* compiled from: FastServiceLoader.kt */
/* loaded from: classes.dex */
public final class FastServiceLoader {
    public static final FastServiceLoader INSTANCE = new FastServiceLoader();

    private FastServiceLoader() {
    }

    public final List<MainDispatcherFactory> loadMainDispatcherFactory$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        MainDispatcherFactory mainDispatcherFactory;
        if (!FastServiceLoaderKt.getANDROID_DETECTED()) {
            ClassLoader classLoader = MainDispatcherFactory.class.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "clz.classLoader");
            return load(MainDispatcherFactory.class, classLoader);
        }
        try {
            ArrayList arrayList = new ArrayList(2);
            MainDispatcherFactory mainDispatcherFactory2 = null;
            try {
                mainDispatcherFactory = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused) {
                mainDispatcherFactory = null;
            }
            if (mainDispatcherFactory != null) {
                arrayList.add(mainDispatcherFactory);
            }
            try {
                mainDispatcherFactory2 = (MainDispatcherFactory) MainDispatcherFactory.class.cast(Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException unused2) {
            }
            if (mainDispatcherFactory2 != null) {
                arrayList.add(mainDispatcherFactory2);
                return arrayList;
            }
            return arrayList;
        } catch (Throwable unused3) {
            ClassLoader classLoader2 = MainDispatcherFactory.class.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader2, "clz.classLoader");
            return load(MainDispatcherFactory.class, classLoader2);
        }
    }

    private final <S> List<S> load(Class<S> cls, ClassLoader classLoader) {
        List<S> list;
        try {
            return loadProviders$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(cls, classLoader);
        } catch (Throwable unused) {
            ServiceLoader load = ServiceLoader.load(cls, classLoader);
            Intrinsics.checkNotNullExpressionValue(load, "load(service, loader)");
            list = CollectionsKt___CollectionsKt.toList(load);
            return list;
        }
    }

    public final <S> List<S> loadProviders$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Class<S> service, ClassLoader loader) {
        Set set;
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(loader, "loader");
        String name = service.getName();
        Enumeration<URL> urls = loader.getResources("META-INF/services/" + name);
        Intrinsics.checkNotNullExpressionValue(urls, "urls");
        ArrayList<URL> list = Collections.list(urls);
        Intrinsics.checkNotNullExpressionValue(list, "list(this)");
        ArrayList arrayList = new ArrayList();
        for (URL it : list) {
            FastServiceLoader fastServiceLoader = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, fastServiceLoader.parse(it));
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList);
        if (!(!set.isEmpty())) {
            throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
        }
        Set<String> set2 = set;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (String str : set2) {
            arrayList2.add(INSTANCE.getProviderInstance(str, loader, service));
        }
        return arrayList2;
    }

    private final <S> S getProviderInstance(String str, ClassLoader classLoader, Class<S> cls) {
        Class<?> cls2 = Class.forName(str, false, classLoader);
        if (!cls.isAssignableFrom(cls2)) {
            throw new IllegalArgumentException(("Expected service of class " + cls + ", but found " + cls2).toString());
        }
        return cls.cast(cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
    }

    private final List<String> parse(URL url) {
        boolean startsWith$default;
        String substringAfter$default;
        String substringBefore$default;
        String substringAfter$default2;
        String url2 = url.toString();
        Intrinsics.checkNotNullExpressionValue(url2, "url.toString()");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(url2, "jar", false, 2, null);
        if (startsWith$default) {
            substringAfter$default = StringsKt__StringsKt.substringAfter$default(url2, "jar:file:", null, 2, null);
            substringBefore$default = StringsKt__StringsKt.substringBefore$default(substringAfter$default, '!', (String) null, 2, (Object) null);
            substringAfter$default2 = StringsKt__StringsKt.substringAfter$default(url2, "!/", null, 2, null);
            JarFile jarFile = new JarFile(substringBefore$default, false);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(new ZipEntry(substringAfter$default2)), "UTF-8"));
                List<String> parseFile = INSTANCE.parseFile(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                jarFile.close();
                return parseFile;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        jarFile.close();
                        throw th2;
                    } catch (Throwable th3) {
                        ExceptionsKt__ExceptionsKt.addSuppressed(th, th3);
                        throw th;
                    }
                }
            }
        }
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url.openStream()));
        try {
            List<String> parseFile2 = INSTANCE.parseFile(bufferedReader2);
            CloseableKt.closeFinally(bufferedReader2, null);
            return parseFile2;
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                CloseableKt.closeFinally(bufferedReader2, th4);
                throw th5;
            }
        }
    }

    private final List<String> parseFile(BufferedReader bufferedReader) {
        List<String> list;
        String substringBefore$default;
        CharSequence trim;
        boolean z;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                substringBefore$default = StringsKt__StringsKt.substringBefore$default(readLine, "#", (String) null, 2, (Object) null);
                trim = StringsKt__StringsKt.trim(substringBefore$default);
                String obj = trim.toString();
                int i = 0;
                while (true) {
                    if (i >= obj.length()) {
                        z = true;
                        break;
                    }
                    char charAt = obj.charAt(i);
                    if (!(charAt == '.' || Character.isJavaIdentifierPart(charAt))) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    throw new IllegalArgumentException(("Illegal service provider class name: " + obj).toString());
                }
                if (obj.length() > 0) {
                    linkedHashSet.add(obj);
                }
            } else {
                list = CollectionsKt___CollectionsKt.toList(linkedHashSet);
                return list;
            }
        }
    }
}
