package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class MediaRouteSelector {
    public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), null);
    private final Bundle mBundle;
    List<String> mControlCategories;

    MediaRouteSelector(Bundle bundle, List<String> list) {
        this.mBundle = bundle;
        this.mControlCategories = list;
    }

    public List<String> getControlCategories() {
        ensureControlCategories();
        return new ArrayList(this.mControlCategories);
    }

    void ensureControlCategories() {
        if (this.mControlCategories == null) {
            ArrayList<String> stringArrayList = this.mBundle.getStringArrayList("controlCategories");
            this.mControlCategories = stringArrayList;
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                this.mControlCategories = Collections.emptyList();
            }
        }
    }

    public boolean matchesControlFilters(List<IntentFilter> list) {
        if (list == null) {
            return false;
        }
        ensureControlCategories();
        if (this.mControlCategories.isEmpty()) {
            return false;
        }
        for (IntentFilter intentFilter : list) {
            if (intentFilter != null) {
                for (String str : this.mControlCategories) {
                    if (intentFilter.hasCategory(str)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public boolean contains(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            return false;
        }
        ensureControlCategories();
        mediaRouteSelector.ensureControlCategories();
        return this.mControlCategories.containsAll(mediaRouteSelector.mControlCategories);
    }

    public boolean isEmpty() {
        ensureControlCategories();
        return this.mControlCategories.isEmpty();
    }

    public boolean isValid() {
        ensureControlCategories();
        return !this.mControlCategories.contains(null);
    }

    public boolean equals(Object obj) {
        if (obj instanceof MediaRouteSelector) {
            MediaRouteSelector mediaRouteSelector = (MediaRouteSelector) obj;
            ensureControlCategories();
            mediaRouteSelector.ensureControlCategories();
            return this.mControlCategories.equals(mediaRouteSelector.mControlCategories);
        }
        return false;
    }

    public int hashCode() {
        ensureControlCategories();
        return this.mControlCategories.hashCode();
    }

    public String toString() {
        return "MediaRouteSelector{ controlCategories=" + Arrays.toString(getControlCategories().toArray()) + " }";
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public static MediaRouteSelector fromBundle(Bundle bundle) {
        if (bundle != null) {
            return new MediaRouteSelector(bundle, null);
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private ArrayList<String> mControlCategories;

        public Builder() {
        }

        public Builder(MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            mediaRouteSelector.ensureControlCategories();
            if (mediaRouteSelector.mControlCategories.isEmpty()) {
                return;
            }
            this.mControlCategories = new ArrayList<>(mediaRouteSelector.mControlCategories);
        }

        public Builder addControlCategory(String str) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            if (this.mControlCategories == null) {
                this.mControlCategories = new ArrayList<>();
            }
            if (!this.mControlCategories.contains(str)) {
                this.mControlCategories.add(str);
            }
            return this;
        }

        public Builder addControlCategories(Collection<String> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("categories must not be null");
            }
            if (!collection.isEmpty()) {
                for (String str : collection) {
                    addControlCategory(str);
                }
            }
            return this;
        }

        public Builder addSelector(MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            addControlCategories(mediaRouteSelector.getControlCategories());
            return this;
        }

        public MediaRouteSelector build() {
            if (this.mControlCategories == null) {
                return MediaRouteSelector.EMPTY;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", this.mControlCategories);
            return new MediaRouteSelector(bundle, this.mControlCategories);
        }
    }
}
