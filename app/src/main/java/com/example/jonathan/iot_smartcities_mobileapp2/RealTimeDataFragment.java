package com.example.jonathan.iot_smartcities_mobileapp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jonathan on 18/08/2016.
 */
public class RealTimeDataFragment extends Fragment {

    private String title;
    private int page;
    private String tag;

    public static RealTimeDataFragment newInstance (int page, String title, String tag) {
        RealTimeDataFragment rtdFragment = new RealTimeDataFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putString("tag", tag);
        rtdFragment.setArguments(args);
        return rtdFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
        title = getArguments().getString("title");
        tag = getArguments().getString("tag");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_real_time_data, container, false);
        return view;
    }


}
