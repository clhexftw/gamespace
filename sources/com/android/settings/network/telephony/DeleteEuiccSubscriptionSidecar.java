package com.android.settings.network.telephony;

import android.app.FragmentManager;
import android.app.PendingIntent;
import android.telephony.SubscriptionInfo;
import android.util.Log;
import com.android.settings.SidecarFragment;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DeleteEuiccSubscriptionSidecar extends EuiccOperationSidecar {
    private List<SubscriptionInfo> mSubscriptions;

    @Override // com.android.settings.network.telephony.EuiccOperationSidecar
    public String getReceiverAction() {
        return "com.android.settings.network.DELETE_SUBSCRIPTION";
    }

    public static DeleteEuiccSubscriptionSidecar get(FragmentManager fragmentManager) {
        return (DeleteEuiccSubscriptionSidecar) SidecarFragment.get(fragmentManager, "DeleteEuiccSubscriptionSidecar", DeleteEuiccSubscriptionSidecar.class, null);
    }

    public void run(List<SubscriptionInfo> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Subscriptions cannot be empty.");
        }
        setState(1, 0);
        this.mSubscriptions = new ArrayList(list);
        deleteSubscription();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.network.telephony.EuiccOperationSidecar
    public void onActionReceived() {
        if (getResultCode() == 0 && !this.mSubscriptions.isEmpty()) {
            deleteSubscription();
        } else {
            super.onActionReceived();
        }
    }

    private void deleteSubscription() {
        SubscriptionInfo remove = this.mSubscriptions.remove(0);
        PendingIntent createCallbackIntent = createCallbackIntent();
        Log.i("DeleteEuiccSubscriptionSidecar", "Deleting subscription ID: " + remove.getSubscriptionId());
        this.mEuiccManager.deleteSubscription(remove.getSubscriptionId(), createCallbackIntent);
    }
}
