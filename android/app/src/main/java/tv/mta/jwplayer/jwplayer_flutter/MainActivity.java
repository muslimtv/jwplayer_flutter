package tv.mta.jwplayer.jwplayer_flutter;

import android.content.res.Configuration;
import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import tv.mta.jwplayer.jwplayer_flutter.jwplayer.PlayerViewFactory;

public class MainActivity extends FlutterActivity {
  private PlayerViewFactory playerViewFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    GeneratedPluginRegistrant.registerWith(this);

    Registrar registrar = registrarFor("tv.mta.jwplayer/JWPlayerPlugin");

    playerViewFactory = new PlayerViewFactory(this, registrar.messenger());

    registrar.platformViewRegistry().registerViewFactory("tv.mta.jwplayer/JWPlayerView", playerViewFactory);
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
  public void onConfigurationChanged(Configuration newConfig) {
    playerViewFactory.onConfigurationChange(newConfig);
    super.onConfigurationChanged(newConfig);
  }
}
