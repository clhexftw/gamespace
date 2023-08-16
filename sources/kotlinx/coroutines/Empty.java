package kotlinx.coroutines;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JobSupport.kt */
/* loaded from: classes2.dex */
public final class Empty implements Incomplete {
    private final boolean isActive;

    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    public Empty(boolean z) {
        this.isActive = z;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return this.isActive;
    }

    public String toString() {
        String str = isActive() ? "Active" : "New";
        return "Empty{" + str + "}";
    }
}
