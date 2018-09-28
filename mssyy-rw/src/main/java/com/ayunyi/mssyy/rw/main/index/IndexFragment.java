package com.ayunyi.mssyy.rw.main.index;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ayunyi.mssyy.rw.R;
import com.ayunyi.mssyy.rw.R2;
import com.ayunyi.mssyy.rw.main.index.message.MessageFragment;
import com.ayunyi.mssyy.rw.main.index.search.SearchFragment;
import com.joanzapata.iconify.widget.IconTextView;
import com.yy.core.fragments.RedWineFragment;
import com.yy.core.fragments.bottom.BottomItemFragment;
import com.yy.core.net.RestCreator;
import com.yy.core.net.callback.IError;
import com.yy.core.net.callback.IFailure;
import com.yy.core.net.rx.RxRestClient;
import com.yy.core.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ft on 2018/8/13.
 */
public class IndexFragment extends BottomItemFragment implements View.OnFocusChangeListener,
        View.OnClickListener,
        IError, IFailure {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    private RefreshHandler mRefreshHandler = null;
    List<CharSequence> data;
    Handler handler = new Handler();
    int mPos = 0;
    private Runnable mRunnable;
    boolean flag = false;


    //加载时候的状态
    private View notDataView;
    private View errorView;
    QuickAdapter mQuickAdapter = null;

    @OnClick(R2.id.icon_index_scan)
    void ScanOnclick() {
        startScanWithCheck(this.getParentDelegate());
    }


    @OnClick(R2.id.icon_index_message)
    void MessageOnclick() {
        getParentDelegate().getSupportDelegate().start(new MessageFragment());
    }


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.app_background), 3));
        final RedWineFragment ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        notDataView = getLayoutInflater().inflate(R.layout.loading_nodata, (ViewGroup) mRecyclerView.getParent(), false);

        errorView = getLayoutInflater().inflate(R.layout.loading_error, (ViewGroup) mRecyclerView.getParent(), false);

        Button button = errorView.getRootView().findViewById(R.id.id_btn_retry);
        button.setOnClickListener(this);

        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView
                , new IndexDataConverter()
                , this

        );
        mSearchView.setOnFocusChangeListener(this);
        mSearchView.setKeyListener(null);//设置为不弹出键盘 为不可编辑状态
        mSearchView.setHint("高端红酒");
        data = new ArrayList<>();
        data.add("甜型红葡萄酒");
        data.add("白兰地");
        data.add("半干红葡萄酒");
        data.add("干白葡萄酒");
        data.add("香槟");
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mSearchView.setHint(data.get(mPos));
                mPos++;
                if (mPos >= data.size()) {
                    mPos = 0;
                }
                handler.postDelayed(this, 3500);
            }
        };

        handler.postDelayed(mRunnable, 3500);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //  mToolbar.getBackground().setAlpha(0);//设置toolbar的背景透明度初始为0
        mToolbar.getBackground().mutate().setAlpha(0);//09.25  08:00
        initRecyclerView();
        initRefreshLayout();
        mRefreshHandler.firstPage("baidu_image.php");
    }


    protected void OnCallRxGet() {
        final String url = "index.php";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void OnCallRxRestClient() {
        final String url = "index.php";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //判断这个控件已经进入焦点模式
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchFragment());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(mRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        mQuickAdapter.setNewData(null);
        final RedWineFragment ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
        mRefreshHandler.firstPage("baidu_image.php");
    }

    @Override
    public void onError(int code, String msg) {
        mQuickAdapter.setNewData(null);
        onRefresh();
    }

    @Override
    public void onFailure() {
        onRefresh();
    }

    private void onRefresh() {
        mQuickAdapter = new QuickAdapter(0);
        mRecyclerView.setAdapter(mQuickAdapter);
     //   mQuickAdapter.setEmptyView(R.layout.loading, (ViewGroup) mRecyclerView.getParent());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (flag) {
                    ViewGroup viewGroup = (ViewGroup) errorView.getParent();
                    viewGroup.removeAllViews();
                    errorView = getLayoutInflater().inflate(R.layout.loading_error, (ViewGroup) mRecyclerView.getParent(), false);
                    Button button = errorView.getRootView().findViewById(R.id.id_btn_retry);
                    button.setOnClickListener(IndexFragment.this);
                }
                {
                    mQuickAdapter.setEmptyView(errorView);
                    flag = true;
                }
            }
        });
    }
}
