package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
/* loaded from: classes.dex */
class MediaRouterActiveScanThrottlingHelper {
    private boolean mActiveScan;
    private long mCurrentTime;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mSuppressActiveScanTimeout;
    private final Runnable mUpdateDiscoveryRequestRunnable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaRouterActiveScanThrottlingHelper(Runnable runnable) {
        this.mUpdateDiscoveryRequestRunnable = runnable;
    }

    public void reset() {
        this.mSuppressActiveScanTimeout = 0L;
        this.mActiveScan = false;
        this.mCurrentTime = SystemClock.elapsedRealtime();
        this.mHandler.removeCallbacks(this.mUpdateDiscoveryRequestRunnable);
    }

    public void requestActiveScan(boolean z, long j) {
        if (z) {
            long j2 = this.mCurrentTime;
            if (j2 - j >= 30000) {
                return;
            }
            this.mSuppressActiveScanTimeout = Math.max(this.mSuppressActiveScanTimeout, (j + 30000) - j2);
            this.mActiveScan = true;
        }
    }

    public boolean finalizeActiveScanAndScheduleSuppressActiveScanRunnable() {
        if (this.mActiveScan) {
            long j = this.mSuppressActiveScanTimeout;
            if (j > 0) {
                this.mHandler.postDelayed(this.mUpdateDiscoveryRequestRunnable, j);
            }
        }
        return this.mActiveScan;
    }
}
