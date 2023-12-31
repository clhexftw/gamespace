package androidx.window.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SpecificationComputer.kt */
/* loaded from: classes.dex */
public abstract class SpecificationComputer<T> {
    public static final Companion Companion = new Companion(null);

    public abstract T compute();

    public abstract SpecificationComputer<T> require(String str, Function1<? super T, Boolean> function1);

    /* JADX INFO: Access modifiers changed from: protected */
    public final String createMessage(Object value, String message) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(message, "message");
        return message + " value: " + value;
    }

    /* compiled from: SpecificationComputer.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ SpecificationComputer startSpecification$default(Companion companion, Object obj, String str, VerificationMode verificationMode, Logger logger, int i, Object obj2) {
            if ((i & 2) != 0) {
                verificationMode = BuildConfig.INSTANCE.getVerificationMode();
            }
            if ((i & 4) != 0) {
                logger = AndroidLogger.INSTANCE;
            }
            return companion.startSpecification(obj, str, verificationMode, logger);
        }

        public final <T> SpecificationComputer<T> startSpecification(T t, String tag, VerificationMode verificationMode, Logger logger) {
            Intrinsics.checkNotNullParameter(t, "<this>");
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(verificationMode, "verificationMode");
            Intrinsics.checkNotNullParameter(logger, "logger");
            return new ValidSpecification(t, tag, verificationMode, logger);
        }
    }
}
