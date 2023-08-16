package androidx.window.embedding;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: EmbeddingAspectRatio.kt */
/* loaded from: classes.dex */
public final class EmbeddingAspectRatio {
    private final String description;
    private final float value;
    public static final Companion Companion = new Companion(null);
    public static final EmbeddingAspectRatio ALWAYS_ALLOW = new EmbeddingAspectRatio("ALWAYS_ALLOW", 0.0f);
    public static final EmbeddingAspectRatio ALWAYS_DISALLOW = new EmbeddingAspectRatio("ALWAYS_DISALLOW", -1.0f);

    public /* synthetic */ EmbeddingAspectRatio(String str, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, f);
    }

    public static final EmbeddingAspectRatio ratio(float f) {
        return Companion.ratio(f);
    }

    private EmbeddingAspectRatio(String str, float f) {
        this.description = str;
        this.value = f;
    }

    public final String getDescription$window_release() {
        return this.description;
    }

    public final float getValue$window_release() {
        return this.value;
    }

    public String toString() {
        return "EmbeddingAspectRatio(" + this.description + ')';
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EmbeddingAspectRatio) {
            EmbeddingAspectRatio embeddingAspectRatio = (EmbeddingAspectRatio) obj;
            return ((this.value > embeddingAspectRatio.value ? 1 : (this.value == embeddingAspectRatio.value ? 0 : -1)) == 0) && Intrinsics.areEqual(this.description, embeddingAspectRatio.description);
        }
        return false;
    }

    public int hashCode() {
        return this.description.hashCode() + (Float.hashCode(this.value) * 31);
    }

    /* compiled from: EmbeddingAspectRatio.kt */
    @SourceDebugExtension({"SMAP\nEmbeddingAspectRatio.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EmbeddingAspectRatio.kt\nandroidx/window/embedding/EmbeddingAspectRatio$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,108:1\n1#2:109\n*E\n"})
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EmbeddingAspectRatio ratio(float f) {
            if (!(f > 1.0f)) {
                throw new IllegalArgumentException("Ratio must be greater than 1.".toString());
            }
            return new EmbeddingAspectRatio("ratio:" + f, f, null);
        }

        public final EmbeddingAspectRatio buildAspectRatioFromValue$window_release(float f) {
            EmbeddingAspectRatio embeddingAspectRatio = EmbeddingAspectRatio.ALWAYS_ALLOW;
            if (f == embeddingAspectRatio.getValue$window_release()) {
                return embeddingAspectRatio;
            }
            EmbeddingAspectRatio embeddingAspectRatio2 = EmbeddingAspectRatio.ALWAYS_DISALLOW;
            return f == embeddingAspectRatio2.getValue$window_release() ? embeddingAspectRatio2 : ratio(f);
        }
    }
}
