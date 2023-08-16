package org.nameless.gamespace.widget;

import android.widget.TextView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MenuSwitcher.kt */
@DebugMetadata(c = "org.nameless.gamespace.widget.MenuSwitcher$onFrameUpdated$1", f = "MenuSwitcher.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MenuSwitcher$onFrameUpdated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ float $newValue;
    int label;
    final /* synthetic */ MenuSwitcher this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenuSwitcher$onFrameUpdated$1(MenuSwitcher menuSwitcher, float f, Continuation<? super MenuSwitcher$onFrameUpdated$1> continuation) {
        super(2, continuation);
        this.this$0 = menuSwitcher;
        this.$newValue = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MenuSwitcher$onFrameUpdated$1(this.this$0, this.$newValue, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MenuSwitcher$onFrameUpdated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TextView content;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DecimalFormat decimalFormat = new DecimalFormat("#");
        MenuSwitcher menuSwitcher = this.this$0;
        float f = this.$newValue;
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        content = menuSwitcher.getContent();
        if (content != null) {
            content.setText(decimalFormat.format(Boxing.boxFloat(f)));
        }
        return Unit.INSTANCE;
    }
}
