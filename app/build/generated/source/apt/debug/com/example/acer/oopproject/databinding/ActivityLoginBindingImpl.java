package com.example.acer.oopproject.databinding;
import com.example.acer.oopproject.R;
import com.example.acer.oopproject.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBindingImpl extends ActivityLoginBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.container, 6);
        sViewsWithIds.put(R.id.cardSignIn, 7);
        sViewsWithIds.put(R.id.etUsername, 8);
        sViewsWithIds.put(R.id.etPassword, 9);
        sViewsWithIds.put(R.id.cardSignUp, 10);
        sViewsWithIds.put(R.id.cardContent, 11);
        sViewsWithIds.put(R.id.temp1, 12);
        sViewsWithIds.put(R.id.temp2, 13);
        sViewsWithIds.put(R.id.etUsernameReg, 14);
        sViewsWithIds.put(R.id.temp3, 15);
        sViewsWithIds.put(R.id.etPasswordReg, 16);
        sViewsWithIds.put(R.id.temp4, 17);
        sViewsWithIds.put(R.id.etConfirmReg, 18);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mEventHandlerOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ActivityLoginBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivityLoginBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.RelativeLayout) bindings[11]
            , (android.support.v7.widget.CardView) bindings[7]
            , (android.support.v7.widget.CardView) bindings[10]
            , (android.widget.ImageView) bindings[3]
            , (android.support.design.widget.CoordinatorLayout) bindings[6]
            , (android.support.design.widget.FloatingActionButton) bindings[5]
            , (com.example.mirlan.oop_cinema.helpers.EditTextCustom) bindings[18]
            , (com.example.mirlan.oop_cinema.helpers.EditTextCustom) bindings[9]
            , (com.example.mirlan.oop_cinema.helpers.EditTextCustom) bindings[16]
            , (com.example.mirlan.oop_cinema.helpers.EditTextCustom) bindings[8]
            , (com.example.mirlan.oop_cinema.helpers.EditTextCustom) bindings[14]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[2]
            , (com.example.mirlan.oop_cinema.helpers.ButtonCustom) bindings[1]
            , (com.example.mirlan.oop_cinema.helpers.ButtonCustom) bindings[4]
            , (com.example.mirlan.oop_cinema.helpers.TextViewCustom) bindings[12]
            , (com.example.mirlan.oop_cinema.helpers.TextInputLayoutCustom) bindings[13]
            , (com.example.mirlan.oop_cinema.helpers.TextInputLayoutCustom) bindings[15]
            , (com.example.mirlan.oop_cinema.helpers.TextInputLayoutCustom) bindings[17]
            );
        this.close.setTag(null);
        this.create.setTag(null);
        this.forgotPassword.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.signIn.setTag(null);
        this.signUp.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            setEventHandler((com.example.mirlan.oop_cinema.ActivitySignIn) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEventHandler(@Nullable com.example.mirlan.oop_cinema.ActivitySignIn EventHandler) {
        this.mEventHandler = EventHandler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.eventHandler);
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
        com.example.mirlan.oop_cinema.ActivitySignIn eventHandler = mEventHandler;
        android.view.View.OnClickListener eventHandlerOnClickAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (eventHandler != null) {
                    // read eventHandler::onClick
                    eventHandlerOnClickAndroidViewViewOnClickListener = (((mEventHandlerOnClickAndroidViewViewOnClickListener == null) ? (mEventHandlerOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mEventHandlerOnClickAndroidViewViewOnClickListener).setValue(eventHandler));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.close.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
            this.create.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
            this.forgotPassword.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
            this.signIn.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
            this.signUp.setOnClickListener(eventHandlerOnClickAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.example.mirlan.oop_cinema.ActivitySignIn value;
        public OnClickListenerImpl setValue(com.example.mirlan.oop_cinema.ActivitySignIn value) {
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
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}