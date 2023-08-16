package com.android.settings.development.tare;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.android.settings.R;
import com.android.settings.development.tare.TareFactorController;
/* loaded from: classes.dex */
public class AlarmManagerFragment extends Fragment implements TareFactorController.DataChangeListener {
    private String[][] mChildren;
    private TareFactorExpandableListAdapter mExpandableListAdapter;
    private TareFactorController mFactorController;
    private String[] mGroups;
    private String[][] mKeys;

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFactorController = TareFactorController.getInstance(getContext());
        populateArrays();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.tare_policy_fragment, (ViewGroup) null);
        ExpandableListView expandableListView = (ExpandableListView) inflate.findViewById(R.id.factor_list);
        this.mExpandableListAdapter = new TareFactorExpandableListAdapter(this.mFactorController, LayoutInflater.from(getActivity()), this.mGroups, this.mChildren, this.mKeys);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(this.mExpandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.android.settings.development.tare.AlarmManagerFragment.1
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView2, View view, int i, int i2, long j) {
                String key = AlarmManagerFragment.this.mExpandableListAdapter.getKey(i, i2);
                AlarmManagerFragment.this.mFactorController.createDialog(key).show(AlarmManagerFragment.this.getFragmentManager(), key);
                return true;
            }
        });
        return inflate;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mFactorController.registerListener(this);
    }

    @Override // android.app.Fragment
    public void onStop() {
        this.mFactorController.unregisterListener(this);
        super.onStop();
    }

    @Override // com.android.settings.development.tare.TareFactorController.DataChangeListener
    public void onDataChanged() {
        this.mExpandableListAdapter.notifyDataSetChanged();
    }

    private void populateArrays() {
        Resources resources = getResources();
        this.mGroups = new String[]{resources.getString(R.string.tare_consumption_limits), resources.getString(R.string.tare_balances), resources.getString(R.string.tare_actions_ctp), resources.getString(R.string.tare_actions_base_price), resources.getString(R.string.tare_rewards_instantaneous), resources.getString(R.string.tare_rewards_ongoing), resources.getString(R.string.tare_rewards_max)};
        int i = R.array.tare_alarm_manager_actions;
        int i2 = R.array.tare_rewards_subfactors;
        this.mChildren = new String[][]{resources.getStringArray(R.array.tare_consumption_limit_subfactors), resources.getStringArray(R.array.tare_app_balance_subfactors), resources.getStringArray(i), resources.getStringArray(i), resources.getStringArray(i2), new String[]{resources.getString(R.string.tare_top_activity)}, resources.getStringArray(i2)};
        this.mKeys = new String[][]{new String[]{"am_initial_consumption_limit", "am_hard_consumption_limit"}, new String[]{"am_max_satiated_balance", "am_min_satiated_balance_exempted", "am_min_satiated_balance_headless_system_app", "am_min_satiated_balance_other_app"}, new String[]{"am_action_alarm_allow_while_idle_exact_wakeup_ctp", "am_action_alarm_allow_while_idle_inexact_wakeup_ctp", "am_action_alarm_exact_wakeup_ctp", "am_action_alarm_inexact_wakeup_ctp", "am_action_alarm_allow_while_idle_exact_nonwakeup_ctp", "am_action_alarm_allow_while_idle_inexact_nonwakeup_ctp", "am_action_alarm_exact_nonwakeup_ctp", "am_action_alarm_inexact_nonwakeup_ctp", "am_action_alarm_alarmclock_ctp"}, new String[]{"am_action_alarm_allow_while_idle_exact_wakeup_base_price", "am_action_alarm_allow_while_idle_inexact_wakeup_base_price", "am_action_alarm_exact_wakeup_base_price", "am_action_alarm_inexact_wakeup_base_price", "am_action_alarm_allow_while_idle_exact_nonwakeup_base_price", "am_action_alarm_allow_while_idle_inexact_nonwakeup_base_price", "am_action_alarm_exact_nonwakeup_base_price", "am_action_alarm_inexact_nonwakeup_base_price", "am_action_alarm_alarmclock_base_price"}, new String[]{"am_reward_top_activity_instant", "am_reward_notification_seen_instant", "am_reward_notification_interaction_instant", "am_reward_widget_interaction_instant", "am_reward_other_user_interaction_instant"}, new String[]{"am_reward_top_activity_ongoing"}, new String[]{"am_reward_top_activity_max", "am_reward_notification_seen_max", "am_reward_notification_interaction_max", "am_reward_widget_interaction_max", "am_reward_other_user_interaction_max"}};
    }
}
