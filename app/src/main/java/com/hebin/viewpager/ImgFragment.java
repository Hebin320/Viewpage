package com.hebin.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImgFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int mParam1;


    public static ImgFragment newInstance(int param1) {
        ImgFragment fragment = new ImgFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img, container, false);
        ImageView ivMain = (ImageView) view.findViewById(R.id.iv_main);
        if (!TextUtils.isEmpty(mParam1 + "")) {
            ivMain.setImageResource(mParam1);
        }
        return view;
    }

}
