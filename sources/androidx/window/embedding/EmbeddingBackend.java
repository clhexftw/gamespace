package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.embedding.SplitController;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: EmbeddingBackend.kt */
/* loaded from: classes.dex */
public interface EmbeddingBackend {
    public static final Companion Companion = Companion.$$INSTANCE;

    static EmbeddingBackend getInstance(Context context) {
        return Companion.getInstance(context);
    }

    @ExperimentalWindowApi
    static void overrideDecorator(EmbeddingBackendDecorator embeddingBackendDecorator) {
        Companion.overrideDecorator(embeddingBackendDecorator);
    }

    @ExperimentalWindowApi
    static void reset() {
        Companion.reset();
    }

    void addRule(EmbeddingRule embeddingRule);

    void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer);

    void clearSplitAttributesCalculator();

    Set<EmbeddingRule> getRules();

    SplitController.SplitSupportStatus getSplitSupportStatus();

    boolean isActivityEmbedded(Activity activity);

    boolean isSplitAttributesCalculatorSupported();

    void removeRule(EmbeddingRule embeddingRule);

    void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer);

    void setRules(Set<? extends EmbeddingRule> set);

    @ExperimentalWindowApi
    void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> function1);

    /* compiled from: EmbeddingBackend.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Function1<? super EmbeddingBackend, ? extends EmbeddingBackend> decorator = new Function1<EmbeddingBackend, EmbeddingBackend>() { // from class: androidx.window.embedding.EmbeddingBackend$Companion$decorator$1
            @Override // kotlin.jvm.functions.Function1
            public final EmbeddingBackend invoke(EmbeddingBackend it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it;
            }
        };

        private Companion() {
        }

        public final EmbeddingBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return decorator.invoke(ExtensionEmbeddingBackend.Companion.getInstance(context));
        }

        @ExperimentalWindowApi
        public final void overrideDecorator(EmbeddingBackendDecorator overridingDecorator) {
            Intrinsics.checkNotNullParameter(overridingDecorator, "overridingDecorator");
            decorator = new EmbeddingBackend$Companion$overrideDecorator$1(overridingDecorator);
        }

        @ExperimentalWindowApi
        public final void reset() {
            decorator = new Function1<EmbeddingBackend, EmbeddingBackend>() { // from class: androidx.window.embedding.EmbeddingBackend$Companion$reset$1
                @Override // kotlin.jvm.functions.Function1
                public final EmbeddingBackend invoke(EmbeddingBackend it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it;
                }
            };
        }
    }
}
