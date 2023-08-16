package androidx.core.widget;

import android.widget.ListView;
/* loaded from: classes.dex */
public final class ListViewCompat {
    public static void scrollListBy(ListView listView, int i) {
        Api19Impl.scrollListBy(listView, i);
    }

    /* loaded from: classes.dex */
    static class Api19Impl {
        static void scrollListBy(ListView listView, int i) {
            listView.scrollListBy(i);
        }
    }
}