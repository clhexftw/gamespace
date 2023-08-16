package com.google.gson.internal.bind.util;

import java.util.TimeZone;
/* loaded from: classes.dex */
public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    /* JADX WARN: Removed duplicated region for block: B:49:0x00cd A[Catch: IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01bc, TryCatch #0 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01bc, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:34:0x0092, B:36:0x009a, B:47:0x00c7, B:49:0x00cd, B:51:0x00d4, B:76:0x0182, B:56:0x00e0, B:57:0x00f9, B:58:0x00fa, B:62:0x0116, B:64:0x0123, B:67:0x012c, B:69:0x014b, B:72:0x015a, B:73:0x017c, B:75:0x017f, B:61:0x0105, B:78:0x01b4, B:79:0x01bb, B:40:0x00b2, B:41:0x00b5), top: B:91:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b4 A[Catch: IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01bc, TryCatch #0 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01bc, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:34:0x0092, B:36:0x009a, B:47:0x00c7, B:49:0x00cd, B:51:0x00d4, B:76:0x0182, B:56:0x00e0, B:57:0x00f9, B:58:0x00fa, B:62:0x0116, B:64:0x0123, B:67:0x012c, B:69:0x014b, B:72:0x015a, B:73:0x017c, B:75:0x017f, B:61:0x0105, B:78:0x01b4, B:79:0x01bb, B:40:0x00b2, B:41:0x00b5), top: B:91:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r18, java.text.ParsePosition r19) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 549
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
