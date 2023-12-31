package com.android.settings.slices;

import android.net.Uri;
import android.util.ArrayMap;
import com.android.settings.display.AlwaysOnDisplaySlice;
import com.android.settings.display.ScreenTimeoutPreferenceController;
import com.android.settings.flashlight.FlashlightSlice;
import com.android.settings.homepage.contextualcards.slices.BluetoothDevicesSlice;
import com.android.settings.homepage.contextualcards.slices.ContextualAdaptiveSleepSlice;
import com.android.settings.homepage.contextualcards.slices.DarkThemeSlice;
import com.android.settings.homepage.contextualcards.slices.FaceSetupSlice;
import com.android.settings.homepage.contextualcards.slices.LowStorageSlice;
import com.android.settings.location.LocationSlice;
import com.android.settings.media.MediaOutputIndicatorSlice;
import com.android.settings.media.RemoteMediaSlice;
import com.android.settings.network.ProviderModelSlice;
import com.android.settings.network.telephony.MobileDataSlice;
import com.android.settings.nfc.NfcPreferenceController;
import com.android.settings.wifi.slice.ContextualWifiSlice;
import com.android.settings.wifi.slice.WifiSlice;
import java.util.Map;
/* loaded from: classes.dex */
public class CustomSliceRegistry {
    public static final Uri ALWAYS_ON_SLICE_URI;
    public static final Uri BLUETOOTH_DEVICES_SLICE_URI;
    public static final Uri BLUETOOTH_URI;
    public static final Uri CONTEXTUAL_ADAPTIVE_SLEEP_URI;
    public static final Uri CONTEXTUAL_WIFI_SLICE_URI;
    public static final Uri DARK_THEME_SLICE_URI;
    public static final Uri ENHANCED_4G_SLICE_URI;
    public static final Uri FACE_ENROLL_SLICE_URI;
    public static final Uri FLASHLIGHT_SLICE_URI;
    public static final Uri LOCATION_SLICE_URI;
    public static final Uri LOW_STORAGE_SLICE_URI;
    public static Uri MEDIA_OUTPUT_INDICATOR_SLICE_URI;
    public static final Uri MOBILE_DATA_SLICE_URI;
    public static final Uri NFC_SLICE_URI;
    public static final Uri PROVIDER_MODEL_SLICE_URI;
    public static Uri REMOTE_MEDIA_SLICE_URI;
    public static final Uri TURN_ON_WIFI_SLICE_URI;
    public static final Uri VOLUME_ALARM_URI;
    public static final Uri VOLUME_CALL_URI;
    public static final Uri VOLUME_MEDIA_URI;
    public static final Uri VOLUME_NOTIFICATION_URI;
    public static final Uri VOLUME_RINGER_URI;
    public static final Uri VOLUME_SEPARATE_RING_URI;
    public static final Uri VOLUME_SLICES_URI;
    public static final Uri WIFI_CALLING_PREFERENCE_URI;
    public static final Uri WIFI_CALLING_URI;
    public static final Uri WIFI_SLICE_URI;
    public static final Uri ZEN_MODE_SLICE_URI;
    static final Map<Uri, Class<? extends CustomSliceable>> sUriToSlice;

    static {
        Uri build = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("intent").appendPath(ScreenTimeoutPreferenceController.PREF_NAME).build();
        CONTEXTUAL_ADAPTIVE_SLEEP_URI = build;
        BLUETOOTH_URI = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("bluetooth").build();
        Uri build2 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("bluetooth_devices").build();
        BLUETOOTH_DEVICES_SLICE_URI = build2;
        Uri build3 = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("contextual_wifi").build();
        CONTEXTUAL_WIFI_SLICE_URI = build3;
        ENHANCED_4G_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("enhanced_4g_lte").build();
        Uri build4 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("face_unlock_greeting_card").build();
        FACE_ENROLL_SLICE_URI = build4;
        Uri build5 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("flashlight").build();
        FLASHLIGHT_SLICE_URI = build5;
        Uri build6 = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("location").build();
        LOCATION_SLICE_URI = build6;
        Uri build7 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendEncodedPath("intent").appendPath("low_storage").build();
        LOW_STORAGE_SLICE_URI = build7;
        NFC_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath(NfcPreferenceController.KEY_TOGGLE_NFC).build();
        Uri build8 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendEncodedPath("action").appendPath("mobile_data").build();
        MOBILE_DATA_SLICE_URI = build8;
        Uri build9 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendEncodedPath("action").appendPath("provider_model").build();
        PROVIDER_MODEL_SLICE_URI = build9;
        VOLUME_ALARM_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("alarm_volume").build();
        VOLUME_CALL_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("call_volume").build();
        VOLUME_MEDIA_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("media_volume").build();
        VOLUME_RINGER_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("ring_volume").build();
        VOLUME_SEPARATE_RING_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("separate_ring_volume").build();
        VOLUME_NOTIFICATION_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("notification_volume").build();
        VOLUME_SLICES_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("volume_slices").build();
        WIFI_CALLING_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("intent").appendPath("wifi_calling").build();
        WIFI_CALLING_PREFERENCE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("wifi_calling_preference").build();
        Uri build10 = new Uri.Builder().scheme("content").authority("android.settings.slices").appendPath("action").appendPath("wifi").build();
        WIFI_SLICE_URI = build10;
        ZEN_MODE_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("zen_mode_toggle").build();
        MEDIA_OUTPUT_INDICATOR_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("intent").appendPath("media_output_indicator").build();
        Uri build11 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("dark_theme").build();
        DARK_THEME_SLICE_URI = build11;
        REMOTE_MEDIA_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("remote_media").build();
        Uri build12 = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("always_on_display").build();
        ALWAYS_ON_SLICE_URI = build12;
        TURN_ON_WIFI_SLICE_URI = new Uri.Builder().scheme("content").authority("com.android.settings.slices").appendPath("action").appendPath("turn_on_wifi").build();
        ArrayMap arrayMap = new ArrayMap();
        sUriToSlice = arrayMap;
        arrayMap.put(build2, BluetoothDevicesSlice.class);
        arrayMap.put(build, ContextualAdaptiveSleepSlice.class);
        arrayMap.put(build3, ContextualWifiSlice.class);
        arrayMap.put(build4, FaceSetupSlice.class);
        arrayMap.put(build5, FlashlightSlice.class);
        arrayMap.put(build6, LocationSlice.class);
        arrayMap.put(build7, LowStorageSlice.class);
        arrayMap.put(MEDIA_OUTPUT_INDICATOR_SLICE_URI, MediaOutputIndicatorSlice.class);
        arrayMap.put(build8, MobileDataSlice.class);
        arrayMap.put(build9, ProviderModelSlice.class);
        arrayMap.put(build10, WifiSlice.class);
        arrayMap.put(build11, DarkThemeSlice.class);
        arrayMap.put(REMOTE_MEDIA_SLICE_URI, RemoteMediaSlice.class);
        arrayMap.put(build12, AlwaysOnDisplaySlice.class);
    }

    public static Class<? extends CustomSliceable> getSliceClassByUri(Uri uri) {
        return sUriToSlice.get(removeParameterFromUri(uri));
    }

    public static Uri removeParameterFromUri(Uri uri) {
        if (uri != null) {
            return uri.buildUpon().clearQuery().build();
        }
        return null;
    }

    public static boolean isValidUri(Uri uri) {
        return sUriToSlice.containsKey(removeParameterFromUri(uri));
    }

    public static boolean isValidAction(String str) {
        return isValidUri(Uri.parse(str));
    }
}
