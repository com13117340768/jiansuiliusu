package com.yy.core.util.timer;

import java.util.TimerTask;

/**
 * Created by ft on 2018/9/21.
 */
public class BaseTimerTask extends TimerTask {


    private ITimerListener listener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.onTimer();
    }
}
