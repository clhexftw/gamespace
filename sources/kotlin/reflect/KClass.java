package kotlin.reflect;
/* compiled from: KClass.kt */
/* loaded from: classes2.dex */
public interface KClass<T> extends KDeclarationContainer, KAnnotatedElement {
    String getQualifiedName();

    String getSimpleName();

    boolean isInstance(Object obj);
}
