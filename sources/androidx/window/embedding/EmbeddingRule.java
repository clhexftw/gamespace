package androidx.window.embedding;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: EmbeddingRule.kt */
/* loaded from: classes.dex */
public abstract class EmbeddingRule {
    private final String tag;

    public EmbeddingRule(String str) {
        this.tag = str;
    }

    public final String getTag() {
        return this.tag;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmbeddingRule) {
            return Intrinsics.areEqual(this.tag, ((EmbeddingRule) obj).tag);
        }
        return false;
    }

    public int hashCode() {
        String str = this.tag;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
