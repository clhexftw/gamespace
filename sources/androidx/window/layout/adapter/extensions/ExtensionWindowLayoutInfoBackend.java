package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: ExtensionWindowLayoutInfoBackend.kt */
/* loaded from: classes.dex */
public final class ExtensionWindowLayoutInfoBackend implements WindowBackend {
    private final WindowLayoutComponent component;
    private final ConsumerAdapter consumerAdapter;
    private final Map<MulticastConsumer, Consumer<WindowLayoutInfo>> consumerToOemConsumer;
    private final Map<MulticastConsumer, ConsumerAdapter.Subscription> consumerToToken;
    private final Map<Context, MulticastConsumer> contextToListeners;
    private final ReentrantLock extensionWindowBackendLock;
    private final Map<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>, Context> listenerToContext;

    public ExtensionWindowLayoutInfoBackend(WindowLayoutComponent component, ConsumerAdapter consumerAdapter) {
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(consumerAdapter, "consumerAdapter");
        this.component = component;
        this.consumerAdapter = consumerAdapter;
        this.extensionWindowBackendLock = new ReentrantLock();
        this.contextToListeners = new LinkedHashMap();
        this.listenerToContext = new LinkedHashMap();
        this.consumerToToken = new LinkedHashMap();
        this.consumerToOemConsumer = new LinkedHashMap();
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> callback) {
        Unit unit;
        List emptyList;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        try {
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer != null) {
                multicastConsumer.addListener(callback);
                this.listenerToContext.put(callback, context);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                final MulticastConsumer multicastConsumer2 = new MulticastConsumer(context);
                this.contextToListeners.put(context, multicastConsumer2);
                this.listenerToContext.put(callback, context);
                multicastConsumer2.addListener(callback);
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                    Function1<WindowLayoutInfo, Unit> function1 = new Function1<WindowLayoutInfo, Unit>() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WindowLayoutInfo windowLayoutInfo) {
                            invoke2(windowLayoutInfo);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(WindowLayoutInfo value) {
                            Intrinsics.checkNotNullParameter(value, "value");
                            ExtensionWindowLayoutInfoBackend.MulticastConsumer.this.accept(value);
                        }
                    };
                    if (context instanceof Activity) {
                        this.consumerToToken.put(multicastConsumer2, this.consumerAdapter.createSubscription((Object) this.component, Reflection.getOrCreateKotlinClass(WindowLayoutInfo.class), "addWindowLayoutInfoListener", "removeWindowLayoutInfoListener", (Activity) context, (Function1) function1));
                    } else {
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                        multicastConsumer2.accept(new WindowLayoutInfo(emptyList));
                        return;
                    }
                } else {
                    Consumer<WindowLayoutInfo> consumer = new Consumer() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0
                        @Override // androidx.window.extensions.core.util.function.Consumer
                        public final void accept(Object obj) {
                            ExtensionWindowLayoutInfoBackend.registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(ExtensionWindowLayoutInfoBackend.MulticastConsumer.this, (WindowLayoutInfo) obj);
                        }
                    };
                    this.consumerToOemConsumer.put(multicastConsumer2, consumer);
                    this.component.addWindowLayoutInfoListener(context, consumer);
                }
            }
            Unit unit2 = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(MulticastConsumer consumer, WindowLayoutInfo info) {
        Intrinsics.checkNotNullParameter(consumer, "$consumer");
        Intrinsics.checkNotNullExpressionValue(info, "info");
        consumer.accept(info);
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        try {
            Context context = this.listenerToContext.get(callback);
            if (context == null) {
                return;
            }
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer == null) {
                return;
            }
            multicastConsumer.removeListener(callback);
            this.listenerToContext.remove(callback);
            if (multicastConsumer.isEmpty()) {
                this.contextToListeners.remove(context);
                if (ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 2) {
                    ConsumerAdapter.Subscription remove = this.consumerToToken.remove(multicastConsumer);
                    if (remove != null) {
                        remove.dispose();
                    }
                } else {
                    Consumer<WindowLayoutInfo> remove2 = this.consumerToOemConsumer.remove(multicastConsumer);
                    if (remove2 != null) {
                        this.component.removeWindowLayoutInfoListener(remove2);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean hasRegisteredListeners() {
        return (this.contextToListeners.isEmpty() && this.listenerToContext.isEmpty() && this.consumerToToken.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ExtensionWindowLayoutInfoBackend.kt */
    @SourceDebugExtension({"SMAP\nExtensionWindowLayoutInfoBackend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExtensionWindowLayoutInfoBackend.kt\nandroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,203:1\n1855#2,2:204\n1#3:206\n*S KotlinDebug\n*F\n+ 1 ExtensionWindowLayoutInfoBackend.kt\nandroidx/window/layout/adapter/extensions/ExtensionWindowLayoutInfoBackend$MulticastConsumer\n*L\n182#1:204,2\n*E\n"})
    /* loaded from: classes.dex */
    public static final class MulticastConsumer implements androidx.core.util.Consumer<WindowLayoutInfo> {
        private final Context context;
        private androidx.window.layout.WindowLayoutInfo lastKnownValue;
        private final ReentrantLock multicastConsumerLock;
        private final Set<androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo>> registeredListeners;

        public MulticastConsumer(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.multicastConsumerLock = new ReentrantLock();
            this.registeredListeners = new LinkedHashSet();
        }

        @Override // androidx.core.util.Consumer
        public void accept(WindowLayoutInfo value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.lastKnownValue = ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(this.context, value);
                Iterator<T> it = this.registeredListeners.iterator();
                while (it.hasNext()) {
                    ((androidx.core.util.Consumer) it.next()).accept(this.lastKnownValue);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final void addListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                androidx.window.layout.WindowLayoutInfo windowLayoutInfo = this.lastKnownValue;
                if (windowLayoutInfo != null) {
                    listener.accept(windowLayoutInfo);
                }
                this.registeredListeners.add(listener);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final void removeListener(androidx.core.util.Consumer<androidx.window.layout.WindowLayoutInfo> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.registeredListeners.remove(listener);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final boolean isEmpty() {
            return this.registeredListeners.isEmpty();
        }
    }
}
