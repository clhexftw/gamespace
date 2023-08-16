package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
/* compiled from: ViewGroup.kt */
/* loaded from: classes.dex */
public final class ViewGroupKt {
    public static final Iterator<View> iterator(final ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return new Iterator<View>() { // from class: androidx.core.view.ViewGroupKt$iterator$1
            private int index;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < viewGroup.getChildCount();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public View next() {
                ViewGroup viewGroup2 = viewGroup;
                int i = this.index;
                this.index = i + 1;
                View childAt = viewGroup2.getChildAt(i);
                if (childAt != null) {
                    return childAt;
                }
                throw new IndexOutOfBoundsException();
            }

            @Override // java.util.Iterator
            public void remove() {
                ViewGroup viewGroup2 = viewGroup;
                int i = this.index - 1;
                this.index = i;
                viewGroup2.removeViewAt(i);
            }
        };
    }

    public static final Sequence<View> getChildren(final ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return new Sequence<View>() { // from class: androidx.core.view.ViewGroupKt$children$1
            @Override // kotlin.sequences.Sequence
            public Iterator<View> iterator() {
                return ViewGroupKt.iterator(viewGroup);
            }
        };
    }
}
