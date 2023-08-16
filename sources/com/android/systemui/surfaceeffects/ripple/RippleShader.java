package com.android.systemui.surfaceeffects.ripple;

import android.graphics.RuntimeShader;
import android.util.Log;
import android.util.MathUtils;
import com.android.systemui.animation.Interpolators;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: RippleShader.kt */
/* loaded from: classes2.dex */
public final class RippleShader extends RuntimeShader {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(RippleShader.class).getSimpleName();
    private final FadeParams baseRingFadeParams;
    private float blurEnd;
    private float blurStart;
    private final FadeParams centerFillFadeParams;
    private int color;
    private float pixelDensity;
    private float progress;
    private float rawProgress;
    private final RippleSize rippleSize;
    private final FadeParams sparkleRingFadeParams;
    private float sparkleStrength;

    /* compiled from: RippleShader.kt */
    /* loaded from: classes2.dex */
    public enum RippleShape {
        CIRCLE,
        ROUNDED_BOX,
        ELLIPSE
    }

    public RippleShader() {
        this(null, 1, null);
    }

    public /* synthetic */ RippleShader(RippleShape rippleShape, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RippleShape.CIRCLE : rippleShape);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RippleShader(RippleShape rippleShape) {
        super(Companion.buildShader(rippleShape));
        Intrinsics.checkNotNullParameter(rippleShape, "rippleShape");
        this.blurStart = 1.25f;
        this.blurEnd = 0.5f;
        this.rippleSize = new RippleSize();
        this.color = 16777215;
        this.pixelDensity = 1.0f;
        this.sparkleRingFadeParams = new FadeParams(0.0f, 0.1f, 0.4f, 1.0f);
        this.baseRingFadeParams = new FadeParams(0.0f, 0.1f, 0.3f, 1.0f);
        this.centerFillFadeParams = new FadeParams(0.0f, 0.0f, 0.0f, 0.6f);
    }

