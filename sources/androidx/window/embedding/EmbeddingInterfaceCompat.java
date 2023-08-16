package androidx.window.embedding;

import android.app.Activity;
import androidx.window.core.ExperimentalWindowApi;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function1;
/* compiled from: EmbeddingInterfaceCompat.kt */
/* loaded from: classes.dex */
public interface EmbeddingInterfaceCompat {

    /* compiled from: EmbeddingInterfaceCompat.kt */
    /* loaded from: classes.dex */
    public interface EmbeddingCallbackInterface {
        void onSplitInfoChanged(List<SplitInfo> list);
    }

    void clearSplitAttributesCalculator();

    boolean isActivityEmbedded(Activity activity);

    boolean isSplitAttributesCalculatorSupported();

    void setEmbeddingCallback(EmbeddingCallbackInterface embeddingCallbackInterface);

    void setRules(Set<? extends EmbeddingRule> set);

    @ExperimentalWindowApi
    void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> function1);
}
