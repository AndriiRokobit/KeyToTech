package com.test.keytotech.mvp.start;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.test.keytotech.AndroidApplication;
import com.test.keytotech.R;
import com.test.keytotech.model.CommentsResponse;
import com.test.keytotech.model.ParcelableComments;
import com.test.keytotech.mvp.comments.CommentsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class StartActivity extends MvpAppCompatActivity implements StartView {

    @InjectPresenter
    StartPresenter presenter;

    @ProvidePresenter
    public StartPresenter providePresenter() {
        return AndroidApplication.startComponent().provideStartPresenter();
    }

    @BindView(R.id.etLowerBound)
    TextInputEditText etLowerBound;
    @BindView(R.id.etUpperBound)
    TextInputEditText etUpperBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidApplication.applicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnEnter)
    public void goToNextScreen(View view) {
        //pick comments only postId=1
        presenter.loadComments(1);
        hideSoftKeyboard(this);
    }

    @Override
    public void successLoadingComments(List<CommentsResponse> list) {
        ArrayList<ParcelableComments> parcelableComments = new ArrayList<>();
        for (CommentsResponse c : list) {
            parcelableComments.add(new ParcelableComments(c));
        }
        if (!TextUtils.isEmpty(etLowerBound.getText()) && !TextUtils.isEmpty(etUpperBound.getText()))
        loadFragment(CommentsFragment.newInstance(parcelableComments,Integer.valueOf(etLowerBound.getText().toString()),
                Integer.valueOf(etUpperBound.getText().toString())));
        else Toast.makeText(StartActivity.this, getResources().getString(R.string.enter_data_message),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toggleLoading(boolean visible, int messageId) {

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
