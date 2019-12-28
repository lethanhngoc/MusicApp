package com.android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.mainapp.R;

public class FragmentYourLibrary extends Fragment {

    private static final String KEY_COLOR = "key_color";
    public FragmentYourLibrary() {
    }

    public static FragmentYourLibrary newInstance(int color) {
        FragmentYourLibrary fragment = new FragmentYourLibrary();
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);
        ViewPager viewPager=rootView.findViewById(R.id.viewpagerLibrary);

        RelativeLayout relativeLayout = rootView.findViewById(R.id.fragmentLocalMusic);
        return rootView;
    }


}
