package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R$attr;
import androidx.core.R$styleable;
import androidx.core.math.MathUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class ColorStateListInflaterCompat {
    private static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    public static ColorStateList inflate(Resources resources, int i, Resources.Theme theme) {
        try {
            return createFromXml(resources, resources.getXml(i), theme);
        } catch (Exception e) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", e);
            return null;
        }
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return createFromXmlInner(resources, xmlPullParser, asAttributeSet, theme);
    }

    public static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        return inflate(resources, xmlPullParser, attributeSet, theme);
    }

    private static ColorStateList inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int color;
        float f;
        Resources resources2 = resources;
        int i = 1;
        int depth2 = xmlPullParser.getDepth() + 1;
        int[][] iArr = new int[20];
        int[] iArr2 = new int[20];
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == i || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals("item")) {
                TypedArray obtainAttributes = obtainAttributes(resources2, theme, attributeSet, R$styleable.ColorStateListItem);
                int i3 = R$styleable.ColorStateListItem_android_color;
                int resourceId = obtainAttributes.getResourceId(i3, -1);
                if (resourceId != -1 && !isColorInt(resources2, resourceId)) {
                    try {
                        color = createFromXml(resources2, resources2.getXml(resourceId), theme).getDefaultColor();
                    } catch (Exception unused) {
                        color = obtainAttributes.getColor(R$styleable.ColorStateListItem_android_color, -65281);
                    }
                } else {
                    color = obtainAttributes.getColor(i3, -65281);
                }
                float f2 = 1.0f;
                int i4 = R$styleable.ColorStateListItem_android_alpha;
                if (obtainAttributes.hasValue(i4)) {
                    f2 = obtainAttributes.getFloat(i4, 1.0f);
                } else {
                    int i5 = R$styleable.ColorStateListItem_alpha;
                    if (obtainAttributes.hasValue(i5)) {
                        f2 = obtainAttributes.getFloat(i5, 1.0f);
                    }
                }
                int i6 = R$styleable.ColorStateListItem_android_lStar;
                if (obtainAttributes.hasValue(i6)) {
                    f = obtainAttributes.getFloat(i6, -1.0f);
                } else {
                    f = obtainAttributes.getFloat(R$styleable.ColorStateListItem_lStar, -1.0f);
                }
                obtainAttributes.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr3 = new int[attributeCount];
                int i7 = 0;
                for (int i8 = 0; i8 < attributeCount; i8++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i8);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != R$attr.alpha && attributeNameResource != R$attr.lStar) {
                        int i9 = i7 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i8, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr3[i7] = attributeNameResource;
                        i7 = i9;
                    }
                }
                int[] trimStateSet = StateSet.trimStateSet(iArr3, i7);
                iArr2 = GrowingArrayUtils.append(iArr2, i2, modulateColorAlpha(color, f2, f));
                iArr = (int[][]) GrowingArrayUtils.append(iArr, i2, trimStateSet);
                i2++;
            }
            i = 1;
            resources2 = resources;
        }
        int[] iArr4 = new int[i2];
        int[][] iArr5 = new int[i2];
        System.arraycopy(iArr2, 0, iArr4, 0, i2);
        System.arraycopy(iArr, 0, iArr5, 0, i2);
        return new ColorStateList(iArr5, iArr4);
    }

    private static boolean isColorInt(Resources resources, int i) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.type;
        return i2 >= 28 && i2 <= 31;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = sTempTypedValue;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            TypedValue typedValue2 = new TypedValue();
            threadLocal.set(typedValue2);
            return typedValue2;
        }
        return typedValue;
    }

    private static TypedArray obtainAttributes(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    private static int modulateColorAlpha(int i, float f, float f2) {
        boolean z = f2 >= 0.0f && f2 <= 100.0f;
        if (f != 1.0f || z) {
            int clamp = MathUtils.clamp((int) ((Color.alpha(i) * f) + 0.5f), 0, 255);
            if (z) {
                CamColor fromColor = CamColor.fromColor(i);
                i = CamColor.toColor(fromColor.getHue(), fromColor.getChroma(), f2);
            }
            return (i & 16777215) | (clamp << 24);
        }
        return i;
    }
}
