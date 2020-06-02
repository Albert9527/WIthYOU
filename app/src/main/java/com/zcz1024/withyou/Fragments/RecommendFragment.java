package com.zcz1024.withyou.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.zcz1024.withyou.Fragments.RecommendFrgments.BookFragment;
import com.zcz1024.withyou.Fragments.RecommendFrgments.VideoFragment;
import com.zcz1024.withyou.R;

public class RecommendFragment extends BaseFragment {
    private FragmentManager fragmentManager;
    private BookFragment bookFragment;
    private VideoFragment movieFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initFragment();
        fragmentManager = getChildFragmentManager();
        bookFragment = new BookFragment();
        fragmentManager.beginTransaction()
                .add(R.id.recommend_fragment, bookFragment)
                .show(bookFragment)
                .commit();
        return view;
    }

    private void initFragment() {

    }
}
