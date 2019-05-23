import 'package:flutter/services.dart';

class PlayerObserver {
  static final PlayerObserver _instance = PlayerObserver(
      EventChannel("tv.mta.jwplayer/JWEventHandler", JSONMethodCodec()));

  PlayerObserver(this.eventChannel);

  static PlayerObserver instance() {
    return _instance;
  }

  final EventChannel eventChannel;

  Function onFirstFrameCallback;
  Function onPlayerErrorCallback;

  bool isAutoPlay = false;

  void listen(Function firstFrameCallback, Function playerErrorCallback) async {
    onFirstFrameCallback = firstFrameCallback;
    onPlayerErrorCallback = playerErrorCallback;
    eventChannel.receiveBroadcastStream().listen(processEvent);
  }

  void processEvent(dynamic event) async {
    String eventName = event["name"];

    switch (eventName) {

      /* onReady */
      case "onReady":
        int setupTime = event["setupTime"];
        break;

      /* onBeforePlay */
      case "onBeforePlay":
        break;

      /* onFirstFrame */
      case "onFirstFrame":
        break;

      /* onPause */
      case "onPause":
        break;

      /* onPlayEvent */
      case "onPlayEvent":
        break;

      /* onComplete */
      case "onComplete":
        break;

      /* onTime */
      case "onTime":
        double position = (event["position"].toDouble()).abs();
        break;

      /* onSeek */
      case "onSeek":

        /* position of the player before the player seeks (in seconds) */
        int position = (event["position"]).toInt();

        /* requested position to seek to (in seconds) */
        double offset = double.parse("${event["offset"]}");

        /* do something */

        break;

      case "onError":
        /* do something */
        break;

      default:
        break;
    }
  }
}
