package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.ImaAdvertising;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.JSONMethodCodec;

public class PlayerLayout extends FrameLayout implements VideoPlayerEvents.OnFullscreenListener {

    private String LOG_TAG =PlayerLayout.class.getName();


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
        Log.d(LOG_TAG, "constructor int..");



        try {
            JSONObject args = (JSONObject) arguments;
            String file = args.getString("file");
            Log.d(LOG_TAG, "got file -> " + file);
            boolean autoPlay = args.getBoolean("autoPlay");
            Log.d(LOG_TAG, "got autplay -> " + String.valueOf(autoPlay));
            setPlayerConfig(file,autoPlay);

            //setAutoPlay(args.getBoolean("autoPlay"));

            //setFile(args.getString("file"));

            initPlayer();

        } catch (Exception e) {
            /* ignore */ }
    }

    private void setPlayerConfig(String file,boolean autoPlay) {
        ImaAdvertising imaAdvertising = getImaAd();
        List<PlaylistItem> playlist = getPlaylist(file);

        playerConfig.setPlaylist(playlist);
        playerConfig.setAdvertising(imaAdvertising);
        playerConfig.setAutostart(autoPlay);
    }

    private ImaAdvertising getImaAd() {
        Log.d(LOG_TAG, "Gettting IMA ad..");

        String adTagUrl = "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/single_ad_samples&sz=640x480&cust_params=sample_ct%3Dlinear&ciu_szs=300x250%2C728x90&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=";
        AdBreak adBreakPre = new AdBreak.Builder()
                .tag(adTagUrl)
                .offset("pre")
                .build();

        List<AdBreak> schedule = new ArrayList<>();
        schedule.add(adBreakPre);
        return new ImaAdvertising(schedule);
    }

    private List<PlaylistItem> getPlaylist(String file) {
        Log.d(LOG_TAG, "Gettting playlist..");

        PlaylistItem playlistItem = new PlaylistItem.Builder()
                .file(file)
                .build();

        List<PlaylistItem> playlist = new ArrayList<>();
        playlist.add(playlistItem);
        return playlist;
    }

    private void initPlayer() {
        Log.d(LOG_TAG, "init player..");

        /*
         * An instance of our event handling class
         */

        mPlayerView = new JWPlayerView(this.activity, playerConfig);

        Log.d(LOG_TAG, "made jwplayerview");


        //mPlayerView.setup(playerConfig);

        /* handle hiding/showing of ActionBar */
        mPlayerView.addOnFullscreenListener(this);

        /* enable background audio */
        mPlayerView.setBackgroundAudio(true);

        /* keep the screen on during playback */
        keepScreenOnHandler = new KeepScreenOnHandler(mPlayerView, this.activity.getWindow());

        JWEventHandler mEventHandler = new JWEventHandler(mPlayerView);

        EventChannel eventChannel = new EventChannel(
                messenger,
                "tv.mta.jwplayer/JWEventHandler",
                JSONMethodCodec.INSTANCE);

        eventChannel.setStreamHandler(mEventHandler);

        Log.d(LOG_TAG, "setting view");


        this.addView(mPlayerView);
    }

    public void setFile(String file) {

        playerConfig.setFile(file);
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