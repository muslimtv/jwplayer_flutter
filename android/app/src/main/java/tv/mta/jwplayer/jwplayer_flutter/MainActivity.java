package tv.mta.jwplayer.jwplayer_flutter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import tv.mta.jwplayer.jwplayer_flutter.jwplayer.PlayerViewFactory;

public class MainActivity extends FlutterActivity {
  private PlayerViewFactory playerViewFactory;
/*
 @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    Registrar registrar = registrarFor("tv.mta.jwplayer/JWPlayerPlugin");

    playerViewFactory = new PlayerViewFactory(this, registrar.messenger());

    registrar.platformViewRegistry().registerViewFactory("tv.mta.jwplayer/JWPlayerView", playerViewFactory);
  }

 */


  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    super.configureFlutterEngine(flutterEngine);
    Log.v("configureFlutterEngige","configure called");
    playerViewFactory = new PlayerViewFactory(this, flutterEngine.getDartExecutor().getBinaryMessenger());
    flutterEngine.getPlatformViewsController().getRegistry().registerViewFactory("tv.mta.jwplayer/JWPlayerView", playerViewFactory);
  }


  @Override
  protected void onResume() {
    super.onResume();

    if (playerViewFactory != null) {
      playerViewFactory.onResume();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();

    if (playerViewFactory != null) {
      playerViewFactory.onPause();
    }
  }

  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    playerViewFactory.onConfigurationChange(newConfig);
  }
}
