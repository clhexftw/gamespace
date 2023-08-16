package androidx.window.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpecificationComputer.kt */
/* loaded from: classes.dex */
public final class ValidSpecification<T> extends SpecificationComputer<T> {
    private final Logger logger;
    private final String tag;
    private final T value;
    private final VerificationMode verificationMode;

    public final T getValue() {
        return this.value;
    }

    public final String getTag() {
        return this.tag;
    }

    public final VerificationMode getVerificationMode() {
        return this.verificationMode;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public ValidSpecification(T value, String tag, VerificationMode verificationMode, Logger logger) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(verificationMode, "verificationMode");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.value = value;
        this.tag = tag;
        this.verificationMode = verificationMode;
        this.logger = logger;
    }

    @Override // androidx.window.core.SpecificationComputer
    public SpecificationComputer<T> require(String message, Function1<? super T, Boolean> condition) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(condition, "condition");
        return condition.invoke((T) this.value).booleanValue() ? this : new FailedSpecification(this.value, this.tag, message, this.logger, this.verificationMode);
    }

    @Override // androidx.window.core.SpecificationComputer
    public T compute() {
        return this.value;
    }
}
