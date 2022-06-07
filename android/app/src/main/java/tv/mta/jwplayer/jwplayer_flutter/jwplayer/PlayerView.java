package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;


public class PlayerView implements PlatformView, MethodChannel.MethodCallHandler {

    private final PlayerLayout player;

    PlayerView(Context context, Activity activity, int id, BinaryMessenger messenger, Object args) {

        MethodChannel channel = new MethodChannel(messenger, "tv.mta.jwplayer/JWPlayerView_" + id);
        channel.setMethodCallHandler(this);

        player = new PlayerLayout(context, activity, messenger, args);
    }



    @Override
    public View getView() {
        return player;
    }

    @Override
    public void dispose() {
        player.onHostDestroy();
    }

    protected void onPause() {
        player.onHostPause();
    }

    @Override
    public void onFlutterViewAttached(@NonNull View flutterView) {
        PlatformView.super.onFlutterViewAttached(flutterView);
        Log.d(LogTags.AD_TAG, "onFlutterViewAttached");

    }

    @Override
    public void onFlutterViewDetached() {
        PlatformView.super.onFlutterViewDetached();
        Log.d(LogTags.AD_TAG, "onFlutterViewDetached");
    }

    protected void onResume() {
        Log.d(LogTags.AD_TAG, "onResume");

        player.onHostResume();
    }

    public void onConfigurationChange(Configuration newConfig) {
        player.onConfigurationChanged(newConfig);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if ("dispose".equals(call.method)) {
            dispose();
            result.success(true);
        } else {
            result.notImplemented();
        }
    }
}