package com.example.acer.oopproject.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.mirlan.oop_cinema.ActivitySignIn;
import com.example.mirlan.oop_cinema.helpers.ButtonCustom;
import com.example.mirlan.oop_cinema.helpers.EditTextCustom;
import com.example.mirlan.oop_cinema.helpers.TextInputLayoutCustom;
import com.example.mirlan.oop_cinema.helpers.TextViewCustom;

public abstract class ActivityLoginBinding extends ViewDataBinding {
  @NonNull
  public final RelativeLayout cardContent;

  @NonNull
  public final CardView cardSignIn;

  @NonNull
  public final CardView cardSignUp;

  @NonNull
  public final ImageView close;

  @NonNull
  public final CoordinatorLayout container;

  @NonNull
  public final FloatingActionButton create;

  @NonNull
  public final EditTextCustom etConfirmReg;

  @NonNull
  public final EditTextCustom etPassword;

  @NonNull
  public final EditTextCustom etPasswordReg;

  @NonNull
  public final EditTextCustom etUsername;

  @NonNull
  public final EditTextCustom etUsernameReg;

  @NonNull
  public final TextViewCustom forgotPassword;

  @NonNull
  public final ButtonCustom signIn;

  @NonNull
  public final ButtonCustom signUp;

  @NonNull
  public final TextViewCustom temp1;

  @NonNull
  public final TextInputLayoutCustom temp2;

  @NonNull
  public final TextInputLayoutCustom temp3;

  @NonNull
  public final TextInputLayoutCustom temp4;

  @Bindable
  protected ActivitySignIn mEventHandler;

  protected ActivityLoginBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RelativeLayout cardContent, CardView cardSignIn, CardView cardSignUp,
      ImageView close, CoordinatorLayout container, FloatingActionButton create,
      EditTextCustom etConfirmReg, EditTextCustom etPassword, EditTextCustom etPasswordReg,
      EditTextCustom etUsername, EditTextCustom etUsernameReg, TextViewCustom forgotPassword,
      ButtonCustom signIn, ButtonCustom signUp, TextViewCustom temp1, TextInputLayoutCustom temp2,
      TextInputLayoutCustom temp3, TextInputLayoutCustom temp4) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardContent = cardContent;
    this.cardSignIn = cardSignIn;
    this.cardSignUp = cardSignUp;
    this.close = close;
    this.container = container;
    this.create = create;
    this.etConfirmReg = etConfirmReg;
    this.etPassword = etPassword;
    this.etPasswordReg = etPasswordReg;
    this.etUsername = etUsername;
    this.etUsernameReg = etUsernameReg;
    this.forgotPassword = forgotPassword;
    this.signIn = signIn;
    this.signUp = signUp;
    this.temp1 = temp1;
    this.temp2 = temp2;
    this.temp3 = temp3;
    this.temp4 = temp4;
  }

  public abstract void setEventHandler(@Nullable ActivitySignIn eventHandler);

  @Nullable
  public ActivitySignIn getEventHandler() {
    return mEventHandler;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.example.acer.oopproject.R.layout.activity_login, root, attachToRoot, component);
  }

  @Nullable
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.example.acer.oopproject.R.layout.activity_login, null, false, component);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityLoginBinding)bind(component, view, com.example.acer.oopproject.R.layout.activity_login);
  }
}
