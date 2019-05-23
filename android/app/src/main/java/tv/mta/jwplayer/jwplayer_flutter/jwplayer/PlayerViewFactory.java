package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.JSONMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class PlayerViewFactory extends PlatformViewFactory {

    private final Activity activity;

    private PlayerView playerView;

    private BinaryMessenger messenger;

    public PlayerViewFactory(Activity activity, BinaryMessenger messenger) {
        super(JSONMessageCodec.INSTANCE);
        this.activity = activity;
        this.messenger = messenger;
    }

    @Override
    public PlatformView create(Context context, int id, Object args) {

        playerView = new PlayerView(context, activity, id, messenger, args);

        return playerView;
    }

    public void onResume() {
        if (playerView != null) {
            playerView.onResume();
        }
    }

    public void onPause() {
        if (playerView != null) {
            playerView.onPause();
        }
    }

    public void onConfigurationChange(Configuration newConfig) {
        if (playerView != null) {
            playerView.onConfigurationChange(newConfig);
        }
    }
}