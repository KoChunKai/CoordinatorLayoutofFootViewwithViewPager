package ko.chun.kai.coordinatorLayout.of.footView.with.viewpager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by kevin on 2015/11/18.
 */
public class BottomToUpAnimation {

    AnimatorSet animatorSet;
    public boolean isAnimation = false;
    int amType = 0;//0:BottomToUp 1:UpToBottom;
    View view;

    public BottomToUpAnimation(View view){
        this.view = view;
        animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.addListener(animatorListener);
    }

    public void BottomToUp(){
        if(isAnimation) return;
        if(amType == 0) return;
        amType = 0;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", view.getHeight(), 0));
        animatorSet.setInterpolator(new DecelerateInterpolator(3.0f));
        animatorSet.start();
    }

    public void UpToBottom(){
        if(isAnimation) return;
        if(amType == 1) return;
        amType = 1;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", 0, view.getHeight()));
        animatorSet.start();
    }

    public void start(){
        if(isAnimation) return;
        if(amType == 1){
            BottomToUp();
        }else{
            UpToBottom();
        }
    }

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            if(amType == 0) view.setVisibility(View.VISIBLE);
            isAnimation = true;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if(amType == 1) view.setVisibility(View.GONE);
            isAnimation = false;
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };


}
