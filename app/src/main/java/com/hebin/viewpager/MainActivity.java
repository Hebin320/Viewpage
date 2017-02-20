package com.hebin.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    MyViewpager vpMain;
    LinearLayout llPoint;
    private int[] img = {R.mipmap.ic_vp_01, R.mipmap.ic_vp_02, R.mipmap.ic_vp_03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpMain = (MyViewpager) findViewById(R.id.vp_main);
        llPoint = (LinearLayout) findViewById(R.id.ll_point);
        ArrayList<Fragment> list = new ArrayList<>();
        ImgFragment imgFragment;
        imgFragment = ImgFragment.newInstance(img[img.length - 1]);
        list.add(imgFragment);
        for (int i = 0; i < img.length; i++) {
            imgFragment = ImgFragment.newInstance(img[i]);
            list.add(imgFragment);
        }
        imgFragment = ImgFragment.newInstance(img[0]);
        list.add(imgFragment);
        //第一个Viewpager
        vpMain.setPoint(llPoint, R.drawable.gray_circle, R.drawable.color_circle);
        vpMain.setFm(getSupportFragmentManager());
        vpMain.setTime(800);
        vpMain.setFragment(list);

    }


}
