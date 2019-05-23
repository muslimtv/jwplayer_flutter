import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'PlayerObserver.dart';

class MediaPlayer extends StatefulWidget {
  final bool autoPlay;
  final String file;
  final String thumbnail;

  MediaPlayer(this.autoPlay, this.file, {this.thumbnail, Key key})
      : super(key: key);

  @override
  MediaPlayerState createState() => new MediaPlayerState();
}

class MediaPlayerState extends State<MediaPlayer> {
  PlayerObserver playerObserver;
  int _platformViewId;
  bool _isPlaying = false;

  @override
  void initState() {
    super.initState();
    _isPlaying = widget.autoPlay;
  }

  @override
  void didUpdateWidget(MediaPlayer oldWidget) {
    super.didUpdateWidget(oldWidget);
  }

  @override
  void dispose() {
    _disposePlatformView(_platformViewId, isDisposing: true);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    Widget playerWidget = Container(
      color: Colors.black87,
    );

    /* setup player */
    if (widget.file != null) {
      if (playerObserver == null) {
        playerObserver = PlayerObserver.instance();
      }

      playerObserver.isAutoPlay = widget.autoPlay;

      /* Android */
      if (Platform.isAndroid) {
        playerWidget = AndroidView(
          viewType: 'tv.mta.jwplayer/JWPlayerView',
          gestureRecognizers: Set()
            ..add(Factory<HorizontalDragGestureRecognizer>(
                () => HorizontalDragGestureRecognizer()))
            ..add(Factory<TapGestureRecognizer>(() => TapGestureRecognizer())),
          creationParams: {
            "autoPlay": _isPlaying,
            "file": widget.file,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            _platformViewId = viewId;
            playerObserver.listen(_onFirstFrame, _onPlayerError);
          },
        );
      }

      /* iOS */
      else if (Platform.isIOS) {
        playerWidget = UiKitView(
          viewType: 'tv.mta.jwplayer/JWPlayerView',
          gestureRecognizers: Set()
            ..add(Factory<HorizontalDragGestureRecognizer>(
                () => HorizontalDragGestureRecognizer()))
            ..add(Factory<TapGestureRecognizer>(() => TapGestureRecognizer())),
          creationParams: {
            "autoPlay": _isPlaying,
            "file": widget.file,
          },
          creationParamsCodec: const JSONMessageCodec(),
          onPlatformViewCreated: (viewId) {
            _platformViewId = viewId;
            playerObserver.listen(_onFirstFrame, _onPlayerError);
          },
        );
      }
    } else {
      _disposePlatformView(_platformViewId);
    }

    /* render player */
    return AspectRatio(
      aspectRatio: 16 / 9,
      child: playerWidget,
    );
  }

  void _onFirstFrame() async {
    /* first frame */
  }

  void _onPlayerError() async {
    /* player error */
  }

  void _disposePlatformView(int viewId, {bool isDisposing = false}) {
    if (viewId != null) {
      var methodChannel =
          MethodChannel("tv.mta.jwplayer/JWPlayerView_${viewId}");

      /* clean platform view */
      methodChannel.invokeMethod("dispose");

      if (!isDisposing) {
        setState(() {
          _platformViewId = null;
        });
      }
    }
  }
}
