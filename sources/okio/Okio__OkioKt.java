package okio;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Okio.kt */
/* loaded from: classes.dex */
final /* synthetic */ class Okio__OkioKt {
    public static final BufferedSource buffer(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new RealBufferedSource(source);
    }
}
