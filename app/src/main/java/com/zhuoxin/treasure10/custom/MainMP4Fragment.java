package com.zhuoxin.treasure10.custom;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.treasure10.commons.ActivityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MainMP4Fragment extends Fragment implements TextureView.SurfaceTextureListener {

    private TextureView mTextureView;
    private ActivityUtils mActivity;
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextureView = new TextureView(getContext());
        return mTextureView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextureView.setSurfaceTextureListener(this);
        mActivity = new ActivityUtils(this);
        super.onViewCreated(view, savedInstanceState);
    }
    //--------------------实现监听方法------------------------------------

    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, int i, int i1) {
        try {
            AssetFileDescriptor openFd = getContext().getAssets().openFd("welcome.mp4");
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(openFd.getFileDescriptor(),openFd.getStartOffset(),openFd.getLength());
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setSurface(new Surface(surfaceTexture));
                    mPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            mActivity.showToast("视频播放失败"+e.getMessage());
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
    //-----------------------------------------

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer!=null) {
            mPlayer.release();
            mPlayer = null;
        }
        if (mActivity!=null) {
            mActivity=null;
        }
        if (mTextureView!=null) {
            mTextureView=null;
        }
    }
}
