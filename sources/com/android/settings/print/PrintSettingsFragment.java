package com.android.settings.print;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintJob;
import android.print.PrintJobId;
import android.print.PrintJobInfo;
import android.print.PrintManager;
import android.printservice.PrintServiceInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.widget.AppPreference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class PrintSettingsFragment extends ProfileSettingsPreferenceFragment implements View.OnClickListener {
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider(R.xml.print_settings);
    private PreferenceCategory mActivePrintJobsCategory;
    private Button mAddNewServiceButton;
    private PrintJobsController mPrintJobsController;
    private PreferenceCategory mPrintServicesCategory;
    private PrintServicesController mPrintServicesController;

    @Override // com.android.settings.print.ProfileSettingsPreferenceFragment
    protected String getIntentActionString() {
        return "android.settings.ACTION_PRINT_SETTINGS";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 80;
    }

    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_printing;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        addPreferencesFromResource(R.xml.print_settings);
        this.mActivePrintJobsCategory = (PreferenceCategory) findPreference("print_jobs_category");
        this.mPrintServicesCategory = (PreferenceCategory) findPreference("print_services_category");
        getPreferenceScreen().removePreference(this.mActivePrintJobsCategory);
        this.mPrintJobsController = new PrintJobsController();
        getLoaderManager().initLoader(1, null, this.mPrintJobsController);
        this.mPrintServicesController = new PrintServicesController();
        getLoaderManager().initLoader(2, null, this.mPrintServicesController);
        return onCreateView;
    }

    @Override // com.android.settings.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
        startSubSettingsIfNeeded();
    }

    @Override // com.android.settings.print.ProfileSettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) getListView().getParent();
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.empty_print_state, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.message)).setText(R.string.print_no_services_installed);
        if (createAddNewServiceIntentOrNull() != null) {
            Button button = (Button) inflate.findViewById(R.id.add_new_service);
            this.mAddNewServiceButton = button;
            button.setOnClickListener(this);
            this.mAddNewServiceButton.setVisibility(0);
        }
        viewGroup.addView(inflate);
        setEmptyView(inflate);
    }

    /* loaded from: classes.dex */
    private final class PrintServicesController implements LoaderManager.LoaderCallbacks<List<PrintServiceInfo>> {
        private PrintServicesController() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<PrintServiceInfo>> onCreateLoader(int i, Bundle bundle) {
            PrintManager printManager = (PrintManager) PrintSettingsFragment.this.getContext().getSystemService("print");
            if (printManager != null) {
                return new SettingsPrintServicesLoader(printManager, PrintSettingsFragment.this.getContext(), 3);
            }
            return null;
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<PrintServiceInfo>> loader, List<PrintServiceInfo> list) {
            if (list.isEmpty()) {
                PrintSettingsFragment.this.getPreferenceScreen().removePreference(PrintSettingsFragment.this.mPrintServicesCategory);
                return;
            }
            if (PrintSettingsFragment.this.getPreferenceScreen().findPreference("print_services_category") == null) {
                PrintSettingsFragment.this.getPreferenceScreen().addPreference(PrintSettingsFragment.this.mPrintServicesCategory);
            }
            PrintSettingsFragment.this.mPrintServicesCategory.removeAll();
            PackageManager packageManager = PrintSettingsFragment.this.getActivity().getPackageManager();
            Context prefContext = PrintSettingsFragment.this.getPrefContext();
            if (prefContext == null) {
                Log.w("PrintSettingsFragment", "No preference context, skip adding print services");
                return;
            }
            for (PrintServiceInfo printServiceInfo : list) {
                Preference appPreference = new AppPreference(prefContext);
                String charSequence = printServiceInfo.getResolveInfo().loadLabel(packageManager).toString();
                appPreference.setTitle(charSequence);
                ComponentName componentName = printServiceInfo.getComponentName();
                appPreference.setKey(componentName.flattenToString());
                appPreference.setFragment(PrintServiceSettingsFragment.class.getName());
                appPreference.setPersistent(false);
                if (printServiceInfo.isEnabled()) {
                    appPreference.setSummary(PrintSettingsFragment.this.getString(R.string.print_feature_state_on));
                } else {
                    appPreference.setSummary(PrintSettingsFragment.this.getString(R.string.print_feature_state_off));
                }
                Drawable loadIcon = printServiceInfo.getResolveInfo().loadIcon(packageManager);
                if (loadIcon != null) {
                    appPreference.setIcon(loadIcon);
                }
                Bundle extras = appPreference.getExtras();
                extras.putBoolean("EXTRA_CHECKED", printServiceInfo.isEnabled());
                extras.putString("EXTRA_TITLE", charSequence);
                extras.putString("EXTRA_SERVICE_COMPONENT_NAME", componentName.flattenToString());
                PrintSettingsFragment.this.mPrintServicesCategory.addPreference(appPreference);
            }
            Preference newAddServicePreferenceOrNull = PrintSettingsFragment.this.newAddServicePreferenceOrNull();
            if (newAddServicePreferenceOrNull != null) {
                PrintSettingsFragment.this.mPrintServicesCategory.addPreference(newAddServicePreferenceOrNull);
            }
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<PrintServiceInfo>> loader) {
            PrintSettingsFragment.this.getPreferenceScreen().removePreference(PrintSettingsFragment.this.mPrintServicesCategory);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Preference newAddServicePreferenceOrNull() {
        Intent createAddNewServiceIntentOrNull = createAddNewServiceIntentOrNull();
        if (createAddNewServiceIntentOrNull == null) {
            return null;
        }
        Preference preference = new Preference(getPrefContext());
        preference.setTitle(R.string.print_menu_item_add_service);
        preference.setIcon(R.drawable.ic_add_24dp);
        preference.setOrder(2147483646);
        preference.setIntent(createAddNewServiceIntentOrNull);
        preference.setPersistent(false);
        return preference;
    }

    private Intent createAddNewServiceIntentOrNull() {
        String string = Settings.Secure.getString(getContentResolver(), "print_service_search_uri");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new Intent("android.intent.action.VIEW", Uri.parse(string));
    }

    private void startSubSettingsIfNeeded() {
        String string;
        if (getArguments() == null || (string = getArguments().getString("EXTRA_PRINT_SERVICE_COMPONENT_NAME")) == null) {
            return;
        }
        getArguments().remove("EXTRA_PRINT_SERVICE_COMPONENT_NAME");
        Preference findPreference = findPreference(string);
        if (findPreference != null) {
            findPreference.performClick();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent createAddNewServiceIntentOrNull;
        if (this.mAddNewServiceButton != view || (createAddNewServiceIntentOrNull = createAddNewServiceIntentOrNull()) == null) {
            return;
        }
        try {
            startActivity(createAddNewServiceIntentOrNull);
        } catch (ActivityNotFoundException e) {
            Log.w("PrintSettingsFragment", "Unable to start activity", e);
        }
    }

    /* loaded from: classes.dex */
    private final class PrintJobsController implements LoaderManager.LoaderCallbacks<List<PrintJobInfo>> {
        private PrintJobsController() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<PrintJobInfo>> onCreateLoader(int i, Bundle bundle) {
            if (i == 1) {
                return new PrintJobsLoader(PrintSettingsFragment.this.getContext());
            }
            return null;
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<PrintJobInfo>> loader, List<PrintJobInfo> list) {
            if (list == null || list.isEmpty()) {
                PrintSettingsFragment.this.getPreferenceScreen().removePreference(PrintSettingsFragment.this.mActivePrintJobsCategory);
                return;
            }
            if (PrintSettingsFragment.this.getPreferenceScreen().findPreference("print_jobs_category") == null) {
                PrintSettingsFragment.this.getPreferenceScreen().addPreference(PrintSettingsFragment.this.mActivePrintJobsCategory);
            }
            PrintSettingsFragment.this.mActivePrintJobsCategory.removeAll();
            Context prefContext = PrintSettingsFragment.this.getPrefContext();
            if (prefContext == null) {
                Log.w("PrintSettingsFragment", "No preference context, skip adding print jobs");
                return;
            }
            for (PrintJobInfo printJobInfo : list) {
                Preference preference = new Preference(prefContext);
                preference.setPersistent(false);
                preference.setFragment(PrintJobSettingsFragment.class.getName());
                preference.setKey(printJobInfo.getId().flattenToString());
                int state = printJobInfo.getState();
                if (state == 2 || state == 3) {
                    if (!printJobInfo.isCancelling()) {
                        preference.setTitle(PrintSettingsFragment.this.getString(R.string.print_printing_state_title_template, printJobInfo.getLabel()));
                    } else {
                        preference.setTitle(PrintSettingsFragment.this.getString(R.string.print_cancelling_state_title_template, printJobInfo.getLabel()));
                    }
                } else if (state != 4) {
                    if (state == 6) {
                        preference.setTitle(PrintSettingsFragment.this.getString(R.string.print_failed_state_title_template, printJobInfo.getLabel()));
                    }
                } else if (!printJobInfo.isCancelling()) {
                    preference.setTitle(PrintSettingsFragment.this.getString(R.string.print_blocked_state_title_template, printJobInfo.getLabel()));
                } else {
                    preference.setTitle(PrintSettingsFragment.this.getString(R.string.print_cancelling_state_title_template, printJobInfo.getLabel()));
                }
                preference.setSummary(PrintSettingsFragment.this.getString(R.string.print_job_summary, printJobInfo.getPrinterName(), DateUtils.formatSameDayTime(printJobInfo.getCreationTime(), printJobInfo.getCreationTime(), 3, 3)));
                TypedArray obtainStyledAttributes = PrintSettingsFragment.this.getActivity().obtainStyledAttributes(new int[]{16843817});
                int color = obtainStyledAttributes.getColor(0, 0);
                obtainStyledAttributes.recycle();
                int state2 = printJobInfo.getState();
                if (state2 == 2 || state2 == 3) {
                    Drawable drawable = PrintSettingsFragment.this.getActivity().getDrawable(17302828);
                    drawable.setTint(color);
                    preference.setIcon(drawable);
                } else if (state2 == 4 || state2 == 6) {
                    Drawable drawable2 = PrintSettingsFragment.this.getActivity().getDrawable(17302829);
                    drawable2.setTint(color);
                    preference.setIcon(drawable2);
                }
                preference.getExtras().putString("EXTRA_PRINT_JOB_ID", printJobInfo.getId().flattenToString());
                PrintSettingsFragment.this.mActivePrintJobsCategory.addPreference(preference);
            }
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<PrintJobInfo>> loader) {
            PrintSettingsFragment.this.getPreferenceScreen().removePreference(PrintSettingsFragment.this.mActivePrintJobsCategory);
        }
    }

    /* loaded from: classes.dex */
    private static final class PrintJobsLoader extends AsyncTaskLoader<List<PrintJobInfo>> {
        private PrintManager.PrintJobStateChangeListener mPrintJobStateChangeListener;
        private List<PrintJobInfo> mPrintJobs;
        private final PrintManager mPrintManager;

        public PrintJobsLoader(Context context) {
            super(context);
            this.mPrintJobs = new ArrayList();
            this.mPrintManager = ((PrintManager) context.getSystemService("print")).getGlobalPrintManagerForUser(context.getUserId());
        }

        @Override // androidx.loader.content.Loader
        public void deliverResult(List<PrintJobInfo> list) {
            if (isStarted()) {
                super.deliverResult((PrintJobsLoader) list);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.Loader
        public void onStartLoading() {
            if (!this.mPrintJobs.isEmpty()) {
                deliverResult((List<PrintJobInfo>) new ArrayList(this.mPrintJobs));
            }
            if (this.mPrintJobStateChangeListener == null) {
                PrintManager.PrintJobStateChangeListener printJobStateChangeListener = new PrintManager.PrintJobStateChangeListener() { // from class: com.android.settings.print.PrintSettingsFragment.PrintJobsLoader.1
                    public void onPrintJobStateChanged(PrintJobId printJobId) {
                        PrintJobsLoader.this.onForceLoad();
                    }
                };
                this.mPrintJobStateChangeListener = printJobStateChangeListener;
                this.mPrintManager.addPrintJobStateChangeListener(printJobStateChangeListener);
            }
            if (this.mPrintJobs.isEmpty()) {
                onForceLoad();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.Loader
        public void onStopLoading() {
            onCancelLoad();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.Loader
        public void onReset() {
            onStopLoading();
            this.mPrintJobs.clear();
            PrintManager.PrintJobStateChangeListener printJobStateChangeListener = this.mPrintJobStateChangeListener;
            if (printJobStateChangeListener != null) {
                this.mPrintManager.removePrintJobStateChangeListener(printJobStateChangeListener);
                this.mPrintJobStateChangeListener = null;
            }
        }

        @Override // androidx.loader.content.AsyncTaskLoader
        public List<PrintJobInfo> loadInBackground() {
            List<PrintJob> printJobs = this.mPrintManager.getPrintJobs();
            int size = printJobs.size();
            ArrayList arrayList = null;
            for (int i = 0; i < size; i++) {
                PrintJobInfo info = printJobs.get(i).getInfo();
                if (PrintSettingPreferenceController.shouldShowToUser(info)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(info);
                }
            }
            return arrayList;
        }
    }
}