    /* compiled from: RippleShader.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {

        /* compiled from: RippleShader.kt */
        /* loaded from: classes2.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[RippleShape.values().length];
                try {
                    iArr[RippleShape.CIRCLE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[RippleShape.ROUNDED_BOX.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[RippleShape.ELLIPSE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String buildShader(RippleShape rippleShape) {
            int i = WhenMappings.$EnumSwitchMapping$0[rippleShape.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return "\n            uniform vec2 in_center;\n            uniform vec2 in_size;\n            uniform float in_cornerRadius;\n            uniform float in_thickness;\n            uniform float in_time;\n            uniform float in_distort_radial;\n            uniform float in_distort_xy;\n            uniform float in_fadeSparkle;\n            uniform float in_fadeFill;\n            uniform float in_fadeRing;\n            uniform float in_blur;\n            uniform float in_pixelDensity;\n            layout(color) uniform vec4 in_color;\n            uniform float in_sparkle_strength;\n        \n            float triangleNoise(vec2 n) {\n                n  = fract(n * vec2(5.3987, 5.4421));\n                n += dot(n.yx, n.xy + vec2(21.5351, 14.3137));\n                float xy = n.x * n.y;\n                // compute in [0..2[ and remap to [-1.0..1.0[\n                return fract(xy * 95.4307) + fract(xy * 75.04961) - 1.0;\n            }\n\n            const float PI = 3.1415926535897932384626;\n\n            float sparkles(vec2 uv, float t) {\n                float n = triangleNoise(uv);\n                float s = 0.0;\n                for (float i = 0; i < 4; i += 1) {\n                    float l = i * 0.01;\n                    float h = l + 0.1;\n                    float o = smoothstep(n - l, h, n);\n                    o *= abs(sin(PI * o * (t + 0.55 * i)));\n                    s += o;\n                }\n                return s;\n            }\n\n            vec2 distort(vec2 p, float time, float distort_amount_radial,\n                float distort_amount_xy) {\n                    float angle = atan(p.y, p.x);\n                      return p + vec2(sin(angle * 8 + time * 0.003 + 1.641),\n                                cos(angle * 5 + 2.14 + time * 0.00412)) * distort_amount_radial\n                         + vec2(sin(p.x * 0.01 + time * 0.00215 + 0.8123),\n                                cos(p.y * 0.01 + time * 0.005931)) * distort_amount_xy;\n            }\n\n            // Return range [-1, 1].\n            vec3 hash(vec3 p) {\n                p = fract(p * vec3(.3456, .1234, .9876));\n                p += dot(p, p.yxz + 43.21);\n                p = (p.xxy + p.yxx) * p.zyx;\n                return (fract(sin(p) * 4567.1234567) - .5) * 2.;\n            }\n\n            // Skew factors (non-uniform).\n            const float SKEW = 0.3333333;  // 1/3\n            const float UNSKEW = 0.1666667;  // 1/6\n\n            // Return range roughly [-1,1].\n            // It's because the hash function (that returns a random gradient vector) returns\n            // different magnitude of vectors. Noise doesn't have to be in the precise range thus\n            // skipped normalize.\n            float simplex3d(vec3 p) {\n                // Skew the input coordinate, so that we get squashed cubical grid\n                vec3 s = floor(p + (p.x + p.y + p.z) * SKEW);\n\n                // Unskew back\n                vec3 u = s - (s.x + s.y + s.z) * UNSKEW;\n\n                // Unskewed coordinate that is relative to p, to compute the noise contribution\n                // based on the distance.\n                vec3 c0 = p - u;\n\n                // We have six simplices (in this case tetrahedron, since we are in 3D) that we\n                // could possibly in.\n                // Here, we are finding the correct tetrahedron (simplex shape), and traverse its\n                // four vertices (c0..3) when computing noise contribution.\n                // The way we find them is by comparing c0's x,y,z values.\n                // For example in 2D, we can find the triangle (simplex shape in 2D) that we are in\n                // by comparing x and y values. i.e. x>y lower, x<y, upper triangle.\n                // Same applies in 3D.\n                //\n                // Below indicates the offsets (or offset directions) when c0=(x0,y0,z0)\n                // x0>y0>z0: (1,0,0), (1,1,0), (1,1,1)\n                // x0>z0>y0: (1,0,0), (1,0,1), (1,1,1)\n                // z0>x0>y0: (0,0,1), (1,0,1), (1,1,1)\n                // z0>y0>x0: (0,0,1), (0,1,1), (1,1,1)\n                // y0>z0>x0: (0,1,0), (0,1,1), (1,1,1)\n                // y0>x0>z0: (0,1,0), (1,1,0), (1,1,1)\n                //\n                // The rule is:\n                // * For offset1, set 1 at the max component, otherwise 0.\n                // * For offset2, set 0 at the min component, otherwise 1.\n                // * For offset3, set 1 for all.\n                //\n                // Encode x0-y0, y0-z0, z0-x0 in a vec3\n                vec3 en = c0 - c0.yzx;\n                // Each represents whether x0>y0, y0>z0, z0>x0\n                en = step(vec3(0.), en);\n                // en.zxy encodes z0>x0, x0>y0, y0>x0\n                vec3 offset1 = en * (1. - en.zxy); // find max\n                vec3 offset2 = 1. - en.zxy * (1. - en); // 1-(find min)\n                vec3 offset3 = vec3(1.);\n\n                vec3 c1 = c0 - offset1 + UNSKEW;\n                vec3 c2 = c0 - offset2 + UNSKEW * 2.;\n                vec3 c3 = c0 - offset3 + UNSKEW * 3.;\n\n                // Kernel summation: dot(max(0, r^2-d^2))^4, noise contribution)\n                //\n                // First compute d^2, squared distance to the point.\n                vec4 w; // w = max(0, r^2 - d^2))\n                w.x = dot(c0, c0);\n                w.y = dot(c1, c1);\n                w.z = dot(c2, c2);\n                w.w = dot(c3, c3);\n\n                // Noise contribution should decay to zero before they cross the simplex boundary.\n                // Usually r^2 is 0.5 or 0.6;\n                // 0.5 ensures continuity but 0.6 increases the visual quality for the application\n                // where discontinuity isn't noticeable.\n                w = max(0.6 - w, 0.);\n\n                // Noise contribution from each point.\n                vec4 nc;\n                nc.x = dot(hash(s), c0);\n                nc.y = dot(hash(s + offset1), c1);\n                nc.z = dot(hash(s + offset2), c2);\n                nc.w = dot(hash(s + offset3), c3);\n\n                nc *= w*w*w*w;\n\n                // Add all the noise contributions.\n                // Should multiply by the possible max contribution to adjust the range in [-1,1].\n                return dot(vec4(32.), nc);\n            }\n            \n            float soften(float d, float blur) {\n                float blurHalf = blur * 0.5;\n                return smoothstep(-blurHalf, blurHalf, d);\n            }\n\n            float subtract(float outer, float inner) {\n                return max(outer, -inner);\n            }\n        float sdEllipse(vec2 p, vec2 wh) {\n            wh *= 0.5;\n\n            // symmetry\n            (wh.x > wh.y) ? wh = wh.yx, p = abs(p.yx) : p = abs(p);\n\n            vec2 u = wh*p, v = wh*wh;\n\n            float U1 = u.y/2.0;\n            float U2 = v.y-v.x;\n            float U3 = u.x-U2;\n            float U4 = u.x+U2;\n            float U5 = 4.0*U1;\n            float U6 = 6.0*U1;\n            float U7 = 3.0*U3;\n\n            float t = 0.5;\n            for (int i = 0; i < 3; i ++) {\n                float F1 = t*(t*t*(U1*t+U3)+U4)-U1;\n                float F2 = t*t*(U5*t+U7)+U4;\n                float F3 = t*(U6*t+U7);\n\n                t += (F1*F2)/(F1*F3-F2*F2);\n            }\n\n            t = clamp(t, 0.0, 1.0);\n\n            float d = distance(p, wh*vec2(1.0-t*t,2.0*t)/(t*t+1.0));\n            d /= wh.y;\n\n            return (dot(p/wh,p/wh)>1.0) ? d : -d;\n        }\n\n        float ellipseRing(vec2 p, vec2 wh) {\n            vec2 thicknessHalf = wh * 0.25;\n\n            float outerEllipse = sdEllipse(p, wh + thicknessHalf);\n            float innerEllipse = sdEllipse(p, wh);\n\n            return subtract(outerEllipse, innerEllipse);\n        }\n        \n            vec4 main(vec2 p) {\n                vec2 p_distorted = distort(p, in_time, in_distort_radial, in_distort_xy);\n\n                float sparkleRing = soften(ellipseRing(p_distorted-in_center, in_size), in_blur);\n                float inside = soften(sdEllipse(p_distorted-in_center, in_size * 1.2), in_blur);\n                float sparkle = sparkles(p - mod(p, in_pixelDensity * 0.8), in_time * 0.00175)\n                    * (1.-sparkleRing) * in_fadeSparkle;\n\n                float rippleInsideAlpha = (1.-inside) * in_fadeFill;\n                float rippleRingAlpha = (1.-sparkleRing) * in_fadeRing;\n                float rippleAlpha = max(rippleInsideAlpha, rippleRingAlpha) * in_color.a;\n                vec4 ripple = vec4(in_color.rgb, 1.0) * rippleAlpha;\n                return mix(ripple, vec4(sparkle), sparkle * in_sparkle_strength);\n            }\n        ";
                    }
                    throw new NoWhenBranchMatchedException();
                }
                return "\n            uniform vec2 in_center;\n            uniform vec2 in_size;\n            uniform float in_cornerRadius;\n            uniform float in_thickness;\n            uniform float in_time;\n            uniform float in_distort_radial;\n            uniform float in_distort_xy;\n            uniform float in_fadeSparkle;\n            uniform float in_fadeFill;\n            uniform float in_fadeRing;\n            uniform float in_blur;\n            uniform float in_pixelDensity;\n            layout(color) uniform vec4 in_color;\n            uniform float in_sparkle_strength;\n        \n            float triangleNoise(vec2 n) {\n                n  = fract(n * vec2(5.3987, 5.4421));\n                n += dot(n.yx, n.xy + vec2(21.5351, 14.3137));\n                float xy = n.x * n.y;\n                // compute in [0..2[ and remap to [-1.0..1.0[\n                return fract(xy * 95.4307) + fract(xy * 75.04961) - 1.0;\n            }\n\n            const float PI = 3.1415926535897932384626;\n\n            float sparkles(vec2 uv, float t) {\n                float n = triangleNoise(uv);\n                float s = 0.0;\n                for (float i = 0; i < 4; i += 1) {\n                    float l = i * 0.01;\n                    float h = l + 0.1;\n                    float o = smoothstep(n - l, h, n);\n                    o *= abs(sin(PI * o * (t + 0.55 * i)));\n                    s += o;\n                }\n                return s;\n            }\n\n            vec2 distort(vec2 p, float time, float distort_amount_radial,\n                float distort_amount_xy) {\n                    float angle = atan(p.y, p.x);\n                      return p + vec2(sin(angle * 8 + time * 0.003 + 1.641),\n                                cos(angle * 5 + 2.14 + time * 0.00412)) * distort_amount_radial\n                         + vec2(sin(p.x * 0.01 + time * 0.00215 + 0.8123),\n                                cos(p.y * 0.01 + time * 0.005931)) * distort_amount_xy;\n            }\n\n            // Return range [-1, 1].\n            vec3 hash(vec3 p) {\n                p = fract(p * vec3(.3456, .1234, .9876));\n                p += dot(p, p.yxz + 43.21);\n                p = (p.xxy + p.yxx) * p.zyx;\n                return (fract(sin(p) * 4567.1234567) - .5) * 2.;\n            }\n\n            // Skew factors (non-uniform).\n            const float SKEW = 0.3333333;  // 1/3\n            const float UNSKEW = 0.1666667;  // 1/6\n\n            // Return range roughly [-1,1].\n            // It's because the hash function (that returns a random gradient vector) returns\n            // different magnitude of vectors. Noise doesn't have to be in the precise range thus\n            // skipped normalize.\n            float simplex3d(vec3 p) {\n                // Skew the input coordinate, so that we get squashed cubical grid\n                vec3 s = floor(p + (p.x + p.y + p.z) * SKEW);\n\n                // Unskew back\n                vec3 u = s - (s.x + s.y + s.z) * UNSKEW;\n\n                // Unskewed coordinate that is relative to p, to compute the noise contribution\n                // based on the distance.\n                vec3 c0 = p - u;\n\n                // We have six simplices (in this case tetrahedron, since we are in 3D) that we\n                // could possibly in.\n                // Here, we are finding the correct tetrahedron (simplex shape), and traverse its\n                // four vertices (c0..3) when computing noise contribution.\n                // The way we find them is by comparing c0's x,y,z values.\n                // For example in 2D, we can find the triangle (simplex shape in 2D) that we are in\n                // by comparing x and y values. i.e. x>y lower, x<y, upper triangle.\n                // Same applies in 3D.\n                //\n                // Below indicates the offsets (or offset directions) when c0=(x0,y0,z0)\n                // x0>y0>z0: (1,0,0), (1,1,0), (1,1,1)\n                // x0>z0>y0: (1,0,0), (1,0,1), (1,1,1)\n                // z0>x0>y0: (0,0,1), (1,0,1), (1,1,1)\n                // z0>y0>x0: (0,0,1), (0,1,1), (1,1,1)\n                // y0>z0>x0: (0,1,0), (0,1,1), (1,1,1)\n                // y0>x0>z0: (0,1,0), (1,1,0), (1,1,1)\n                //\n                // The rule is:\n                // * For offset1, set 1 at the max component, otherwise 0.\n                // * For offset2, set 0 at the min component, otherwise 1.\n                // * For offset3, set 1 for all.\n                //\n                // Encode x0-y0, y0-z0, z0-x0 in a vec3\n                vec3 en = c0 - c0.yzx;\n                // Each represents whether x0>y0, y0>z0, z0>x0\n                en = step(vec3(0.), en);\n                // en.zxy encodes z0>x0, x0>y0, y0>x0\n                vec3 offset1 = en * (1. - en.zxy); // find max\n                vec3 offset2 = 1. - en.zxy * (1. - en); // 1-(find min)\n                vec3 offset3 = vec3(1.);\n\n                vec3 c1 = c0 - offset1 + UNSKEW;\n                vec3 c2 = c0 - offset2 + UNSKEW * 2.;\n                vec3 c3 = c0 - offset3 + UNSKEW * 3.;\n\n                // Kernel summation: dot(max(0, r^2-d^2))^4, noise contribution)\n                //\n                // First compute d^2, squared distance to the point.\n                vec4 w; // w = max(0, r^2 - d^2))\n                w.x = dot(c0, c0);\n                w.y = dot(c1, c1);\n                w.z = dot(c2, c2);\n                w.w = dot(c3, c3);\n\n                // Noise contribution should decay to zero before they cross the simplex boundary.\n                // Usually r^2 is 0.5 or 0.6;\n                // 0.5 ensures continuity but 0.6 increases the visual quality for the application\n                // where discontinuity isn't noticeable.\n                w = max(0.6 - w, 0.);\n\n                // Noise contribution from each point.\n                vec4 nc;\n                nc.x = dot(hash(s), c0);\n                nc.y = dot(hash(s + offset1), c1);\n                nc.z = dot(hash(s + offset2), c2);\n                nc.w = dot(hash(s + offset3), c3);\n\n                nc *= w*w*w*w;\n\n                // Add all the noise contributions.\n                // Should multiply by the possible max contribution to adjust the range in [-1,1].\n                return dot(vec4(32.), nc);\n            }\n            \n            float soften(float d, float blur) {\n                float blurHalf = blur * 0.5;\n                return smoothstep(-blurHalf, blurHalf, d);\n            }\n\n            float subtract(float outer, float inner) {\n                return max(outer, -inner);\n            }\n        \n            float sdRoundedBox(vec2 p, vec2 size, float cornerRadius) {\n                size *= 0.5;\n                cornerRadius *= 0.5;\n                vec2 d = abs(p)-size+cornerRadius;\n\n                float outside = length(max(d, 0.0));\n                float inside = min(max(d.x, d.y), 0.0);\n\n                return (outside+inside-cornerRadius)/size.y;\n            }\n\n            float roundedBoxRing(vec2 p, vec2 size, float cornerRadius,\n                float borderThickness) {\n                float outerRoundBox = sdRoundedBox(p, size + vec2(borderThickness),\n                    cornerRadius + borderThickness);\n                float innerRoundBox = sdRoundedBox(p, size, cornerRadius);\n                return subtract(outerRoundBox, innerRoundBox);\n            }\n        \n            vec4 main(vec2 p) {\n                float sparkleRing = soften(roundedBoxRing(p-in_center, in_size, in_cornerRadius,\n                    in_thickness), in_blur);\n                float inside = soften(sdRoundedBox(p-in_center, in_size * 1.25, in_cornerRadius),\n                    in_blur);\n                float sparkle = sparkles(p - mod(p, in_pixelDensity * 0.8), in_time * 0.00175)\n                    * (1.-sparkleRing) * in_fadeSparkle;\n\n                float rippleInsideAlpha = (1.-inside) * in_fadeFill;\n                float rippleRingAlpha = (1.-sparkleRing) * in_fadeRing;\n                float rippleAlpha = max(rippleInsideAlpha, rippleRingAlpha) * in_color.a;\n                vec4 ripple = vec4(in_color.rgb, 1.0) * rippleAlpha;\n                return mix(ripple, vec4(sparkle), sparkle * in_sparkle_strength);\n            }\n        ";
            }
            return "\n            uniform vec2 in_center;\n            uniform vec2 in_size;\n            uniform float in_cornerRadius;\n            uniform float in_thickness;\n            uniform float in_time;\n            uniform float in_distort_radial;\n            uniform float in_distort_xy;\n            uniform float in_fadeSparkle;\n            uniform float in_fadeFill;\n            uniform float in_fadeRing;\n            uniform float in_blur;\n            uniform float in_pixelDensity;\n            layout(color) uniform vec4 in_color;\n            uniform float in_sparkle_strength;\n        \n            float triangleNoise(vec2 n) {\n                n  = fract(n * vec2(5.3987, 5.4421));\n                n += dot(n.yx, n.xy + vec2(21.5351, 14.3137));\n                float xy = n.x * n.y;\n                // compute in [0..2[ and remap to [-1.0..1.0[\n                return fract(xy * 95.4307) + fract(xy * 75.04961) - 1.0;\n            }\n\n            const float PI = 3.1415926535897932384626;\n\n            float sparkles(vec2 uv, float t) {\n                float n = triangleNoise(uv);\n                float s = 0.0;\n                for (float i = 0; i < 4; i += 1) {\n                    float l = i * 0.01;\n                    float h = l + 0.1;\n                    float o = smoothstep(n - l, h, n);\n                    o *= abs(sin(PI * o * (t + 0.55 * i)));\n                    s += o;\n                }\n                return s;\n            }\n\n            vec2 distort(vec2 p, float time, float distort_amount_radial,\n                float distort_amount_xy) {\n                    float angle = atan(p.y, p.x);\n                      return p + vec2(sin(angle * 8 + time * 0.003 + 1.641),\n                                cos(angle * 5 + 2.14 + time * 0.00412)) * distort_amount_radial\n                         + vec2(sin(p.x * 0.01 + time * 0.00215 + 0.8123),\n                                cos(p.y * 0.01 + time * 0.005931)) * distort_amount_xy;\n            }\n\n            // Return range [-1, 1].\n            vec3 hash(vec3 p) {\n                p = fract(p * vec3(.3456, .1234, .9876));\n                p += dot(p, p.yxz + 43.21);\n                p = (p.xxy + p.yxx) * p.zyx;\n                return (fract(sin(p) * 4567.1234567) - .5) * 2.;\n            }\n\n            // Skew factors (non-uniform).\n            const float SKEW = 0.3333333;  // 1/3\n            const float UNSKEW = 0.1666667;  // 1/6\n\n            // Return range roughly [-1,1].\n            // It's because the hash function (that returns a random gradient vector) returns\n            // different magnitude of vectors. Noise doesn't have to be in the precise range thus\n            // skipped normalize.\n            float simplex3d(vec3 p) {\n                // Skew the input coordinate, so that we get squashed cubical grid\n                vec3 s = floor(p + (p.x + p.y + p.z) * SKEW);\n\n                // Unskew back\n                vec3 u = s - (s.x + s.y + s.z) * UNSKEW;\n\n                // Unskewed coordinate that is relative to p, to compute the noise contribution\n                // based on the distance.\n                vec3 c0 = p - u;\n\n                // We have six simplices (in this case tetrahedron, since we are in 3D) that we\n                // could possibly in.\n                // Here, we are finding the correct tetrahedron (simplex shape), and traverse its\n                // four vertices (c0..3) when computing noise contribution.\n                // The way we find them is by comparing c0's x,y,z values.\n                // For example in 2D, we can find the triangle (simplex shape in 2D) that we are in\n                // by comparing x and y values. i.e. x>y lower, x<y, upper triangle.\n                // Same applies in 3D.\n                //\n                // Below indicates the offsets (or offset directions) when c0=(x0,y0,z0)\n                // x0>y0>z0: (1,0,0), (1,1,0), (1,1,1)\n                // x0>z0>y0: (1,0,0), (1,0,1), (1,1,1)\n                // z0>x0>y0: (0,0,1), (1,0,1), (1,1,1)\n                // z0>y0>x0: (0,0,1), (0,1,1), (1,1,1)\n                // y0>z0>x0: (0,1,0), (0,1,1), (1,1,1)\n                // y0>x0>z0: (0,1,0), (1,1,0), (1,1,1)\n                //\n                // The rule is:\n                // * For offset1, set 1 at the max component, otherwise 0.\n                // * For offset2, set 0 at the min component, otherwise 1.\n                // * For offset3, set 1 for all.\n                //\n                // Encode x0-y0, y0-z0, z0-x0 in a vec3\n                vec3 en = c0 - c0.yzx;\n                // Each represents whether x0>y0, y0>z0, z0>x0\n                en = step(vec3(0.), en);\n                // en.zxy encodes z0>x0, x0>y0, y0>x0\n                vec3 offset1 = en * (1. - en.zxy); // find max\n                vec3 offset2 = 1. - en.zxy * (1. - en); // 1-(find min)\n                vec3 offset3 = vec3(1.);\n\n                vec3 c1 = c0 - offset1 + UNSKEW;\n                vec3 c2 = c0 - offset2 + UNSKEW * 2.;\n                vec3 c3 = c0 - offset3 + UNSKEW * 3.;\n\n                // Kernel summation: dot(max(0, r^2-d^2))^4, noise contribution)\n                //\n                // First compute d^2, squared distance to the point.\n                vec4 w; // w = max(0, r^2 - d^2))\n                w.x = dot(c0, c0);\n                w.y = dot(c1, c1);\n                w.z = dot(c2, c2);\n                w.w = dot(c3, c3);\n\n                // Noise contribution should decay to zero before they cross the simplex boundary.\n                // Usually r^2 is 0.5 or 0.6;\n                // 0.5 ensures continuity but 0.6 increases the visual quality for the application\n                // where discontinuity isn't noticeable.\n                w = max(0.6 - w, 0.);\n\n                // Noise contribution from each point.\n                vec4 nc;\n                nc.x = dot(hash(s), c0);\n                nc.y = dot(hash(s + offset1), c1);\n                nc.z = dot(hash(s + offset2), c2);\n                nc.w = dot(hash(s + offset3), c3);\n\n                nc *= w*w*w*w;\n\n                // Add all the noise contributions.\n                // Should multiply by the possible max contribution to adjust the range in [-1,1].\n                return dot(vec4(32.), nc);\n            }\n            \n            float soften(float d, float blur) {\n                float blurHalf = blur * 0.5;\n                return smoothstep(-blurHalf, blurHalf, d);\n            }\n\n            float subtract(float outer, float inner) {\n                return max(outer, -inner);\n            }\n        \n            float sdCircle(vec2 p, float r) {\n                return (length(p)-r) / r;\n            }\n\n            float circleRing(vec2 p, float radius) {\n                float thicknessHalf = radius * 0.25;\n\n                float outerCircle = sdCircle(p, radius + thicknessHalf);\n                float innerCircle = sdCircle(p, radius);\n\n                return subtract(outerCircle, innerCircle);\n            }\n        \n            vec4 main(vec2 p) {\n                vec2 p_distorted = distort(p, in_time, in_distort_radial, in_distort_xy);\n                float radius = in_size.x * 0.5;\n                float sparkleRing = soften(circleRing(p_distorted-in_center, radius), in_blur);\n                float inside = soften(sdCircle(p_distorted-in_center, radius * 1.25), in_blur);\n                float sparkle = sparkles(p - mod(p, in_pixelDensity * 0.8), in_time * 0.00175)\n                    * (1.-sparkleRing) * in_fadeSparkle;\n\n                float rippleInsideAlpha = (1.-inside) * in_fadeFill;\n                float rippleRingAlpha = (1.-sparkleRing) * in_fadeRing;\n                float rippleAlpha = max(rippleInsideAlpha, rippleRingAlpha) * in_color.a;\n                vec4 ripple = vec4(in_color.rgb, 1.0) * rippleAlpha;\n                return mix(ripple, vec4(sparkle), sparkle * in_sparkle_strength);\n            }\n        ";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float subProgress(float f, float f2, float f3) {
            if (f == f2) {
                return f3 > f ? 1.0f : 0.0f;
            }
            return (Math.min(Math.max(f3, Math.min(f, f2)), Math.max(f, f2)) - f) / (f2 - f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getFade(FadeParams fadeParams, float f) {
            return Math.min(subProgress(fadeParams.getFadeInStart(), fadeParams.getFadeInEnd(), f), 1.0f - subProgress(fadeParams.getFadeOutStart(), fadeParams.getFadeOutEnd(), f));
        }
    }

    public final RippleSize getRippleSize() {
        return this.rippleSize;
    }

    public final void setRawProgress(float f) {
        this.rawProgress = f;
        setProgress(Interpolators.STANDARD.getInterpolation(f));
        Companion companion = Companion;
        setFloatUniform("in_fadeSparkle", companion.getFade(this.sparkleRingFadeParams, f));
        setFloatUniform("in_fadeRing", companion.getFade(this.baseRingFadeParams, f));
        setFloatUniform("in_fadeFill", companion.getFade(this.centerFillFadeParams, f));
    }

    private final void setProgress(float f) {
        this.progress = f;
        this.rippleSize.update(f);
        setFloatUniform("in_size", this.rippleSize.getCurrentWidth(), this.rippleSize.getCurrentHeight());
        setFloatUniform("in_thickness", this.rippleSize.getCurrentHeight() * 0.5f);
        setFloatUniform("in_cornerRadius", Math.min(this.rippleSize.getCurrentWidth(), this.rippleSize.getCurrentHeight()));
        setFloatUniform("in_blur", MathUtils.lerp(this.blurStart, this.blurEnd, f));
    }

    public final void setColor(int i) {
        this.color = i;
        setColorUniform("in_color", i);
    }

    public final void setSparkleStrength(float f) {
        this.sparkleStrength = f;
        setFloatUniform("in_sparkle_strength", f);
    }

    public final void setPixelDensity(float f) {
        this.pixelDensity = f;
        setFloatUniform("in_pixelDensity", f);
    }

    public final FadeParams getSparkleRingFadeParams() {
        return this.sparkleRingFadeParams;
    }

    public final FadeParams getBaseRingFadeParams() {
        return this.baseRingFadeParams;
    }

    public final FadeParams getCenterFillFadeParams() {
        return this.centerFillFadeParams;
    }

    /* compiled from: RippleShader.kt */
    /* loaded from: classes2.dex */
    public static final class FadeParams {
        private float fadeInEnd;
        private float fadeInStart;
        private float fadeOutEnd;
        private float fadeOutStart;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FadeParams) {
                FadeParams fadeParams = (FadeParams) obj;
                return Float.compare(this.fadeInStart, fadeParams.fadeInStart) == 0 && Float.compare(this.fadeInEnd, fadeParams.fadeInEnd) == 0 && Float.compare(this.fadeOutStart, fadeParams.fadeOutStart) == 0 && Float.compare(this.fadeOutEnd, fadeParams.fadeOutEnd) == 0;
            }
            return false;
        }

        public int hashCode() {
            return (((((Float.hashCode(this.fadeInStart) * 31) + Float.hashCode(this.fadeInEnd)) * 31) + Float.hashCode(this.fadeOutStart)) * 31) + Float.hashCode(this.fadeOutEnd);
        }

        public String toString() {
            float f = this.fadeInStart;
            float f2 = this.fadeInEnd;
            float f3 = this.fadeOutStart;
            float f4 = this.fadeOutEnd;
            return "FadeParams(fadeInStart=" + f + ", fadeInEnd=" + f2 + ", fadeOutStart=" + f3 + ", fadeOutEnd=" + f4 + ")";
        }

        public FadeParams(float f, float f2, float f3, float f4) {
            this.fadeInStart = f;
            this.fadeInEnd = f2;
            this.fadeOutStart = f3;
            this.fadeOutEnd = f4;
        }

        public final float getFadeInStart() {
            return this.fadeInStart;
        }

        public final void setFadeInStart(float f) {
            this.fadeInStart = f;
        }

        public final float getFadeInEnd() {
            return this.fadeInEnd;
        }

        public final void setFadeInEnd(float f) {
            this.fadeInEnd = f;
        }

        public final float getFadeOutStart() {
            return this.fadeOutStart;
        }

        public final void setFadeOutStart(float f) {
            this.fadeOutStart = f;
        }

        public final float getFadeOutEnd() {
            return this.fadeOutEnd;
        }

        public final void setFadeOutEnd(float f) {
            this.fadeOutEnd = f;
        }
    }

    /* compiled from: RippleShader.kt */
    /* loaded from: classes2.dex */
    public static final class SizeAtProgress {
        private float height;
        private float t;
        private float width;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SizeAtProgress) {
                SizeAtProgress sizeAtProgress = (SizeAtProgress) obj;
                return Float.compare(this.t, sizeAtProgress.t) == 0 && Float.compare(this.width, sizeAtProgress.width) == 0 && Float.compare(this.height, sizeAtProgress.height) == 0;
            }
            return false;
        }

        public int hashCode() {
            return (((Float.hashCode(this.t) * 31) + Float.hashCode(this.width)) * 31) + Float.hashCode(this.height);
        }

        public String toString() {
            float f = this.t;
            float f2 = this.width;
            float f3 = this.height;
            return "SizeAtProgress(t=" + f + ", width=" + f2 + ", height=" + f3 + ")";
        }

        public SizeAtProgress(float f, float f2, float f3) {
            this.t = f;
            this.width = f2;
            this.height = f3;
        }

        public final float getT() {
            return this.t;
        }

        public final float getWidth() {
            return this.width;
        }

        public final float getHeight() {
            return this.height;
        }
    }

    /* compiled from: RippleShader.kt */
    /* loaded from: classes2.dex */
    public final class RippleSize {
        private float currentHeight;
        private int currentSizeIndex;
        private float currentWidth;
        private List<SizeAtProgress> sizes = new ArrayList();
        private final SizeAtProgress initialSize = new SizeAtProgress(0.0f, 0.0f, 0.0f);

        public static /* synthetic */ void getCurrentSizeIndex$annotations() {
        }

        public static /* synthetic */ void getInitialSize$annotations() {
        }

        public static /* synthetic */ void getSizes$annotations() {
        }

        public RippleSize() {
        }

        public final float getCurrentWidth() {
            return this.currentWidth;
        }

        public final float getCurrentHeight() {
            return this.currentHeight;
        }

        public final void setSizeAtProgresses(SizeAtProgress... sizes) {
            Intrinsics.checkNotNullParameter(sizes, "sizes");
            this.sizes.clear();
            this.currentSizeIndex = 0;
            CollectionsKt__MutableCollectionsKt.addAll(this.sizes, sizes);
            List<SizeAtProgress> list = this.sizes;
            if (list.size() > 1) {
                CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new Comparator() { // from class: com.android.systemui.surfaceeffects.ripple.RippleShader$RippleSize$setSizeAtProgresses$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int compareValues;
                        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Float.valueOf(((RippleShader.SizeAtProgress) t).getT()), Float.valueOf(((RippleShader.SizeAtProgress) t2).getT()));
                        return compareValues;
                    }
                });
            }
        }

        public final void update(float f) {
            int updateTargetIndex = updateTargetIndex(f);
            int max = Math.max(updateTargetIndex - 1, 0);
            SizeAtProgress sizeAtProgress = this.sizes.get(updateTargetIndex);
            SizeAtProgress sizeAtProgress2 = this.sizes.get(max);
            float subProgress = RippleShader.Companion.subProgress(sizeAtProgress2.getT(), sizeAtProgress.getT(), f);
            this.currentWidth = (sizeAtProgress.getWidth() * subProgress) + sizeAtProgress2.getWidth();
            this.currentHeight = (sizeAtProgress.getHeight() * subProgress) + sizeAtProgress2.getHeight();
        }

        private final int updateTargetIndex(float f) {
            if (this.sizes.isEmpty()) {
                if (f > 0.0f) {
                    Log.e(RippleShader.TAG, "Did you forget to set the ripple size? Use [setMaxSize] or [setSizeAtProgresses] before playing the animation.");
                }
                setSizeAtProgresses(this.initialSize);
                return this.currentSizeIndex;
            }
            SizeAtProgress sizeAtProgress = this.sizes.get(this.currentSizeIndex);
            while (f > sizeAtProgress.getT()) {
                int min = Math.min(this.currentSizeIndex + 1, this.sizes.size() - 1);
                this.currentSizeIndex = min;
                sizeAtProgress = this.sizes.get(min);
            }
            return this.currentSizeIndex;
        }
    }
}
