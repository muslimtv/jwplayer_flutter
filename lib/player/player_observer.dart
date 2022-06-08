import 'package:flutter/services.dart';
import 'package:rxdart/subjects.dart';

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

  final PublishSubject<bool> _miniplayerPlaying = PublishSubject();
  Stream<bool> get miniplayerPlayingStream => _miniplayerPlaying.stream;

  onMiniplayerDispose() {
    //_miniplayerPlaying.close();
  }

  void listen(Function firstFrameCallback, Function playerErrorCallback) async {
    onFirstFrameCallback = firstFrameCallback;
    onPlayerErrorCallback = playerErrorCallback;
    eventChannel.receiveBroadcastStream().listen(processEvent);
  }

  void processEvent(dynamic event) async {
    String eventName = event["name"];
    print('Event received -> $eventName');

    switch (eventName) {

      /* onReady */
      case "onReady":
        num setupTime = event["setupTime"];
        break;

      case "onAdTime":
        _miniplayerPlaying.add(true);
        break;
      case "onTime":
        print('miniplayer');
        _miniplayerPlaying.add(true);
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
