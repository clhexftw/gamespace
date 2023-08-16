package org.nameless.gamespace.gamebar;

import android.app.GameManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionService.kt */
@DebugMetadata(c = "org.nameless.gamespace.gamebar.SessionService$applyGameModeConfig$2", f = "SessionService.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SessionService$applyGameModeConfig$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $app;
    final /* synthetic */ int $preferred;
    int label;
    final /* synthetic */ SessionService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionService$applyGameModeConfig$2(SessionService sessionService, String str, int i, Continuation<? super SessionService$applyGameModeConfig$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionService;
        this.$app = str;
        this.$preferred = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionService$applyGameModeConfig$2(this.this$0, this.$app, this.$preferred, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionService$applyGameModeConfig$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        GameManager gameManager;
        boolean contains;
        GameManager gameManager2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            gameManager = this.this$0.gameManager;
            GameManager gameManager3 = null;
            if (gameManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gameManager");
                gameManager = null;
            }
            int[] it = gameManager.getAvailableGameModes(this.$app);
            int i = this.$preferred;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            contains = ArraysKt___ArraysKt.contains(it, i);
            if (!contains) {
                it = null;
            }
            if (it != null) {
                SessionService sessionService = this.this$0;
                String str = this.$app;
                int i2 = this.$preferred;
                gameManager2 = sessionService.gameManager;
                if (gameManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gameManager");
                } else {
                    gameManager3 = gameManager2;
                }
                gameManager3.setGameMode(str, i2);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
