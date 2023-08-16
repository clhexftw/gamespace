package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActivityEmbeddingController.kt */
/* loaded from: classes.dex */
public final class ActivityEmbeddingController {
    public static final Companion Companion = new Companion(null);
    private final EmbeddingBackend backend;

    public static final ActivityEmbeddingController getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public ActivityEmbeddingController(EmbeddingBackend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.backend = backend;
    }

    public final boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.backend.isActivityEmbedded(activity);
    }

    /* compiled from: ActivityEmbeddingController.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ActivityEmbeddingController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new ActivityEmbeddingController(EmbeddingBackend.Companion.getInstance(context));
        }
    }
}
