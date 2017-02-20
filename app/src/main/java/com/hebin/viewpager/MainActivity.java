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
    ViewPager vpMain_1;
    LinearLayout llPoint_1;
    private int[] img = {R.mipmap.ic_vp_01, R.mipmap.ic_vp_02, R.mipmap.ic_vp_03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpMain = (MyViewpager) findViewById(R.id.vp_main);
        llPoint = (LinearLayout) findViewById(R.id.ll_point);
        vpMain_1 = (ViewPager) findViewById(R.id.vp_main_1);
        llPoint_1 = (LinearLayout) findViewById(R.id.ll_point_1);
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
        //第二个Viewpager
        ArrayList<Fragment> list_1 = new ArrayList<>();
        ImgFragment imgFragment_1;
        imgFragment_1 = ImgFragment.newInstance(img[img.length - 1]);
        list_1.add(imgFragment_1);
        for (int i = 0; i < img.length; i++) {
            imgFragment_1 = ImgFragment.newInstance(img[i]);
            list_1.add(imgFragment_1);
        }
        imgFragment_1 = ImgFragment.newInstance(img[0]);
        list_1.add(imgFragment_1);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(),list_1);
        vpMain_1.setAdapter(vpAdapter);
        vpMain_1.setCurrentItem(1);
        vpMain_1.setOffscreenPageLimit(img.length-1);
        vpMain_1.addOnPageChangeListener(new VpListener(VpListener.getImageViews(this,llPoint_1,list_1),vpMain_1,1600));

    }


}
