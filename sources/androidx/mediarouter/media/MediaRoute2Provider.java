package androidx.mediarouter.media;

import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRoute2Provider;
import androidx.mediarouter.media.MediaRouteDescriptor;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
class MediaRoute2Provider extends MediaRouteProvider {
    static final boolean DEBUG = Log.isLoggable("MR2Provider", 3);
    final Callback mCallback;
    private final MediaRouter2.ControllerCallback mControllerCallback;
    final Map<MediaRouter2.RoutingController, GroupRouteController> mControllerMap;
    private final Handler mHandler;
    private final Executor mHandlerExecutor;
    final MediaRouter2 mMediaRouter2;
    private final MediaRouter2.RouteCallback mRouteCallback;
    private Map<String, String> mRouteIdToOriginalRouteIdMap;
    private List<MediaRoute2Info> mRoutes;
    private final MediaRouter2.TransferCallback mTransferCallback;

    /* loaded from: classes.dex */
    static abstract class Callback {
        public abstract void onReleaseController(MediaRouteProvider.RouteController routeController);

        public abstract void onSelectFallbackRoute(int i);

        public abstract void onSelectRoute(String str, int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaRoute2Provider(Context context, Callback callback) {
        super(context);
        this.mControllerMap = new ArrayMap();
        this.mRouteCallback = new RouteCallback();
        this.mTransferCallback = new TransferCallback();
        this.mControllerCallback = new ControllerCallback();
        this.mRoutes = new ArrayList();
        this.mRouteIdToOriginalRouteIdMap = new ArrayMap();
        this.mMediaRouter2 = MediaRouter2.getInstance(context);
        this.mCallback = callback;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        Objects.requireNonNull(handler);
        this.mHandlerExecutor = new MediaRoute2Provider$$ExternalSyntheticLambda0(handler);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (MediaRouter.getGlobalCallbackCount() > 0) {
            this.mMediaRouter2.registerRouteCallback(this.mHandlerExecutor, this.mRouteCallback, MediaRouter2Utils.toDiscoveryPreference(updateDiscoveryRequest(mediaRouteDiscoveryRequest, MediaRouter.isTransferToLocalEnabled())));
            this.mMediaRouter2.registerTransferCallback(this.mHandlerExecutor, this.mTransferCallback);
            this.mMediaRouter2.registerControllerCallback(this.mHandlerExecutor, this.mControllerCallback);
            return;
        }
        this.mMediaRouter2.unregisterRouteCallback(this.mRouteCallback);
        this.mMediaRouter2.unregisterTransferCallback(this.mTransferCallback);
        this.mMediaRouter2.unregisterControllerCallback(this.mControllerCallback);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String str) {
        return new MemberRouteController(this.mRouteIdToOriginalRouteIdMap.get(str), null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.RouteController onCreateRouteController(String str, String str2) {
        String str3 = this.mRouteIdToOriginalRouteIdMap.get(str);
        for (GroupRouteController groupRouteController : this.mControllerMap.values()) {
            if (TextUtils.equals(str2, groupRouteController.getGroupRouteId())) {
                return new MemberRouteController(str3, groupRouteController);
            }
        }
        Log.w("MR2Provider", "Could not find the matching GroupRouteController. routeId=" + str + ", routeGroupId=" + str2);
        return new MemberRouteController(str3, null);
    }

    @Override // androidx.mediarouter.media.MediaRouteProvider
    public MediaRouteProvider.DynamicGroupRouteController onCreateDynamicGroupRouteController(String str) {
        for (Map.Entry<MediaRouter2.RoutingController, GroupRouteController> entry : this.mControllerMap.entrySet()) {
            GroupRouteController value = entry.getValue();
            if (TextUtils.equals(str, value.mInitialMemberRouteId)) {
                return value;
            }
        }
        return null;
    }

    public void transferTo(String str) {
        MediaRoute2Info routeById = getRouteById(str);
        if (routeById == null) {
            Log.w("MR2Provider", "transferTo: Specified route not found. routeId=" + str);
            return;
        }
        this.mMediaRouter2.transferTo(routeById);
    }

    protected void refreshRoutes() {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        for (MediaRoute2Info mediaRoute2Info : this.mMediaRouter2.getRoutes()) {
            if (mediaRoute2Info != null && !arraySet.contains(mediaRoute2Info) && !mediaRoute2Info.isSystemRoute()) {
                arraySet.add(mediaRoute2Info);
                arrayList.add(mediaRoute2Info);
            }
        }
        if (arrayList.equals(this.mRoutes)) {
            return;
        }
        this.mRoutes = arrayList;
        this.mRouteIdToOriginalRouteIdMap.clear();
        for (MediaRoute2Info mediaRoute2Info2 : this.mRoutes) {
            Bundle extras = mediaRoute2Info2.getExtras();
            if (extras == null || extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID") == null) {
                Log.w("MR2Provider", "Cannot find the original route Id. route=" + mediaRoute2Info2);
            } else {
                this.mRouteIdToOriginalRouteIdMap.put(mediaRoute2Info2.getId(), extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (MediaRoute2Info mediaRoute2Info3 : this.mRoutes) {
            MediaRouteDescriptor mediaRouteDescriptor = MediaRouter2Utils.toMediaRouteDescriptor(mediaRoute2Info3);
            if (mediaRoute2Info3 != null) {
                arrayList2.add(mediaRouteDescriptor);
            }
        }
        setDescriptor(new MediaRouteProviderDescriptor.Builder().setSupportsDynamicGroupRoute(true).addRoutes(arrayList2).build());
    }

    MediaRoute2Info getRouteById(String str) {
        if (str == null) {
            return null;
        }
        for (MediaRoute2Info mediaRoute2Info : this.mRoutes) {
            if (TextUtils.equals(mediaRoute2Info.getId(), str)) {
                return mediaRoute2Info;
            }
        }
        return null;
    }

    static Messenger getMessengerFromRoutingController(MediaRouter2.RoutingController routingController) {
        Bundle controlHints;
        if (routingController == null || (controlHints = routingController.getControlHints()) == null) {
            return null;
        }
        return (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSessionIdForRouteController(MediaRouteProvider.RouteController routeController) {
        MediaRouter2.RoutingController routingController;
        if ((routeController instanceof GroupRouteController) && (routingController = ((GroupRouteController) routeController).mRoutingController) != null) {
            return routingController.getId();
        }
        return null;
    }

    void setDynamicRouteDescriptors(MediaRouter2.RoutingController routingController) {
        GroupRouteController groupRouteController = this.mControllerMap.get(routingController);
        if (groupRouteController == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No matching routeController found. routingController=" + routingController);
            return;
        }
        List<MediaRoute2Info> selectedRoutes = routingController.getSelectedRoutes();
        if (selectedRoutes.isEmpty()) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No selected routes. This may happen when the selected routes become invalid.routingController=" + routingController);
            return;
        }
        List<String> routeIds = MediaRouter2Utils.getRouteIds(selectedRoutes);
        MediaRouteDescriptor mediaRouteDescriptor = MediaRouter2Utils.toMediaRouteDescriptor(selectedRoutes.get(0));
        MediaRouteDescriptor mediaRouteDescriptor2 = null;
        Bundle controlHints = routingController.getControlHints();
        String string = getContext().getString(R$string.mr_dialog_default_group_name);
        if (controlHints != null) {
            try {
                String string2 = controlHints.getString("androidx.mediarouter.media.KEY_SESSION_NAME");
                if (!TextUtils.isEmpty(string2)) {
                    string = string2;
                }
                Bundle bundle = controlHints.getBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE");
                if (bundle != null) {
                    mediaRouteDescriptor2 = MediaRouteDescriptor.fromBundle(bundle);
                }
            } catch (Exception e) {
                Log.w("MR2Provider", "Exception while unparceling control hints.", e);
            }
        }
        if (mediaRouteDescriptor2 == null) {
            mediaRouteDescriptor2 = new MediaRouteDescriptor.Builder(routingController.getId(), string).setConnectionState(2).setPlaybackType(1).setVolume(routingController.getVolume()).setVolumeMax(routingController.getVolumeMax()).setVolumeHandling(routingController.getVolumeHandling()).addControlFilters(mediaRouteDescriptor.getControlFilters()).addGroupMemberIds(routeIds).build();
        }
        List<String> routeIds2 = MediaRouter2Utils.getRouteIds(routingController.getSelectableRoutes());
        List<String> routeIds3 = MediaRouter2Utils.getRouteIds(routingController.getDeselectableRoutes());
        MediaRouteProviderDescriptor descriptor = getDescriptor();
        if (descriptor == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: providerDescriptor is not set.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<MediaRouteDescriptor> routes = descriptor.getRoutes();
        if (!routes.isEmpty()) {
            for (MediaRouteDescriptor mediaRouteDescriptor3 : routes) {
                String id = mediaRouteDescriptor3.getId();
                arrayList.add(new MediaRouteProvider.DynamicGroupRouteController.DynamicRouteDescriptor.Builder(mediaRouteDescriptor3).setSelectionState(routeIds.contains(id) ? 3 : 1).setIsGroupable(routeIds2.contains(id)).setIsUnselectable(routeIds3.contains(id)).setIsTransferable(true).build());
            }
        }
        groupRouteController.setGroupRouteDescriptor(mediaRouteDescriptor2);
        groupRouteController.notifyDynamicRoutesChanged(mediaRouteDescriptor2, arrayList);
    }

    private MediaRouteDiscoveryRequest updateDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest, boolean z) {
        if (mediaRouteDiscoveryRequest == null) {
            mediaRouteDiscoveryRequest = new MediaRouteDiscoveryRequest(MediaRouteSelector.EMPTY, false);
        }
        List<String> controlCategories = mediaRouteDiscoveryRequest.getSelector().getControlCategories();
        if (z) {
            if (!controlCategories.contains("android.media.intent.category.LIVE_AUDIO")) {
                controlCategories.add("android.media.intent.category.LIVE_AUDIO");
            }
        } else {
            controlCategories.remove("android.media.intent.category.LIVE_AUDIO");
        }
        return new MediaRouteDiscoveryRequest(new MediaRouteSelector.Builder().addControlCategories(controlCategories).build(), mediaRouteDiscoveryRequest.isActiveScan());
    }

    /* loaded from: classes.dex */
    private class RouteCallback extends MediaRouter2.RouteCallback {
        RouteCallback() {
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesAdded(List<MediaRoute2Info> list) {
            MediaRoute2Provider.this.refreshRoutes();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesRemoved(List<MediaRoute2Info> list) {
            MediaRoute2Provider.this.refreshRoutes();
        }

        @Override // android.media.MediaRouter2.RouteCallback
        public void onRoutesChanged(List<MediaRoute2Info> list) {
            MediaRoute2Provider.this.refreshRoutes();
        }
    }

    /* loaded from: classes.dex */
    private class TransferCallback extends MediaRouter2.TransferCallback {
        TransferCallback() {
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
            MediaRoute2Provider.this.mControllerMap.remove(routingController);
            if (routingController2 == MediaRoute2Provider.this.mMediaRouter2.getSystemController()) {
                MediaRoute2Provider.this.mCallback.onSelectFallbackRoute(3);
                return;
            }
            List<MediaRoute2Info> selectedRoutes = routingController2.getSelectedRoutes();
            if (selectedRoutes.isEmpty()) {
                Log.w("MR2Provider", "Selected routes are empty. This shouldn't happen.");
                return;
            }
            String id = selectedRoutes.get(0).getId();
            MediaRoute2Provider.this.mControllerMap.put(routingController2, new GroupRouteController(routingController2, id));
            MediaRoute2Provider.this.mCallback.onSelectRoute(id, 3);
            MediaRoute2Provider.this.setDynamicRouteDescriptors(routingController2);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
            Log.w("MR2Provider", "Transfer failed. requestedRoute=" + mediaRoute2Info);
        }

        @Override // android.media.MediaRouter2.TransferCallback
        public void onStop(MediaRouter2.RoutingController routingController) {
            GroupRouteController remove = MediaRoute2Provider.this.mControllerMap.remove(routingController);
            if (remove != null) {
                MediaRoute2Provider.this.mCallback.onReleaseController(remove);
                return;
            }
            Log.w("MR2Provider", "onStop: No matching routeController found. routingController=" + routingController);
        }
    }

    /* loaded from: classes.dex */
    private class ControllerCallback extends MediaRouter2.ControllerCallback {
        ControllerCallback() {
        }

        @Override // android.media.MediaRouter2.ControllerCallback
        public void onControllerUpdated(MediaRouter2.RoutingController routingController) {
            MediaRoute2Provider.this.setDynamicRouteDescriptors(routingController);
        }
    }

    /* loaded from: classes.dex */
    private class MemberRouteController extends MediaRouteProvider.RouteController {
        final GroupRouteController mGroupRouteController;
        final String mOriginalRouteId;

        MemberRouteController(String str, GroupRouteController groupRouteController) {
            this.mOriginalRouteId = str;
            this.mGroupRouteController = groupRouteController;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i) {
            GroupRouteController groupRouteController;
            String str = this.mOriginalRouteId;
            if (str == null || (groupRouteController = this.mGroupRouteController) == null) {
                return;
            }
            groupRouteController.setMemberRouteVolume(str, i);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i) {
            GroupRouteController groupRouteController;
            String str = this.mOriginalRouteId;
            if (str == null || (groupRouteController = this.mGroupRouteController) == null) {
                return;
            }
            groupRouteController.updateMemberRouteVolume(str, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class GroupRouteController extends MediaRouteProvider.DynamicGroupRouteController {
        final Handler mControllerHandler;
        MediaRouteDescriptor mGroupRouteDescriptor;
        final String mInitialMemberRouteId;
        final Messenger mReceiveMessenger;
        final MediaRouter2.RoutingController mRoutingController;
        final Messenger mServiceMessenger;
        final SparseArray<MediaRouter.ControlRequestCallback> mPendingCallbacks = new SparseArray<>();
        AtomicInteger mNextRequestId = new AtomicInteger(1);
        private final Runnable mClearOptimisticVolumeRunnable = new Runnable() { // from class: androidx.mediarouter.media.MediaRoute2Provider$GroupRouteController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MediaRoute2Provider.GroupRouteController.this.lambda$new$0();
            }
        };
        int mOptimisticVolume = -1;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0() {
            this.mOptimisticVolume = -1;
        }

        GroupRouteController(MediaRouter2.RoutingController routingController, String str) {
            this.mRoutingController = routingController;
            this.mInitialMemberRouteId = str;
            Messenger messengerFromRoutingController = MediaRoute2Provider.getMessengerFromRoutingController(routingController);
            this.mServiceMessenger = messengerFromRoutingController;
            this.mReceiveMessenger = messengerFromRoutingController == null ? null : new Messenger(new ReceiveHandler());
            this.mControllerHandler = new Handler(Looper.getMainLooper());
        }

        public String getGroupRouteId() {
            MediaRouteDescriptor mediaRouteDescriptor = this.mGroupRouteDescriptor;
            return mediaRouteDescriptor != null ? mediaRouteDescriptor.getId() : this.mRoutingController.getId();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onSetVolume(int i) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null) {
                return;
            }
            routingController.setVolume(i);
            this.mOptimisticVolume = i;
            scheduleClearOptimisticVolume();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onUpdateVolume(int i) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null) {
                return;
            }
            int i2 = this.mOptimisticVolume;
            if (i2 < 0) {
                i2 = routingController.getVolume();
            }
            int max = Math.max(0, Math.min(i2 + i, this.mRoutingController.getVolumeMax()));
            this.mOptimisticVolume = max;
            this.mRoutingController.setVolume(max);
            scheduleClearOptimisticVolume();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.RouteController
        public void onRelease() {
            this.mRoutingController.release();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onUpdateMemberRoutes(List<String> list) {
            if (list == null || list.isEmpty()) {
                Log.w("MR2Provider", "onUpdateMemberRoutes: Ignoring null or empty routeIds.");
                return;
            }
            String str = list.get(0);
            MediaRoute2Info routeById = MediaRoute2Provider.this.getRouteById(str);
            if (routeById == null) {
                Log.w("MR2Provider", "onUpdateMemberRoutes: Specified route not found. routeId=" + str);
                return;
            }
            MediaRoute2Provider.this.mMediaRouter2.transferTo(routeById);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onAddMemberRoute(String str) {
            if (str == null || str.isEmpty()) {
                Log.w("MR2Provider", "onAddMemberRoute: Ignoring null or empty routeId.");
                return;
            }
            MediaRoute2Info routeById = MediaRoute2Provider.this.getRouteById(str);
            if (routeById == null) {
                Log.w("MR2Provider", "onAddMemberRoute: Specified route not found. routeId=" + str);
                return;
            }
            this.mRoutingController.selectRoute(routeById);
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider.DynamicGroupRouteController
        public void onRemoveMemberRoute(String str) {
            if (str == null || str.isEmpty()) {
                Log.w("MR2Provider", "onRemoveMemberRoute: Ignoring null or empty routeId.");
                return;
            }
            MediaRoute2Info routeById = MediaRoute2Provider.this.getRouteById(str);
            if (routeById == null) {
                Log.w("MR2Provider", "onRemoveMemberRoute: Specified route not found. routeId=" + str);
                return;
            }
            this.mRoutingController.deselectRoute(routeById);
        }

        private void scheduleClearOptimisticVolume() {
            this.mControllerHandler.removeCallbacks(this.mClearOptimisticVolumeRunnable);
            this.mControllerHandler.postDelayed(this.mClearOptimisticVolumeRunnable, 1000L);
        }

        void setMemberRouteVolume(String str, int i) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null || routingController.isReleased() || this.mServiceMessenger == null) {
                return;
            }
            int andIncrement = this.mNextRequestId.getAndIncrement();
            Message obtain = Message.obtain();
            obtain.what = 7;
            obtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            obtain.setData(bundle);
            obtain.replyTo = this.mReceiveMessenger;
            try {
                this.mServiceMessenger.send(obtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }

        void updateMemberRouteVolume(String str, int i) {
            MediaRouter2.RoutingController routingController = this.mRoutingController;
            if (routingController == null || routingController.isReleased() || this.mServiceMessenger == null) {
                return;
            }
            int andIncrement = this.mNextRequestId.getAndIncrement();
            Message obtain = Message.obtain();
            obtain.what = 8;
            obtain.arg1 = andIncrement;
            Bundle bundle = new Bundle();
            bundle.putInt("volume", i);
            bundle.putString("routeId", str);
            obtain.setData(bundle);
            obtain.replyTo = this.mReceiveMessenger;
            try {
                this.mServiceMessenger.send(obtain);
            } catch (DeadObjectException unused) {
            } catch (RemoteException e) {
                Log.e("MR2Provider", "Could not send control request to service.", e);
            }
        }

        void setGroupRouteDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            this.mGroupRouteDescriptor = mediaRouteDescriptor;
        }

        /* loaded from: classes.dex */
        class ReceiveHandler extends Handler {
            ReceiveHandler() {
                super(Looper.getMainLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                int i2 = message.arg1;
                Object obj = message.obj;
                Bundle peekData = message.peekData();
                MediaRouter.ControlRequestCallback controlRequestCallback = GroupRouteController.this.mPendingCallbacks.get(i2);
                if (controlRequestCallback == null) {
                    Log.w("MR2Provider", "Pending callback not found for control request.");
                    return;
                }
                GroupRouteController.this.mPendingCallbacks.remove(i2);
                if (i == 3) {
                    controlRequestCallback.onResult((Bundle) obj);
                } else if (i != 4) {
                } else {
                    controlRequestCallback.onError(peekData == null ? null : peekData.getString("error"), (Bundle) obj);
                }
            }
        }
    }
}
