package androidx.window.embedding;

import android.app.Activity;
import androidx.core.util.Consumer;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
/* compiled from: SplitController.kt */
@DebugMetadata(c = "androidx.window.embedding.SplitController$addSplitListener$1$1", f = "SplitController.kt", l = {80}, m = "invokeSuspend")
/* loaded from: classes.dex */
final class SplitController$addSplitListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Consumer<List<SplitInfo>> $consumer;
    int label;
    final /* synthetic */ SplitController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitController$addSplitListener$1$1(SplitController splitController, Activity activity, Consumer<List<SplitInfo>> consumer, Continuation<? super SplitController$addSplitListener$1$1> continuation) {
        super(2, continuation);
        this.this$0 = splitController;
        this.$activity = activity;
        this.$consumer = consumer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SplitController$addSplitListener$1$1(this.this$0, this.$activity, this.$consumer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SplitController$addSplitListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<List<SplitInfo>> splitInfoList = this.this$0.splitInfoList(this.$activity);
            final Consumer<List<SplitInfo>> consumer = this.$consumer;
            FlowCollector<? super List<SplitInfo>> flowCollector = new FlowCollector() { // from class: androidx.window.embedding.SplitController$addSplitListener$1$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((List) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(List<SplitInfo> list, Continuation<? super Unit> continuation) {
                    consumer.accept(list);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (splitInfoList.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
