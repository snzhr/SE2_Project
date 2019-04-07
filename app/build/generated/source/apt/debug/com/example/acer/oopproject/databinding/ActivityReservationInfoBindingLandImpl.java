package com.example.acer.oopproject.databinding;
import com.example.acer.oopproject.R;
import com.example.acer.oopproject.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityReservationInfoBindingLandImpl extends ActivityReservationInfoBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tempUserInfo, 6);
        sViewsWithIds.put(R.id.username, 7);
        sViewsWithIds.put(R.id.userCash, 8);
        sViewsWithIds.put(R.id.poster, 9);
        sViewsWithIds.put(R.id.tvSeats, 10);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    private final com.example.mirlan.oop_cinema.helpers.TextViewCustom mboundView3;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mEventHandlerOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ActivityReservationInfoBindingLandImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ActivityReservationInfoBindingLandImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[4]
            , (android.widget.Button) bindings[5]
            , (android.widget.ImageView) bindings[9]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[6]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[2]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[10]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[8]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[7]
            );
        this.aboutMovie.setTag(null);
        this.confirm.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView3 = (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[3];
        this.mboundView3.setTag(null);
        this.tvReservationAmt.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.eventHandler == variableId) {
            setEventHandler((com.example.mirlan.oop_cinema.ActivityReservationInfo) variable);
        }
        else if (BR.movie == variableId) {
            setMovie((com.example.mirlan.oop_cinema.helpers.Movie) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEventHandler(@Nullable com.example.mirlan.oop_cinema.ActivityReservationInfo EventHandler) {
        this.mEventHandler = EventHandler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.eventHandler);
        super.requestRebind();
    }
    public void setMovie(@Nullable com.example.mirlan.oop_cinema.helpers.Movie Movie) {
        this.mMovie = Movie;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.movie);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.example.mirlan.oop_cinema.ActivityReservationInfo eventHandler = mEventHandler;
        com.example.mirlan.oop_cinema.helpers.Movie movie = mMovie;
        java.lang.String movieGetName = null;
        java.lang.String movieGetDescription = null;
        android.view.View.OnClickListener eventHandlerOnClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (eventHandler != null) {
                    // read eventHandler::onClick
                    eventHandlerOnClickAndroidViewViewOnClickListener = (((mEventHandlerOnClickAndroidViewViewOnClickListener == null) ? (mEventHandlerOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mEventHandlerOnClickAndroidViewViewOnClickListener).setValue(eventHandler));
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {



                if (movie != null) {
                    // read movie.getName
                    movieGetName = movie.getName();
                    // read movie.getDescription
                    movieGetDescription = movie.getDescription();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.aboutMovie.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
            this.confirm.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, movieGetDescription);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvReservationAmt, movieGetName);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.example.mirlan.oop_cinema.ActivityReservationInfo value;
        public OnClickListenerImpl setValue(com.example.mirlan.oop_cinema.ActivityReservationInfo value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): eventHandler
        flag 1 (0x2L): movie
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}