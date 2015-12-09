package com.example.nas.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class GestureActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    int count =4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        // Add all the images to the ViewFlipper
        for (int i = 0; i < mThumbIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mThumbIds[i]);
            mViewFlipper.addView(imageView);
        }

        // Set in/out flipping animations
       // mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
       // mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX() ) {
                mViewFlipper.setInAnimation(GestureActivity.this, R.anim.left_in);
                mViewFlipper.setOutAnimation(GestureActivity.this, R.anim.left_out);

                int displayedChild = mViewFlipper.getDisplayedChild();

                Toast.makeText(getApplication(),"displaychile"+displayedChild,Toast.LENGTH_LONG).show();
                int childCount = mViewFlipper.getChildCount();

                Toast.makeText(getApplication(),"childCount"+childCount,Toast.LENGTH_LONG).show();

                if (displayedChild != childCount - 1) {
                    //flipper.stopFlipping();
                    mViewFlipper.showNext();
                }




            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.setInAnimation(GestureActivity.this, R.anim.right_in);
                mViewFlipper.setOutAnimation(GestureActivity.this, R.anim.right_out);

                int displayedChild = mViewFlipper.getDisplayedChild();

                Toast.makeText(getApplication(),"displaychile"+displayedChild,Toast.LENGTH_LONG).show();
                int childCount = mViewFlipper.getChildCount();

                Toast.makeText(getApplication(),"childCount"+childCount,Toast.LENGTH_LONG).show();

                if(displayedChild!=0){
                    mViewFlipper.showPrevious();
                }

            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }





    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h,
            R.drawable.i, R.drawable.j,
            R.drawable.k, R.drawable.l,
            R.drawable.m, R.drawable.n


    };

}
