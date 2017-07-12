package com.markchan.carrier.panel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.markchan.carrier.R;
import com.markchan.carrier.adapter.TextPanelPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mark Chan <a href="markchan2gm@gmail.com">Contact me.</a>
 * @version 1.0
 * @since 17/7/11
 */
public class TextPanelFragment extends Fragment {

    @BindView(R.id.text_panel_fragment_vp_tab)
    SmartTabLayout mViewPagerTab;
    @BindView(R.id.text_panel_fragment_vp)
    ViewPager mViewPager;
    @BindView(R.id.text_panel_fragment_acbtn_complete)
    AppCompatButton mCompleteBtn;

    private Unbinder mUnbinder;

    private TextPanelPagerAdapter mTextPanelPagerAdapter;
    private List<View> mViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_panel, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mViews = new ArrayList<>();
        View typefacePanelView = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_vp_typeface, container, false);
        mViews.add(typefacePanelView);
        View textSizePanelView = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_vp_text_size, container, false);
        mViews.add(textSizePanelView);
        View textAlignmentPanelView = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_vp_text_alignment, container, false);
        mViews.add(textAlignmentPanelView);
        View textColorPanel = LayoutInflater.from(getActivity())
                .inflate(R.layout.item_vp_text_color, container, false);
        mViews.add(textColorPanel);

        mTextPanelPagerAdapter = new TextPanelPagerAdapter(getActivity(), mViews);

        mViewPager.setAdapter(mTextPanelPagerAdapter);

        mViewPagerTab.setViewPager(mViewPager);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}