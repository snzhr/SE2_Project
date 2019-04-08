package com.example.acer.oopproject.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.mirlan.oop_cinema.ActivityReservationInfo;
import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.helpers.TextViewCustom;

public abstract class ActivityReservationInfoBinding extends ViewDataBinding {
  @NonNull
  public final TextViewCustom aboutMovie;

  @NonNull
  public final Button confirm;

  @NonNull
  public final ImageView poster;

  @NonNull
  public final TextViewCustom tempUserInfo;

  @NonNull
  public final TextViewCustom tvReservationAmt;

  @NonNull
  public final TextViewCustom tvSeats;

  @NonNull
  public final TextViewCustom userCash;

  @NonNull
  public final TextViewCustom username;

  @Bindable
  protected ActivityReservationInfo mEventHandler;

  @Bindable
  protected Movie mMovie;

  protected ActivityReservationInfoBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextViewCustom aboutMovie, Button confirm, ImageView poster,
      TextViewCustom tempUserInfo, TextViewCustom tvReservationAmt, TextViewCustom tvSeats,
      TextViewCustom userCash, TextViewCustom username) {
    super(_bindingComponent, _root, _localFieldCount);
    this.aboutMovie = aboutMovie;
    this.confirm = confirm;
    this.poster = poster;
    this.tempUserInfo = tempUserInfo;
    this.tvReservationAmt = tvReservationAmt;
    this.tvSeats = tvSeats;
    this.userCash = userCash;
    this.username = username;
  }

  public abstract void setEventHandler(@Nullable ActivityReservationInfo eventHandler);

  @Nullable
  public ActivityReservationInfo getEventHandler() {
    return mEventHandler;
  }

  public abstract void setMovie(@Nullable Movie movie);

  @Nullable
  public Movie getMovie() {
    return mMovie;
  }

  @NonNull
  public static ActivityReservationInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityReservationInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityReservationInfoBinding>inflate(inflater, com.example.acer.oopproject.R.layout.activity_reservation_info, root, attachToRoot, component);
  }

  @Nullable
  public static ActivityReservationInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityReservationInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityReservationInfoBinding>inflate(inflater, com.example.acer.oopproject.R.layout.activity_reservation_info, null, false, component);
  }

  @NonNull
  public static ActivityReservationInfoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityReservationInfoBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityReservationInfoBinding)bind(component, view, com.example.acer.oopproject.R.layout.activity_reservation_info);
  }
}
