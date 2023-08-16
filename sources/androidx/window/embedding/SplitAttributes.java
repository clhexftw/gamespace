package androidx.window.embedding;

import android.annotation.SuppressLint;
import android.graphics.Color;
import androidx.window.core.SpecificationComputer;
import androidx.window.core.VerificationMode;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
/* compiled from: SplitAttributes.kt */
/* loaded from: classes.dex */
public final class SplitAttributes {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = SplitAttributes.class.getSimpleName();
    private final BackgroundColor animationBackgroundColor;
    private final LayoutDirection layoutDirection;
    private final SplitType splitType;

    public SplitAttributes() {
        this(null, null, null, 7, null);
    }

    public SplitAttributes(SplitType splitType, LayoutDirection layoutDirection, BackgroundColor animationBackgroundColor) {
        Intrinsics.checkNotNullParameter(splitType, "splitType");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(animationBackgroundColor, "animationBackgroundColor");
        this.splitType = splitType;
        this.layoutDirection = layoutDirection;
        this.animationBackgroundColor = animationBackgroundColor;
    }

    public /* synthetic */ SplitAttributes(SplitType splitType, LayoutDirection layoutDirection, BackgroundColor backgroundColor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SplitType.SPLIT_TYPE_EQUAL : splitType, (i & 2) != 0 ? LayoutDirection.LOCALE : layoutDirection, (i & 4) != 0 ? BackgroundColor.DEFAULT : backgroundColor);
    }

    public final SplitType getSplitType() {
        return this.splitType;
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final BackgroundColor getAnimationBackgroundColor() {
        return this.animationBackgroundColor;
    }

    /* compiled from: SplitAttributes.kt */
    /* loaded from: classes.dex */
    public static final class SplitType {
        public static final Companion Companion;
        public static final SplitType SPLIT_TYPE_EQUAL;
        public static final SplitType SPLIT_TYPE_EXPAND;
        public static final SplitType SPLIT_TYPE_HINGE;
        private final String description;
        private final float value;

        public static final SplitType ratio(float f) {
            return Companion.ratio(f);
        }

        public SplitType(String description, float f) {
            Intrinsics.checkNotNullParameter(description, "description");
            this.description = description;
            this.value = f;
        }

        public final String getDescription$window_release() {
            return this.description;
        }

        public final float getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SplitType) {
                SplitType splitType = (SplitType) obj;
                return ((this.value > splitType.value ? 1 : (this.value == splitType.value ? 0 : -1)) == 0) && Intrinsics.areEqual(this.description, splitType.description);
            }
            return false;
        }

        public int hashCode() {
            return this.description.hashCode() + (Float.hashCode(this.value) * 31);
        }

        /* compiled from: SplitAttributes.kt */
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SplitType ratio(final float f) {
                SpecificationComputer.Companion companion = SpecificationComputer.Companion;
                Float valueOf = Float.valueOf(f);
                String TAG = SplitAttributes.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                Object compute = SpecificationComputer.Companion.startSpecification$default(companion, valueOf, TAG, VerificationMode.STRICT, null, 4, null).require("Ratio must be in range (0.0, 1.0). Use SplitType.expandContainers() instead of 0 or 1.", new Function1<Float, Boolean>() { // from class: androidx.window.embedding.SplitAttributes$SplitType$Companion$ratio$checkedRatio$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f2) {
                        return invoke(f2.floatValue());
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:10:0x0032, code lost:
                        if (r6 == false) goto L9;
                     */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Boolean invoke(float r7) {
                        /*
                            r6 = this;
                            float r7 = r1
                            double r0 = (double) r7
                            r2 = 0
                            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                            r2 = 1
                            r3 = 0
                            if (r7 > 0) goto L13
                            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                            if (r7 > 0) goto L13
                            r7 = r2
                            goto L14
                        L13:
                            r7 = r3
                        L14:
                            if (r7 == 0) goto L35
                            r7 = 2
                            java.lang.Float[] r7 = new java.lang.Float[r7]
                            r0 = 0
                            java.lang.Float r0 = java.lang.Float.valueOf(r0)
                            r7[r3] = r0
                            r0 = 1065353216(0x3f800000, float:1.0)
                            java.lang.Float r0 = java.lang.Float.valueOf(r0)
                            r7[r2] = r0
                            float r6 = r1
                            java.lang.Float r6 = java.lang.Float.valueOf(r6)
                            boolean r6 = kotlin.collections.ArraysKt.contains(r7, r6)
                            if (r6 != 0) goto L35
                            goto L36
                        L35:
                            r2 = r3
                        L36:
                            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
                            return r6
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitAttributes$SplitType$Companion$ratio$checkedRatio$1.invoke(float):java.lang.Boolean");
                    }
                }).compute();
                Intrinsics.checkNotNull(compute);
                float floatValue = ((Number) compute).floatValue();
                return new SplitType("ratio:" + floatValue, floatValue);
            }

            @SuppressLint({"Range"})
            public final SplitType buildSplitTypeFromValue$window_release(float f) {
                SplitType splitType = SplitType.SPLIT_TYPE_EXPAND;
                return (f > splitType.getValue$window_release() ? 1 : (f == splitType.getValue$window_release() ? 0 : -1)) == 0 ? splitType : ratio(f);
            }
        }

        static {
            Companion companion = new Companion(null);
            Companion = companion;
            SPLIT_TYPE_EXPAND = new SplitType("expandContainers", 0.0f);
            SPLIT_TYPE_EQUAL = companion.ratio(0.5f);
            SPLIT_TYPE_HINGE = new SplitType("hinge", -1.0f);
        }
    }

    /* compiled from: SplitAttributes.kt */
    /* loaded from: classes.dex */
    public static final class LayoutDirection {
        private final String description;
        private final int value;
        public static final Companion Companion = new Companion(null);
        public static final LayoutDirection LOCALE = new LayoutDirection("LOCALE", 0);
        public static final LayoutDirection LEFT_TO_RIGHT = new LayoutDirection("LEFT_TO_RIGHT", 1);
        public static final LayoutDirection RIGHT_TO_LEFT = new LayoutDirection("RIGHT_TO_LEFT", 2);
        public static final LayoutDirection TOP_TO_BOTTOM = new LayoutDirection("TOP_TO_BOTTOM", 3);
        public static final LayoutDirection BOTTOM_TO_TOP = new LayoutDirection("BOTTOM_TO_TOP", 4);

        private LayoutDirection(String str, int i) {
            this.description = str;
            this.value = i;
        }

        public final int getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return this.description;
        }

        /* compiled from: SplitAttributes.kt */
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final LayoutDirection getLayoutDirectionFromValue$window_release(int i) {
                LayoutDirection layoutDirection = LayoutDirection.LEFT_TO_RIGHT;
                if (i != layoutDirection.getValue$window_release()) {
                    layoutDirection = LayoutDirection.RIGHT_TO_LEFT;
                    if (i != layoutDirection.getValue$window_release()) {
                        layoutDirection = LayoutDirection.LOCALE;
                        if (i != layoutDirection.getValue$window_release()) {
                            layoutDirection = LayoutDirection.TOP_TO_BOTTOM;
                            if (i != layoutDirection.getValue$window_release()) {
                                layoutDirection = LayoutDirection.BOTTOM_TO_TOP;
                                if (i != layoutDirection.getValue$window_release()) {
                                    throw new IllegalArgumentException("Undefined value:" + i);
                                }
                            }
                        }
                    }
                }
                return layoutDirection;
            }
        }
    }

    /* compiled from: SplitAttributes.kt */
    /* loaded from: classes.dex */
    public static final class BackgroundColor {
        public static final Companion Companion = new Companion(null);
        public static final BackgroundColor DEFAULT = new BackgroundColor("DEFAULT", 0);
        private final String description;
        private final int value;

        public /* synthetic */ BackgroundColor(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        public static final BackgroundColor color(int i) {
            return Companion.color(i);
        }

        private BackgroundColor(String str, int i) {
            this.description = str;
            this.value = i;
        }

        public final int getValue$window_release() {
            return this.value;
        }

        public String toString() {
            return "BackgroundColor(" + this.description + ')';
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof BackgroundColor) {
                BackgroundColor backgroundColor = (BackgroundColor) obj;
                return this.value == backgroundColor.value && Intrinsics.areEqual(this.description, backgroundColor.description);
            }
            return false;
        }

        public int hashCode() {
            return this.description.hashCode() + (Integer.hashCode(this.value) * 31);
        }

        /* compiled from: SplitAttributes.kt */
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final BackgroundColor color(int i) {
                if (!(-16777216 <= i && i <= -1)) {
                    throw new IllegalArgumentException("Background color must be opaque".toString());
                }
                return new BackgroundColor("color:" + Integer.toHexString(i), i, null);
            }

            public final BackgroundColor buildFromValue$window_release(int i) {
                if (Color.alpha(i) != 255) {
                    return BackgroundColor.DEFAULT;
                }
                return color(i);
            }
        }
    }

    /* compiled from: SplitAttributes.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public int hashCode() {
        return (((this.splitType.hashCode() * 31) + this.layoutDirection.hashCode()) * 31) + this.animationBackgroundColor.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SplitAttributes) {
            SplitAttributes splitAttributes = (SplitAttributes) obj;
            return Intrinsics.areEqual(this.splitType, splitAttributes.splitType) && Intrinsics.areEqual(this.layoutDirection, splitAttributes.layoutDirection) && Intrinsics.areEqual(this.animationBackgroundColor, splitAttributes.animationBackgroundColor);
        }
        return false;
    }

    public String toString() {
        return SplitAttributes.class.getSimpleName() + ":{splitType=" + this.splitType + ", layoutDir=" + this.layoutDirection + ", animationBackgroundColor=" + this.animationBackgroundColor;
    }

    /* compiled from: SplitAttributes.kt */
    @SourceDebugExtension({"SMAP\nSplitAttributes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SplitAttributes.kt\nandroidx/window/embedding/SplitAttributes$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,559:1\n1#2:560\n*E\n"})
    /* loaded from: classes.dex */
    public static final class Builder {
        private SplitType splitType = SplitType.SPLIT_TYPE_EQUAL;
        private LayoutDirection layoutDirection = LayoutDirection.LOCALE;
        private BackgroundColor animationBackgroundColor = BackgroundColor.DEFAULT;

        public final Builder setSplitType(SplitType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.splitType = type;
            return this;
        }

        public final Builder setLayoutDirection(LayoutDirection layoutDirection) {
            Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
            this.layoutDirection = layoutDirection;
            return this;
        }

        public final Builder setAnimationBackgroundColor(BackgroundColor color) {
            Intrinsics.checkNotNullParameter(color, "color");
            this.animationBackgroundColor = color;
            return this;
        }

        public final SplitAttributes build() {
            return new SplitAttributes(this.splitType, this.layoutDirection, this.animationBackgroundColor);
        }
    }
}
