package me.wavever.ganklock.ui.fragment;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import me.wavever.ganklock.R;
import me.wavever.ganklock.presenter.LikePresenter;
import me.wavever.ganklock.view.ILikeView;

/**
 * Created by wavever on 2016/8/12.
 */
public class LikeFragment extends BaseFragment<ILikeView,LikePresenter> implements ILikeView{

    @Override
    protected int loadView() {
        return R.layout.fragment_like;
    }

    @Override
    public void initViews() {

    }

    @Override
    public LikePresenter createPresenter() {
        return new LikePresenter();
    }
}
