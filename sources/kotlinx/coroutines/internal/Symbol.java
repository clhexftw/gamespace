package kotlinx.coroutines.internal;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Symbol.kt */
/* loaded from: classes2.dex */
public final class Symbol {
    public final String symbol;

    public Symbol(String symbol) {
        Intrinsics.checkNotNullParameter(symbol, "symbol");
        this.symbol = symbol;
    }

    public String toString() {
        String str = this.symbol;
        return "<" + str + ">";
    }
}
