package com.example.mirlan.oop_cinema;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.acer.oopproject.R;
import com.example.acer.oopproject.databinding.ActivityLoginBinding;
import com.example.mirlan.oop_cinema.helpers.Const;
import com.example.mirlan.oop_cinema.helpers.Utils;
import com.example.mirlan.oop_cinema.models.Database;
import com.example.mirlan.oop_cinema.models.User;

/**
 * Created by Mirlan on 06.12.2016.
 */

public class ActivitySignIn extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private int fabX0, fabY0, containerX, containerY, cardX, cardY, cardTranslateY;
    private float maxRadius;
    private boolean startReservation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setEventHandler(this);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark2));

        binding.container.post(new Runnable() {
            @Override
            public void run() {
                defineDimensions();
            }
        });

        startReservation = getIntent().getBooleanExtra(Const.STARTRESACTIVITY, false);
        if (startReservation)
            Toast.makeText(this, "Sign in first", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.create) {
            enable(!binding.signIn.isEnabled());
            openRevealEffect();
        } else if (id == R.id.close) {
            enable(!binding.signIn.isEnabled());
            closeRevealEffect();
        } else if (id == R.id.signUp) {
            handleSignUp(binding.etUsernameReg.getText().toString(),
                    binding.etPasswordReg.getText().toString(),
                    binding.etConfirmReg.getText().toString());
        } else if (id == R.id.signIn) {
            handleSignIn(binding.etUsername.getText().toString(),
                    binding.etPassword.getText().toString());
        } else if (id == R.id.forgotPassword) {
            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSignIn(String username, String password) {
        String message = "Username or password incorrect";
        int length = Toast.LENGTH_SHORT;
        if (Utils.isOk(new String[]{username, password})) {
            User user = Database.getInstance(this).getUser(username, password);
            if (user != null) {
                message = String.format("Signed in as %s", user.getUsername());
                length = Toast.LENGTH_LONG;
                finishActivity();
            }
        }
        Toast.makeText(this, message, length).show();
    }

    private void handleSignUp(String username, String pass, String passConf) {
        String message;
        int length = Toast.LENGTH_SHORT;
        if (Utils.isOk(new String[]{username, pass, passConf})) {
            if (Utils.hasSpecialChars(username))
                message = "Use only following chars: a-z 0-9_ ";
            else if (username.length() < 3)
                message = "Username should have at least 3 characters";
            else if (pass.equals(passConf)) {
                if (pass.length() > 5) {
                    String sha256 = Utils.getSHA256Hash(pass);
                    if (Database.getInstance(this).addToUsersTable(username, sha256, getResources().getInteger(R.integer.amountOfMoney))) {
                        User.getUser(username, getResources().getInteger(R.integer.amountOfMoney));
                        message = "Registered successfully!";
                        finishActivity();
                    } else message = "User exists with this name";
                } else message = "Password should have at least 6 characters";
            } else message = "Passwords don't match";
        } else message = "Incorrect input, fill fields!";
        Toast.makeText(this, message, length).show();
    }

    private void finishActivity() {
        if (startReservation) {
            Intent intent = new Intent(this, ActivitySeatReservation.class);
            intent.putExtra(Const.MOVIE, getIntent().getSerializableExtra(Const.MOVIE));
            intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
            startActivity(intent);
        } else {
//        RESULT_OK => registration or sign in completed successfully
            setResult(RESULT_OK);
        }
        finish();
    }

    private void closeRevealEffect() {
        Animator reveal = getCircularReveal(false);
        final AnimatorSet set = getFABTranslateSet(false);
        final AnimatorSet translateBack = getCardTranslateSet(false);
        final Animator alpha = ObjectAnimator.ofFloat(binding.cardContent, "alpha", 1, 0);
        alpha.setDuration(250);
        reveal.setStartDelay(50);
        reveal.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                binding.cardSignUp.setVisibility(View.INVISIBLE);
                translateBack.start();
                set.start();
                binding.create.show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        alpha.start();
        reveal.start();
    }

    private void openRevealEffect() {
        final Animator reveal = getCircularReveal(true);
        final Animator translate = getCardTranslateSet(true);
        final Animator alpha = ObjectAnimator.ofFloat(binding.cardContent, "alpha", 0, 1);
        alpha.setDuration(400);
        reveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                alpha.start();
                translate.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        AnimatorSet set = getFABTranslateSet(true);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                binding.cardSignUp.setVisibility(View.VISIBLE);
                reveal.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
        binding.create.hide();
    }

    private Animator getCircularReveal(boolean isOpening) {
        float fromRadius = isOpening ? 0 : maxRadius + Utils.convertDPtoPX(getResources(), 3);
        float toRadius = isOpening ? maxRadius + Utils.convertDPtoPX(getResources(), 5) : 0;
        Animator reveal = ViewAnimationUtils.createCircularReveal(binding.cardSignUp, cardX, cardY, fromRadius, toRadius);
        reveal.setDuration(getResources().getInteger(R.integer.revealTime));
        reveal.setInterpolator(isOpening ? new FastOutSlowInInterpolator() : new AccelerateInterpolator());
        return reveal;
    }

    private AnimatorSet getCardTranslateSet(boolean toUp) {
        float fromY = toUp ? 0 : cardTranslateY;
        float toY = toUp ? cardTranslateY : 0;
        Animator y = ObjectAnimator.ofFloat(binding.cardSignIn, "translationY", fromY, toY);
        AnimatorSet set = new AnimatorSet();
        set.play(y);
        set.setDuration(400);
        set.setInterpolator(new OvershootInterpolator());
        return set;
    }

    private AnimatorSet getFABTranslateSet(boolean toUp) {
        float fromX = toUp ? 0 : containerX - fabX0;
        float fromY = toUp ? 0 : containerY - fabY0;
        float toX = toUp ? containerX - fabX0 : 0;
        float toY = toUp ? containerY - fabY0 : 0;
        Animator x = ObjectAnimator.ofFloat(binding.create, "translationX", fromX, toX);
        Animator y = ObjectAnimator.ofFloat(binding.create, "translationY", fromY, toY);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(x, y);
        set.setDuration(getResources().getInteger(R.integer.fabTime));
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        return set;
    }

    private void enable(boolean isEnabled) {
        binding.etUsername.setEnabled(isEnabled);
        binding.etPassword.setEnabled(isEnabled);
        binding.signIn.setEnabled(isEnabled);
        binding.forgotPassword.setEnabled(isEnabled);

        binding.close.setEnabled(!isEnabled);
        binding.etUsernameReg.setEnabled(!isEnabled);
        binding.etPasswordReg.setEnabled(!isEnabled);
        binding.etConfirmReg.setEnabled(!isEnabled);
        binding.signUp.setEnabled(!isEnabled);
    }

    private void defineDimensions() {
//        centerX and centerY values
        cardX = (binding.cardSignUp.getWidth()) / 2;
        cardY = (binding.cardSignUp.getHeight()) / 2;
        maxRadius = (float) Math.sqrt(cardX * cardX + cardY * cardY);
        fabY0 = (binding.create.getBottom() - binding.create.getHeight() / 2);
        fabX0 = (binding.create.getRight() - binding.create.getWidth() / 2);
        containerX = (binding.container.getWidth()) / 2;
        containerY = (binding.container.getHeight() - Utils.convertDPtoPX(getResources(), 16)) / 2;
        cardTranslateY = Utils.convertDPtoPX(getResources(), -24);
    }
}
