package androidx.window.embedding;

import android.content.Context;
import androidx.window.embedding.EmbeddingBackend;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RuleController.kt */
/* loaded from: classes.dex */
public final class RuleController {
    public static final Companion Companion = new Companion(null);
    private final EmbeddingBackend embeddingBackend;

    public /* synthetic */ RuleController(EmbeddingBackend embeddingBackend, DefaultConstructorMarker defaultConstructorMarker) {
        this(embeddingBackend);
    }

    public static final RuleController getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public static final Set<EmbeddingRule> parseRules(Context context, int i) {
        return Companion.parseRules(context, i);
    }

    private RuleController(EmbeddingBackend embeddingBackend) {
        this.embeddingBackend = embeddingBackend;
    }

    public final Set<EmbeddingRule> getRules() {
        Set<EmbeddingRule> set;
        set = CollectionsKt___CollectionsKt.toSet(this.embeddingBackend.getRules());
        return set;
    }

    public final void addRule(EmbeddingRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        this.embeddingBackend.addRule(rule);
    }

    public final void removeRule(EmbeddingRule rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        this.embeddingBackend.removeRule(rule);
    }

    public final void setRules(Set<? extends EmbeddingRule> rules) {
        Intrinsics.checkNotNullParameter(rules, "rules");
        this.embeddingBackend.setRules(rules);
    }

    public final void clearRules() {
        Set<? extends EmbeddingRule> emptySet;
        EmbeddingBackend embeddingBackend = this.embeddingBackend;
        emptySet = SetsKt__SetsKt.emptySet();
        embeddingBackend.setRules(emptySet);
    }

    /* compiled from: RuleController.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RuleController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Context applicationContext = context.getApplicationContext();
            EmbeddingBackend.Companion companion = EmbeddingBackend.Companion;
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            return new RuleController(companion.getInstance(applicationContext), null);
        }

        public final Set<EmbeddingRule> parseRules(Context context, int i) {
            Set<EmbeddingRule> emptySet;
            Intrinsics.checkNotNullParameter(context, "context");
            RuleParser ruleParser = RuleParser.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            Set<EmbeddingRule> parseRules$window_release = ruleParser.parseRules$window_release(applicationContext, i);
            if (parseRules$window_release == null) {
                emptySet = SetsKt__SetsKt.emptySet();
                return emptySet;
            }
            return parseRules$window_release;
        }
    }
}
