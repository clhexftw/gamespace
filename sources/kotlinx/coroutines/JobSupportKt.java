package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
/* compiled from: JobSupport.kt */
/* loaded from: classes2.dex */
public final class JobSupportKt {
    private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
    private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");
    private static final Symbol SEALED = new Symbol("SEALED");
    private static final Empty EMPTY_NEW = new Empty(false);
    private static final Empty EMPTY_ACTIVE = new Empty(true);

    public static final /* synthetic */ Symbol access$getCOMPLETING_ALREADY$p() {
        return COMPLETING_ALREADY;
    }

    public static final /* synthetic */ Symbol access$getCOMPLETING_RETRY$p() {
        return COMPLETING_RETRY;
    }

    public static final /* synthetic */ Empty access$getEMPTY_ACTIVE$p() {
        return EMPTY_ACTIVE;
    }

    public static final /* synthetic */ Empty access$getEMPTY_NEW$p() {
        return EMPTY_NEW;
    }

    public static final /* synthetic */ Symbol access$getSEALED$p() {
        return SEALED;
    }

    public static final /* synthetic */ Symbol access$getTOO_LATE_TO_CANCEL$p() {
        return TOO_LATE_TO_CANCEL;
    }

    public static final Object boxIncomplete(Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    public static final Object unboxState(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }
}
