package com.hebin.viewpager;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyViewpager extends ViewPager {

    private Context context;
    private int mPagePoistion;
    private int mCount;
    private LinearLayout llPoint;
    private int PHOTO_CHANGE_TIME = 1000;
    private  ImageView[] imageViews;
    private FragmentManager fm;
    private int normalBackground;
    private int selectBackground;


    public MyViewpager(Context context) {
        super(context);
        this.context = context;
    }

    public MyViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setPoint(LinearLayout llPoint,int normalBackground,int selectBackground){
        this.llPoint = llPoint;
        this.normalBackground = normalBackground;
        this.selectBackground = selectBackground;
    }
    public void setFm(FragmentManager fm){
        this.fm = fm;
    }
    public void setTime(int PHOTO_CHANGE_TIME){
        this.PHOTO_CHANGE_TIME = PHOTO_CHANGE_TIME;
    }

    public void setFragment(ArrayList<Fragment> fragments ) {
        VpAdapter adapter= new VpAdapter(fm,fragments);
        MyViewpager.this.setAdapter(adapter);
        MyViewpager.this.setOffscreenPageLimit(fragments.size() - 1);
        MyViewpager.this.setCurrentItem(1);
        imageViews = getImageViews(context,llPoint,fragments);
        assert imageViews != null;
        if (imageViews.length == 3) {
            MyViewpager.this.addOnPageChangeListener(null);
            mCount = imageViews.length - 2;
        } else if (imageViews.length > 3) {
            mCount = imageViews.length - 2;
        } else {
            mCount = 1;
        }
        mHandler.sendEmptyMessageAtTime(1, PHOTO_CHANGE_TIME);
        MyViewpager.this.addOnPageChangeListener(new OnPageChangeListener() {
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

            @Override
            public void onPageScrollStateChanged(int state) {
                if (ViewPager.SCROLL_STATE_IDLE == state) {
                    MyViewpager.this.setCurrentItem(mPagePoistion, false);//第二个参数必须为false，不然会有个跳转动画
                }
            }
        });
    }

    private int index = 1;
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if (index > imageViews.length - 2) {
                index = 1;
            }
            MyViewpager.this.setCurrentItem(index);
            index++;
            mHandler.sendEmptyMessageDelayed(1, PHOTO_CHANGE_TIME);
        }
    };

    private void setPoint(int states) {
        if (imageViews != null ) {
            for (int i = 1; i < mCount + 1; i++) {
                if (i == states) {
                    imageViews[i].setImageResource(selectBackground);
                } else {
                    imageViews[i].setImageResource(normalBackground);
                }
            }
        }
    }

    public ImageView[] getImageViews(Context context, LinearLayout linearLayout, List list) {
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
                    imageViews[i].setBackgroundResource(selectBackground);
                } else if (i == list.size() - 1) {
                    imageViews[i].setBackgroundResource(R.color.transparent);
                } else {
                    imageViews[i].setBackgroundResource(normalBackground);
                }
                if (list.size()==3){
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
