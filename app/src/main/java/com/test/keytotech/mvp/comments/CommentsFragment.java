package com.test.keytotech.mvp.comments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.keytotech.R;
import com.test.keytotech.model.ParcelableComments;
import com.test.keytotech.mvp.base.BaseFragment;
import com.test.keytotech.mvp.comments.adapter.CommentsAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsFragment extends BaseFragment {

    private static final String LOWER_BOUND = "lower_bound";
    private static final String UPPER_BOUND = "upper_bound";
    private static final String LIST = "list";

    private CommentsAdapter adapter;
    private int lowerBound;
    private int upperBound;

    @BindView(R.id.rvComments)
    RecyclerView rvComments;

    public static CommentsFragment newInstance(ArrayList<ParcelableComments> comments, int lower, int upper) {

        Bundle args = new Bundle();

        CommentsFragment fragment = new CommentsFragment();
        args.putParcelableArrayList(LIST,comments);
        args.putInt(UPPER_BOUND, upper);
        args.putInt(LOWER_BOUND, lower);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new CommentsAdapter();
        if (getArguments() != null){
            lowerBound = getArguments().getInt(LOWER_BOUND);
            upperBound = getArguments().getInt(UPPER_BOUND);
            initAdapter(getArguments().getParcelableArrayList(LIST));
        }
    }

    private void initAdapter(ArrayList<ParcelableComments> parcelableComments) {
        ArrayList<ParcelableComments> newList = new ArrayList<>();
        for (ParcelableComments p : parcelableComments) {
            if (p.getId() >= lowerBound && p.getId() <= upperBound){
                newList.add(p);
            }
        }
        adapter.setData(newList, getContext());
        rvComments.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvComments.setAdapter(adapter);
    }
}
