package com.ayunyi.mssyy.rw.main.index;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.ayunyi.mssyy.rw.R;
import com.yy.core.app.RedWine;

/**
 * Created by ft on 2018/8/16.
 */
public class TranslucentBehavior  extends CoordinatorLayout.Behavior<Toolbar>{

    //注意这个变量一定要定义成类变量
    private int mOffset = 0;
    //延长滑动过程
    private static final int MORE = 100;

    private static final int MAX_ALPHA = 255;
    private  Context context = RedWine.getApplicationContext();

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;//通过id去验证是不是我们依赖的View
    }

    /**
     * 这里返回true，才会接收到后续滑动事件
     *
     * @param coordinatorLayout
     * @param child             使用该Behavior的View
     * @param directTargetChild Coordinator的子View，它可能包含一些滑动的操作
     * @param target            初始化滑动动作的View
     * @param nestedScrollAxes  滑动的轴，根据这个值来判断是纵向滑动还是横向滑动;
     *                          {@linkplain ViewCompat#SCROLL_AXIS_VERTICAL} 竖向；
     *                          {@linkplain ViewCompat#SCROLL_AXIS_HORIZONTAL} 横向
     * @return
     */
    @SuppressWarnings("JavadocReference")
    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child
            , @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    //当我们指定依赖的View 有变化时，调用这个方法
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }


    /**
     * 这里可以获取到CoordinatorLayout的滑动
     *
     * @param coordinatorLayout
     * @param child             使用该Behavior的View
     * @param target            执行滑动动作的View
     * @param dx                横向滑动的距离,以像素为单位
     * @param dy                纵向滑动的距离,以像素为单位
     * @param consumed
     */
    @SuppressWarnings("JavadocReference")
    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout
            , @NonNull Toolbar toolbar
            , @NonNull View target
            , int dxConsumed
            , int dyConsumed
            , int dxUnconsumed
            , int dyUnconsumed
            , int type) {
        final int startOffset = 0;
        final int endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) + MORE;
        mOffset += dyConsumed;
        if (mOffset <= startOffset) {
            toolbar.getBackground().setAlpha(0);
        } else if (mOffset < endOffset) {
            final float percent = (float) (mOffset - startOffset) / endOffset;
            final int alpha = Math.round(percent * MAX_ALPHA);
            toolbar.getBackground().setAlpha(alpha);
        } else if (mOffset >= endOffset) {
            toolbar.getBackground().setAlpha(MAX_ALPHA);
        }
    }
}
