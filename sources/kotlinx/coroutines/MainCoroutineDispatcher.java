package kotlinx.coroutines;
/* compiled from: MainCoroutineDispatcher.kt */
/* loaded from: classes2.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher getImmediate();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl == null) {
            String classSimpleName = DebugStringsKt.getClassSimpleName(this);
            String hexAddress = DebugStringsKt.getHexAddress(this);
            return classSimpleName + "@" + hexAddress;
        }
        return stringInternalImpl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String toStringInternalImpl() {
        MainCoroutineDispatcher mainCoroutineDispatcher;
        MainCoroutineDispatcher main = Dispatchers.getMain();
        if (this == main) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher = main.getImmediate();
        } catch (UnsupportedOperationException unused) {
            mainCoroutineDispatcher = null;
        }
        if (this == mainCoroutineDispatcher) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }
}
