package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Strings.kt */
/* loaded from: classes2.dex */
public final class DelimitedRangesSequence implements Sequence<IntRange> {
    private final Function2<CharSequence, Integer, Pair<Integer, Integer>> getNextMatch;
    private final CharSequence input;
    private final int limit;
    private final int startIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public DelimitedRangesSequence(CharSequence input, int i, int i2, Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> getNextMatch) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(getNextMatch, "getNextMatch");
        this.input = input;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = getNextMatch;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<IntRange> iterator() {
        return new Iterator<IntRange>() { // from class: kotlin.text.DelimitedRangesSequence$iterator$1
            private int counter;
            private int currentStartIndex;
            private IntRange nextItem;
            private int nextSearchIndex;
            private int nextState = -1;

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i;
                CharSequence charSequence;
                int coerceIn;
                i = DelimitedRangesSequence.this.startIndex;
                charSequence = DelimitedRangesSequence.this.input;
                coerceIn = RangesKt___RangesKt.coerceIn(i, 0, charSequence.length());
                this.currentStartIndex = coerceIn;
                this.nextSearchIndex = coerceIn;
            }

            /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
                if (r0 < r4) goto L13;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private final void calcNext() {
                /*
                    r6 = this;
                    int r0 = r6.nextSearchIndex
                    r1 = 0
                    if (r0 >= 0) goto Lc
                    r6.nextState = r1
                    r0 = 0
                    r6.nextItem = r0
                    goto L9e
                Lc:
                    kotlin.text.DelimitedRangesSequence r0 = kotlin.text.DelimitedRangesSequence.this
                    int r0 = kotlin.text.DelimitedRangesSequence.access$getLimit$p(r0)
                    r2 = -1
                    r3 = 1
                    if (r0 <= 0) goto L23
                    int r0 = r6.counter
                    int r0 = r0 + r3
                    r6.counter = r0
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    int r4 = kotlin.text.DelimitedRangesSequence.access$getLimit$p(r4)
                    if (r0 >= r4) goto L31
                L23:
                    int r0 = r6.nextSearchIndex
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = r4.length()
                    if (r0 <= r4) goto L47
                L31:
                    kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
                    int r1 = r6.currentStartIndex
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4)
                    r0.<init>(r1, r4)
                    r6.nextItem = r0
                    r6.nextSearchIndex = r2
                    goto L9c
                L47:
                    kotlin.text.DelimitedRangesSequence r0 = kotlin.text.DelimitedRangesSequence.this
                    kotlin.jvm.functions.Function2 r0 = kotlin.text.DelimitedRangesSequence.access$getGetNextMatch$p(r0)
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r5 = r6.nextSearchIndex
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                    java.lang.Object r0 = r0.invoke(r4, r5)
                    kotlin.Pair r0 = (kotlin.Pair) r0
                    if (r0 != 0) goto L77
                    kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
                    int r1 = r6.currentStartIndex
                    kotlin.text.DelimitedRangesSequence r4 = kotlin.text.DelimitedRangesSequence.this
                    java.lang.CharSequence r4 = kotlin.text.DelimitedRangesSequence.access$getInput$p(r4)
                    int r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r4)
                    r0.<init>(r1, r4)
                    r6.nextItem = r0
                    r6.nextSearchIndex = r2
                    goto L9c
                L77:
                    java.lang.Object r2 = r0.component1()
                    java.lang.Number r2 = (java.lang.Number) r2
                    int r2 = r2.intValue()
                    java.lang.Object r0 = r0.component2()
                    java.lang.Number r0 = (java.lang.Number) r0
                    int r0 = r0.intValue()
                    int r4 = r6.currentStartIndex
                    kotlin.ranges.IntRange r4 = kotlin.ranges.RangesKt.until(r4, r2)
                    r6.nextItem = r4
                    int r2 = r2 + r0
                    r6.currentStartIndex = r2
                    if (r0 != 0) goto L99
                    r1 = r3
                L99:
                    int r2 = r2 + r1
                    r6.nextSearchIndex = r2
                L9c:
                    r6.nextState = r3
                L9e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext():void");
            }

            @Override // java.util.Iterator
            public IntRange next() {
                if (this.nextState == -1) {
                    calcNext();
                }
                if (this.nextState == 0) {
                    throw new NoSuchElementException();
                }
                IntRange intRange = this.nextItem;
                Intrinsics.checkNotNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
                this.nextItem = null;
                this.nextState = -1;
                return intRange;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextState == -1) {
                    calcNext();
                }
                return this.nextState == 1;
            }
        };
    }
}
