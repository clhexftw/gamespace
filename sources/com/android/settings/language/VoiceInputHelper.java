package com.android.settings.language;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class VoiceInputHelper {
    ArrayList<RecognizerInfo> mAvailableRecognizerInfos = new ArrayList<>();
    final Context mContext;
    ComponentName mCurrentRecognizer;

    /* loaded from: classes.dex */
    public static class BaseInfo implements Comparable<BaseInfo> {
        public final CharSequence mAppLabel;
        public final ComponentName mComponentName;
        public final String mKey;
        public final CharSequence mLabel;
        public final String mLabelStr;
        public final ServiceInfo mService;
        public final ComponentName mSettings;

        public BaseInfo(PackageManager packageManager, ServiceInfo serviceInfo, String str) {
            this.mService = serviceInfo;
            ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
            this.mComponentName = componentName;
            this.mKey = componentName.flattenToShortString();
            this.mSettings = str != null ? new ComponentName(serviceInfo.packageName, str) : null;
            CharSequence loadLabel = serviceInfo.loadLabel(packageManager);
            this.mLabel = loadLabel;
            this.mLabelStr = loadLabel.toString();
            this.mAppLabel = serviceInfo.applicationInfo.loadLabel(packageManager);
        }

        @Override // java.lang.Comparable
        public int compareTo(BaseInfo baseInfo) {
            return this.mLabelStr.compareTo(baseInfo.mLabelStr);
        }
    }

    /* loaded from: classes.dex */
    public static class RecognizerInfo extends BaseInfo {
        public final boolean mSelectableAsDefault;

        public RecognizerInfo(PackageManager packageManager, ServiceInfo serviceInfo, String str, boolean z) {
            super(packageManager, serviceInfo, str);
            this.mSelectableAsDefault = z;
        }
    }

    public VoiceInputHelper(Context context) {
        this.mContext = context;
    }

    public void buildUi() {
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "voice_recognition_service");
        if (string != null && !string.isEmpty()) {
            this.mCurrentRecognizer = ComponentName.unflattenFromString(string);
        } else {
            this.mCurrentRecognizer = null;
        }
        ArrayList<RecognizerInfo> validRecognitionServices = validRecognitionServices(this.mContext);
        this.mAvailableRecognizerInfos = new ArrayList<>();
        Iterator<RecognizerInfo> it = validRecognitionServices.iterator();
        while (it.hasNext()) {
            RecognizerInfo next = it.next();
            if (!next.mSelectableAsDefault) {
                ServiceInfo serviceInfo = next.mService;
                if (new ComponentName(serviceInfo.packageName, serviceInfo.name).equals(this.mCurrentRecognizer)) {
                }
            }
            this.mAvailableRecognizerInfos.add(next);
        }
        Collections.sort(this.mAvailableRecognizerInfos);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<RecognizerInfo> validRecognitionServices(Context context) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent("android.speech.RecognitionService"), 128);
        ArrayList<RecognizerInfo> arrayList = new ArrayList<>();
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            Pair<String, Boolean> parseRecognitionServiceXmlMetadata = parseRecognitionServiceXmlMetadata(context, serviceInfo);
            if (parseRecognitionServiceXmlMetadata != null) {
                arrayList.add(new RecognizerInfo(context.getPackageManager(), serviceInfo, (String) parseRecognitionServiceXmlMetadata.first, ((Boolean) parseRecognitionServiceXmlMetadata.second).booleanValue()));
            }
        }
        return arrayList;
    }

    private static Pair<String, Boolean> parseRecognitionServiceXmlMetadata(Context context, ServiceInfo serviceInfo) {
        try {
            XmlResourceParser loadXmlMetaData = serviceInfo.loadXmlMetaData(context.getPackageManager(), "android.speech");
            if (loadXmlMetaData == null) {
                throw new XmlPullParserException(String.format("No %s meta-data for %s package", "android.speech", serviceInfo.packageName));
            }
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(serviceInfo.applicationInfo);
            AttributeSet asAttributeSet = Xml.asAttributeSet(loadXmlMetaData);
            while (true) {
                int next = loadXmlMetaData.next();
                if (next == 1 || next == 2) {
                    break;
                }
            }
            if (!"recognition-service".equals(loadXmlMetaData.getName())) {
                throw new XmlPullParserException(String.format("%s package meta-data does not start with a `recognition-service` tag", serviceInfo.packageName));
            }
            TypedArray obtainAttributes = resourcesForApplication.obtainAttributes(asAttributeSet, R.styleable.RecognitionService);
            String string = obtainAttributes.getString(0);
            boolean z = obtainAttributes.getBoolean(1, true);
            obtainAttributes.recycle();
            loadXmlMetaData.close();
            return Pair.create(string, Boolean.valueOf(z));
        } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException e) {
            Log.e("VoiceInputHelper", String.format("Error parsing %s package recognition service meta-data", serviceInfo.packageName), e);
            return null;
        }
    }
}
