package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.window.core.BuildConfig;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExperimentalWindowApi;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.VerificationMode;
import androidx.window.embedding.EmbeddingCompat;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.embedding.SplitController;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: EmbeddingCompat.kt */
/* loaded from: classes.dex */
public final class EmbeddingCompat implements EmbeddingInterfaceCompat {
    public static final Companion Companion = new Companion(null);
    public static final boolean DEBUG = true;
    private static final String TAG = "EmbeddingCompat";
    private final EmbeddingAdapter adapter;
    private final Context applicationContext;
    private final ConsumerAdapter consumerAdapter;
    private final ActivityEmbeddingComponent embeddingExtension;

    public EmbeddingCompat(ActivityEmbeddingComponent embeddingExtension, EmbeddingAdapter adapter, ConsumerAdapter consumerAdapter, Context applicationContext) {
        Intrinsics.checkNotNullParameter(embeddingExtension, "embeddingExtension");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.embeddingExtension = embeddingExtension;
        this.adapter = adapter;
        this.consumerAdapter = consumerAdapter;
        this.applicationContext = applicationContext;
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setRules(Set<? extends EmbeddingRule> rules) {
        boolean z;
        Intrinsics.checkNotNullParameter(rules, "rules");
        Iterator<? extends EmbeddingRule> it = rules.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next() instanceof SplitRule) {
                z = true;
                break;
            }
        }
        if (z && !Intrinsics.areEqual(SplitController.Companion.getInstance(this.applicationContext).getSplitSupportStatus(), SplitController.SplitSupportStatus.SPLIT_AVAILABLE)) {
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                Log.w(TAG, "Cannot set SplitRule because ActivityEmbedding Split is not supported or PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED is not set.");
                return;
            }
            return;
        }
        this.embeddingExtension.setEmbeddingRules(this.adapter.translate(this.applicationContext, rules));
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void setEmbeddingCallback(final EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallback) {
        Intrinsics.checkNotNullParameter(embeddingCallback, "embeddingCallback");
        if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
            this.consumerAdapter.addConsumer(this.embeddingExtension, Reflection.getOrCreateKotlinClass(List.class), "setSplitInfoCallback", new Function1<List<?>, Unit>() { // from class: androidx.window.embedding.EmbeddingCompat$setEmbeddingCallback$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<?> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(List<?> values) {
                    EmbeddingAdapter embeddingAdapter;
                    Intrinsics.checkNotNullParameter(values, "values");
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : values) {
                        if (obj instanceof androidx.window.extensions.embedding.SplitInfo) {
                            arrayList.add(obj);
                        }
                    }
                    EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallbackInterface = EmbeddingInterfaceCompat.EmbeddingCallbackInterface.this;
                    embeddingAdapter = this.adapter;
                    embeddingCallbackInterface.onSplitInfoChanged(embeddingAdapter.translate(arrayList));
                }
            });
            return;
        }
        this.embeddingExtension.setSplitInfoCallback(new Consumer() { // from class: androidx.window.embedding.EmbeddingCompat$$ExternalSyntheticLambda0
            @Override // androidx.window.extensions.core.util.function.Consumer
            public final void accept(Object obj) {
                EmbeddingCompat.setEmbeddingCallback$lambda$0(EmbeddingInterfaceCompat.EmbeddingCallbackInterface.this, this, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setEmbeddingCallback$lambda$0(EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallback, EmbeddingCompat this$0, List splitInfoList) {
        Intrinsics.checkNotNullParameter(embeddingCallback, "$embeddingCallback");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EmbeddingAdapter embeddingAdapter = this$0.adapter;
        Intrinsics.checkNotNullExpressionValue(splitInfoList, "splitInfoList");
        embeddingCallback.onSplitInfoChanged(embeddingAdapter.translate(splitInfoList));
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.embeddingExtension.isActivityEmbedded(activity);
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    @ExperimentalWindowApi
    public void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> calculator) {
        Intrinsics.checkNotNullParameter(calculator, "calculator");
        if (!isSplitAttributesCalculatorSupported()) {
            throw new UnsupportedOperationException("#setSplitAttributesCalculator is not supported on the device.");
        }
        this.embeddingExtension.setSplitAttributesCalculator(this.adapter.translateSplitAttributesCalculator(calculator));
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public void clearSplitAttributesCalculator() {
        if (!isSplitAttributesCalculatorSupported()) {
            throw new UnsupportedOperationException("#clearSplitAttributesCalculator is not supported on the device.");
        }
        this.embeddingExtension.clearSplitAttributesCalculator();
    }

    @Override // androidx.window.embedding.EmbeddingInterfaceCompat
    public boolean isSplitAttributesCalculatorSupported() {
        return ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() >= 2;
    }

    /* compiled from: EmbeddingCompat.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isEmbeddingAvailable() {
            try {
                ClassLoader classLoader = EmbeddingCompat.class.getClassLoader();
                if (classLoader != null) {
                    ConsumerAdapter consumerAdapter = new ConsumerAdapter(classLoader);
                    WindowExtensions windowExtensions = WindowExtensionsProvider.getWindowExtensions();
                    Intrinsics.checkNotNullExpressionValue(windowExtensions, "getWindowExtensions()");
                    return new SafeActivityEmbeddingComponentProvider(classLoader, consumerAdapter, windowExtensions).getActivityEmbeddingComponent() != null;
                }
                return false;
            } catch (NoClassDefFoundError unused) {
                Log.d(EmbeddingCompat.TAG, "Embedding extension version not found");
                return false;
            } catch (UnsupportedOperationException unused2) {
                Log.d(EmbeddingCompat.TAG, "Stub Extension");
                return false;
            }
        }

        public final ActivityEmbeddingComponent embeddingComponent() {
            if (isEmbeddingAvailable()) {
                ClassLoader classLoader = EmbeddingCompat.class.getClassLoader();
                if (classLoader != null) {
                    ConsumerAdapter consumerAdapter = new ConsumerAdapter(classLoader);
                    WindowExtensions windowExtensions = WindowExtensionsProvider.getWindowExtensions();
                    Intrinsics.checkNotNullExpressionValue(windowExtensions, "getWindowExtensions()");
                    ActivityEmbeddingComponent activityEmbeddingComponent = new SafeActivityEmbeddingComponentProvider(classLoader, consumerAdapter, windowExtensions).getActivityEmbeddingComponent();
                    if (activityEmbeddingComponent != null) {
                        return activityEmbeddingComponent;
                    }
                }
                return emptyActivityEmbeddingProxy();
            }
            return emptyActivityEmbeddingProxy();
        }

        private final ActivityEmbeddingComponent emptyActivityEmbeddingProxy() {
            Object newProxyInstance = Proxy.newProxyInstance(EmbeddingCompat.class.getClassLoader(), new Class[]{ActivityEmbeddingComponent.class}, new InvocationHandler() { // from class: androidx.window.embedding.EmbeddingCompat$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    Unit emptyActivityEmbeddingProxy$lambda$2;
                    emptyActivityEmbeddingProxy$lambda$2 = EmbeddingCompat.Companion.emptyActivityEmbeddingProxy$lambda$2(obj, method, objArr);
                    return emptyActivityEmbeddingProxy$lambda$2;
                }
            });
            Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type androidx.window.extensions.embedding.ActivityEmbeddingComponent");
            return (ActivityEmbeddingComponent) newProxyInstance;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit emptyActivityEmbeddingProxy$lambda$2(Object obj, Method method, Object[] objArr) {
            return Unit.INSTANCE;
        }
    }
}
