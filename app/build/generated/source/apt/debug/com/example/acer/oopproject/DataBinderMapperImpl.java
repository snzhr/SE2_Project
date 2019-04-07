package com.example.acer.oopproject;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.example.acer.oopproject.databinding.ActivityLoginBindingImpl;
import com.example.acer.oopproject.databinding.ActivityReservationInfoBindingImpl;
import com.example.acer.oopproject.databinding.ActivityReservationInfoBindingLandImpl;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYRESERVATIONINFO = 1;

  private static final int LAYOUT_ACTIVITYLOGIN = 2;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(2);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.acer.oopproject.R.layout.activity_reservation_info, LAYOUT_ACTIVITYRESERVATIONINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.acer.oopproject.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYRESERVATIONINFO: {
          if ("layout-land/activity_reservation_info_0".equals(tag)) {
            return new ActivityReservationInfoBindingLandImpl(component, view);
          }
          if ("layout/activity_reservation_info_0".equals(tag)) {
            return new ActivityReservationInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_reservation_info is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    final int code = tag.hashCode();
    switch(code) {
      case 1304684545: {
        if(tag.equals("layout-land/activity_reservation_info_0")) {
          return R.layout.activity_reservation_info;
        }
        break;
      }
      case 1039887997: {
        if(tag.equals("layout/activity_reservation_info_0")) {
          return R.layout.activity_reservation_info;
        }
        break;
      }
      case -237232145: {
        if(tag.equals("layout/activity_login_0")) {
          return R.layout.activity_login;
        }
        break;
      }
    }
    return 0;
  }

  @Override
  public String convertBrIdToString(int id) {
    String tmpVal = InnerBrLookup.sKeys.get(id);
    return tmpVal;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(com.example.acer.oopproject.BR._all, "_all");
      sKeys.put(com.example.acer.oopproject.BR.movie, "movie");
      sKeys.put(com.example.acer.oopproject.BR.eventHandler, "eventHandler");
    }
  }
}
