package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class Oscillator {
    double[] mArea;
    MonotonicCurveFit mCustomCurve;
    String mCustomType;
    int mType;
    float[] mPeriod = new float[0];
    double[] mPosition = new double[0];
    double mPI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }

    public void setType(int i, String str) {
        this.mType = i;
        this.mCustomType = str;
        if (str != null) {
            this.mCustomCurve = MonotonicCurveFit.buildWave(str);
        }
    }

    public void addPoint(double d, float f) {
        int length = this.mPeriod.length + 1;
        int binarySearch = Arrays.binarySearch(this.mPosition, d);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.mPosition[binarySearch] = d;
        this.mPeriod[binarySearch] = f;
        this.mNormalized = false;
    }

    public void normalize() {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        int i;
        int i2 = 0;
        double d = 0.0d;
        while (true) {
            if (i2 >= this.mPeriod.length) {
                break;
            }
            d += fArr[i2];
            i2++;
        }
        double d2 = 0.0d;
        int i3 = 1;
        while (true) {
            if (i3 >= this.mPeriod.length) {
                break;
            }
            double[] dArr = this.mPosition;
            d2 += (dArr[i3] - dArr[i3 - 1]) * ((fArr2[i] + fArr2[i3]) / 2.0f);
            i3++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr4 = this.mPeriod;
            if (i4 >= fArr4.length) {
                break;
            }
            fArr4[i4] = fArr4[i4] * ((float) (d / d2));
            i4++;
        }
        this.mArea[0] = 0.0d;
        int i5 = 1;
        while (true) {
            if (i5 < this.mPeriod.length) {
                int i6 = i5 - 1;
                double[] dArr2 = this.mPosition;
                double d3 = dArr2[i5] - dArr2[i6];
                double[] dArr3 = this.mArea;
                dArr3[i5] = dArr3[i6] + (d3 * ((fArr3[i6] + fArr3[i5]) / 2.0f));
                i5++;
            } else {
                this.mNormalized = true;
                return;
            }
        }
    }

    double getP(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch != 0) {
            int i = (-binarySearch) - 1;
            float[] fArr = this.mPeriod;
            float f = fArr[i];
            int i2 = i - 1;
            float f2 = fArr[i2];
            double d2 = f - f2;
            double[] dArr = this.mPosition;
            double d3 = dArr[i];
            double d4 = dArr[i2];
            double d5 = d2 / (d3 - d4);
            return this.mArea[i2] + ((f2 - (d5 * d4)) * (d - d4)) + ((d5 * ((d * d) - (d4 * d4))) / 2.0d);
        }
        return 0.0d;
    }

    public double getValue(double d, double d2) {
        double p = getP(d) + d2;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (p % 1.0d));
            case 2:
                return 1.0d - Math.abs((((p * 4.0d) + 1.0d) % 4.0d) - 2.0d);
            case 3:
                return (((p * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                return 1.0d - (((p * 2.0d) + 1.0d) % 2.0d);
            case 5:
                return Math.cos(this.mPI2 * (d2 + p));
            case 6:
                double abs = 1.0d - Math.abs(((p * 4.0d) % 4.0d) - 2.0d);
                return 1.0d - (abs * abs);
            case 7:
                return this.mCustomCurve.getPos(p % 1.0d, 0);
            default:
                return Math.sin(this.mPI2 * p);
        }
    }

    double getDP(double d) {
        if (d <= 0.0d) {
            d = 1.0E-5d;
        } else if (d >= 1.0d) {
            d = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(this.mPosition, d);
        if (binarySearch <= 0 && binarySearch != 0) {
            int i = (-binarySearch) - 1;
            float[] fArr = this.mPeriod;
            float f = fArr[i];
            int i2 = i - 1;
            float f2 = fArr[i2];
            double d2 = f - f2;
            double[] dArr = this.mPosition;
            double d3 = dArr[i];
            double d4 = dArr[i2];
            double d5 = d2 / (d3 - d4);
            return (f2 - (d5 * d4)) + (d * d5);
        }
        return 0.0d;
    }

    public double getSlope(double d, double d2, double d3) {
        double d4;
        double signum;
        double p = d2 + getP(d);
        double dp = getDP(d) + d3;
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                d4 = dp * 4.0d;
                signum = Math.signum((((p * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return dp * 2.0d;
            case 4:
                return (-dp) * 2.0d;
            case 5:
                double d5 = this.mPI2;
                return (-d5) * dp * Math.sin(d5 * p);
            case 6:
                d4 = dp * 4.0d;
                signum = (((p * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                break;
            case 7:
                return this.mCustomCurve.getSlope(p % 1.0d, 0);
            default:
                double d6 = this.mPI2;
                d4 = dp * d6;
                signum = Math.cos(d6 * p);
                break;
        }
        return d4 * signum;
    }
}
