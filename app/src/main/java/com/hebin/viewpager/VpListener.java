package com.hebin.viewpager;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class VpListener implements ViewPager.OnPageChangeListener {

    private ImageView[] imageViews;
    private ViewPager viewPager;
    private int mPagePoistion;
    private int mCount;

    private int PHOTO_CHANGE_TIME = 1000;//定时变量
    private int index = 1;
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if (index > imageViews.length - 2) {
                index = 1;
            }
            viewPager.setCurrentItem(index);
            index++;
            mHandler.sendEmptyMessageDelayed(1, PHOTO_CHANGE_TIME);
        }
    };

    public VpListener(ImageView[] imageViews, ViewPager viewPager,int PHOTO_CHANGE_TIME ) {
        this.imageViews = imageViews;
        this.viewPager = viewPager;
        this.PHOTO_CHANGE_TIME = PHOTO_CHANGE_TIME;
        if (imageViews.length == 3) {
            viewPager.addOnPageChangeListener(null);
            mCount = imageViews.length - 2;
        } else if (imageViews.length > 3) {
            mCount = imageViews.length - 2;
        } else {
            mCount = 1;
        }
        mHandler.sendEmptyMessageAtTime(1, PHOTO_CHANGE_TIME);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mCount > 1) {
            if (position < 1) {//若到实际首位时把poistion设值为感官末尾
                mPagePoistion = mCount;
                setPoint(mCount);
            } else if (position > mCount) {//若到实际末位时把poistion设值为感官首尾
                mPagePoistion = 1;
                setPoint(1);
            } else {
                mPagePoistion = position;
                setPoint(position);
            }
        }
    }

    private void setPoint(int states) {
        if (imageViews != null) {
            for (int i = 1; i < mCount + 1; i++) {
                if (i == states) {
                    imageViews[i].setImageResource(R.drawable.color_circle);
                } else {
                    imageViews[i].setImageResource(R.drawable.gray_circle);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            viewPager.setCurrentItem(mPagePoistion, false);//第二个参数必须为false，不然会有个跳转动画
        }
    }


    public static ImageView[] getImageViews(Context context, LinearLayout linearLayout, List list) {
        if (list.size() == 1) {
            return null;
        } else {
            linearLayout.removeAllViews();
            ImageView[] imageViews = new ImageView[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ImageView img = new ImageView(context);
                int tb = (int) context.getResources().getDimension(R.dimen.margin_5);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tb, tb);
                layoutParams.setMargins(5, 0, 5, 0);
                img.setLayoutParams(layoutParams);
                imageViews[i] = img;
                if (i == 0) {
                    imageViews[i].setBackgroundResource(R.color.transparent);
                } else if (i == 1) {
                    imageViews[i].setBackgroundResource(R.drawable.color_circle);
                } else if (i == list.size() - 1) {
                    imageViews[i].setBackgroundResource(R.color.transparent);
                } else {
                    imageViews[i].setBackgroundResource(R.drawable.gray_circle);
                }
                if (list.size() == 3) {
                    for (int j = 0; j < list.size(); j++) {
                        imageViews[i].setBackgroundResource(R.color.transparent);
                    }
                }
                linearLayout.addView(imageViews[i]);
            }
            return imageViews;
        }
    }


}
