import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ldt.cinematicket.R;
import com.ldt.cinematicket.model.Cinema;
import com.ldt.cinematicket.ui.widget.SuccessTickView;
import com.ldt.cinematicket.ui.widget.fragmentnavigationcontroller.SupportFragment;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddNewCinema extends SupportFragment implements RequestListener<Drawable>, OnFailureListener, OnCompleteListener<QuerySnapshot> {
    private static final String TAG = "AddNewCinema";

    public static AddNewCinema newInstance() {
        return new AddNewCinema();
    }

    FirebaseFirestore mDb;

    @BindView(R.id.back_button)
    ImageView mBackButton;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.photo)
    ImageView mPhoto;
    @BindView(R.id.photo_empty_icon)
    ImageView mEmptyPhotoIcon;

    @BindView(R.id.option_panel)
    ConstraintLayout mOptionPanel;

    @BindView(R.id.click_to_expand_option)
    ImageView mClickToExpandOption;
    @BindView(R.id.photo_loader)
    MKLoader mPhotoLoader;

    @BindView(R.id.id)
    EditText mIDEditText;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.done_button)
    ImageView mDoneButton;

    @BindView(R.id.ListOfMovies)
    EditText mListOfMovies;
    @BindView(R.id.ListOfShowTimes)
    EditText mListOfShowTimes;
    @BindView(R.id.hotline)
    EditText mHotline;
    @BindView(R.id.address)
    EditText mAddress;

    @OnClick(R.id.back_button)
    void back() {
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

        dialog.setContentView(R.layout.alert_layout);
        dialog.findViewById(R.id.comfirm).setOnClickListener(v -> {
            dialog.dismiss();
            getMainActivity().dismiss();
        });
        dialog.show();

    }

    @OnClick(R.id.click_to_expand_option)
    void ExpandOrCollapseOptionPanel() {
        if (mOptionPanel.getVisibility() == View.VISIBLE) {
            mClickToExpandOption.setRotation((float) Math.PI);
            RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setFillAfter(true);
            ra.setDuration(350);
            mClickToExpandOption.startAnimation(ra);
            mOptionPanel.setVisibility(View.GONE);
        } else {
            //   mClickToExpandOption.setRotation((float) Math.PI);
            RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setFillAfter(true);
            ra.setDuration(350);
            mClickToExpandOption.startAnimation(ra);
            mOptionPanel.setVisibility(View.VISIBLE);
        }
    }
@OnClick(R.id.photo)
    void focusImageUrlEditText() {
        mImageUrlEditText.requestFocus();
    }

    private boolean[] mDone = new boolean[]{false, false, false, false};

    @BindView(R.id.image_url)
    EditText mImageUrlEditText;
    @BindView(R.id.name)
    EditText mName;

    @OnTextChanged(R.id.name)
    void onNameChanged(CharSequence s, int start, int before, int count) {
        if (mDone[1] && s.length() == 0) mDone[1] = false;
        else if (!mDone[1] && s.length() != 0) mDone[1] = true;
        checkoutDone();
    }