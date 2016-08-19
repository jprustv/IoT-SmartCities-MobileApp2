package com.example.jonathan.iot_smartcities_mobileapp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan on 19/08/2016.
 */
public class tbdFragment extends Fragment {

    private String title;
    private int page;
    private String tag;

    public static tbdFragment newInstance (int page, String title, String tag) {
        tbdFragment tobedeletedFragment = new tbdFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        args.putString("tag", tag);
        tobedeletedFragment.setArguments(args);
        return tobedeletedFragment;
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
        View view = inflater.inflate(R.layout.fragment_to_be_deleted, container, false);
        return view;
    }


}

