package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import org.json.JSONObject;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.JSONMethodCodec;

public class PlayerLayout extends FrameLayout implements VideoPlayerEvents.OnFullscreenListener {

    /**
     * Reference to the {@link JWPlayerView}
     */
    private JWPlayerView mPlayerView;

    /**
     * Reference to the {@link PlayerConfig}
     */
    private final PlayerConfig playerConfig = new PlayerConfig.Builder().build();

    /**
     * App main activity
     */
    private final Activity activity;

    private final BinaryMessenger messenger;

    private KeepScreenOnHandler keepScreenOnHandler;

    public PlayerLayout(@NonNull Context context, Activity activity, BinaryMessenger messenger, Object arguments) {
        super(context);

        this.activity = activity;

        this.messenger = messenger;

        try {

            JSONObject args = (JSONObject) arguments;

            setFile(args.getString("file"));

            setAutoPlay(args.getBoolean("autoPlay"));

        } catch (Exception e) { /* ignore */ }
    }

    private void initPlayer() {

        mPlayerView = new JWPlayerView(this.activity, playerConfig);

        /* handle hiding/showing of ActionBar */
        mPlayerView.addOnFullscreenListener(this);

        /* enable background audio */
        mPlayerView.setBackgroundAudio(true);

        /* keep the screen on during playback */
        keepScreenOnHandler = new KeepScreenOnHandler(mPlayerView, this.activity.getWindow());

        /*
         * An instance of our event handling class
         */
        JWEventHandler mEventHandler = new JWEventHandler(mPlayerView);

        EventChannel eventChannel = new EventChannel(
                messenger,
                "tv.mta.jwplayer/JWEventHandler",
                JSONMethodCodec.INSTANCE);

        eventChannel.setStreamHandler(mEventHandler);

        this.addView(mPlayerView);
    }

    public void setFile(String file) {

        playerConfig.setFile(file);

        initPlayer();
    }

    public void setAutoPlay(Boolean autoPlay) {

        playerConfig.setAutostart(autoPlay);

        if (mPlayerView != null && autoPlay) {
            mPlayerView.play();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        /* set fullscreen when the device is rotated to landscape */
        if (mPlayerView != null) {
            mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /* exit fullscreen when the user pressed the Back button */
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerView.getFullscreen()) {
                mPlayerView.setFullscreen(false, true);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFullscreen(FullscreenEvent fullscreenEvent) {
        ActionBar actionBar = this.activity.getActionBar();
        if (actionBar != null) {
            if (fullscreenEvent.getFullscreen()) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        }
    }

    public void retryFailedPlayback() {

        try {

            /* retry playback */
            mPlayerView.play();

        } catch (Exception e) { /* ignore */ }
    }

    public void onHostResume() {

        try {

            /* let JW Player know that the app has returned from the background */
            mPlayerView.onResume();

        } catch (Exception e) { /* ignore */ }
    }

    public void onHostPause() {

        try {

            /* let JW Player know that the app is going to the background */
            mPlayerView.onPause();

        } catch (Exception e) { /* ignore */ }
    }

    public void onHostDestroy() {

        try {

            /* stop controlling screen wake lock */
            keepScreenOnHandler.destroy();

            /* let JW Player know that the app is being destroyed */
            mPlayerView.onDestroy();

        } catch (Exception e) { /* ignore */ }
    }
}