package com.zcz1024.withyou.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Activity 基类
 */
public class BaseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    private CompositeDisposable compositeDisposable;

    /**
     * 将网络请求返回对象Disposable添加到CompositeDisposable，
     * 目的是避免RxJava使用过程产生内存泄露
     */
    protected void addDisposable(Disposable... disposables)
    {
        if (compositeDisposable == null)
        {
            compositeDisposable = new CompositeDisposable();
        }

        for (Disposable disposable : disposables )
        {
            compositeDisposable.add(disposable);
        }

    }


    /**
     * 在Activity销毁之前释放Disposable对象
     */
    private void clearDisposable()
    {
        if (compositeDisposable != null)
        {
            compositeDisposable.clear();
        }
        compositeDisposable = null;
    }

    @Override
    protected void onDestroy()
    {
        clearDisposable();
        super.onDestroy();
    }

    public void closeActivity(View view){
        this.finish();
    }

    public interface OnItemClickListener {
        void itemClick(int position, View view);
    }
}
