package com.test.keytotech.mvp.start;

import android.content.Context;
import android.widget.Toast;

import com.test.keytotech.model.CommentsResponse;
import com.test.keytotech.repository.StartRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {
    private final StartRepository startRepository;
    private Context context;

    @Inject
    public StartPresenter(StartRepository startRepository, Context context) {
        this.startRepository = startRepository;
        this.context = context;
    }

    public void loadComments(int postId){
        startRepository.loadComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadCommentsSuccess, this::loadCommentsFailure);
    }

    private void loadCommentsFailure(Throwable throwable) {
        Toast.makeText(context, "Loading failure", Toast.LENGTH_SHORT).show();
    }

    private void loadCommentsSuccess(List<CommentsResponse> comments) {
        getViewState().successLoadingComments(comments);
    }
}
