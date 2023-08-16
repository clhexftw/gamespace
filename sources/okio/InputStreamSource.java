package okio;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JvmOkio.kt */
/* loaded from: classes.dex */
final class InputStreamSource implements Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream input, Timeout timeout) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.input = input;
        this.timeout = timeout;
    }

    @Override // okio.Source
    public long read(Buffer sink, long j) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            return 0L;
        }
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        try {
            this.timeout.throwIfReached();
            Segment writableSegment$external__okio__android_common__okio_lib = sink.writableSegment$external__okio__android_common__okio_lib(1);
            int read = this.input.read(writableSegment$external__okio__android_common__okio_lib.data, writableSegment$external__okio__android_common__okio_lib.limit, (int) Math.min(j, 8192 - writableSegment$external__okio__android_common__okio_lib.limit));
            if (read == -1) {
                if (writableSegment$external__okio__android_common__okio_lib.pos == writableSegment$external__okio__android_common__okio_lib.limit) {
                    sink.head = writableSegment$external__okio__android_common__okio_lib.pop();
                    SegmentPool.recycle(writableSegment$external__okio__android_common__okio_lib);
                    return -1L;
                }
                return -1L;
            }
            writableSegment$external__okio__android_common__okio_lib.limit += read;
            long j2 = read;
            sink.setSize$external__okio__android_common__okio_lib(sink.size() + j2);
            return j2;
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() {
        this.input.close();
    }

    public String toString() {
        InputStream inputStream = this.input;
        return "source(" + inputStream + ")";
    }
}
