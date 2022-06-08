import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:jwplayer_flutter/player/player_arguments.dart';
import 'package:jwplayer_flutter/player/video_ad_config.dart';

import 'package:jwplayer_flutter/player/video_player_config.dart';

import 'player_observer.dart';

class MediaPlayer extends StatefulWidget {
  final VideoPlayerConfig videoPlayerConfig;
  final double seek;

  MediaPlayer({
    this.videoPlayerConfig,
    this.seek,
    Key key,
  }) : super(key: key);

  @override
  MediaPlayerState createState() => new MediaPlayerState();
}

class MediaPlayerState extends State<MediaPlayer> {
  PlayerObserver playerObserver;
  int _platformViewId;

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
    if (widget.videoPlayerConfig.file != null) {
      if (playerObserver == null) {
        playerObserver = PlayerObserver.instance();
      }

      playerObserver.isAutoPlay = widget.videoPlayerConfig.autoplay;

      /* Android */
      if (Platform.isAndroid) {
        playerWidget = AndroidView(
          key: widget.key,
          viewType: 'tv.mta.jwplayer/JWPlayerView',
          gestureRecognizers: Set()
            ..add(Factory<HorizontalDragGestureRecognizer>(
                () => HorizontalDragGestureRecognizer()))
            ..add(Factory<TapGestureRecognizer>(() => TapGestureRecognizer())),
          creationParams: {
            PlayerArguments.AUTO_PLAY: widget.videoPlayerConfig.autoplay,
            PlayerArguments.FILE: widget.videoPlayerConfig.file,
            PlayerArguments.AD_TAGS: widget.videoPlayerConfig.videoAdConfigs,
            "seek": widget.seek,
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
            "autoPlay": widget.videoPlayerConfig.autoplay,
            "file": widget.videoPlayerConfig.file,
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

/*

  @override
  Widget build(BuildContext context) {
    /* setup player */
    if (widget.videoPlayerConfig.file != null) {
      if (playerObserver == null) {
        playerObserver = PlayerObserver.instance();
      }

      playerObserver.isAutoPlay = widget.videoPlayerConfig.autoplay;

      final String jsonAdConfig = jsonEncode(widget
          .videoPlayerConfig.videoAdConfigs
          .map((config) => config.toJson()));

      /* Android */
      if (Platform.isAndroid) {
        return AspectRatio(
          aspectRatio: 16 / 9,
          child: AndroidView(
            viewType: 'tv.mta.jwplayer/JWPlayerView',
            gestureRecognizers: Set()
              ..add(Factory<HorizontalDragGestureRecognizer>(
                  () => HorizontalDragGestureRecognizer()))
              ..add(
                  Factory<TapGestureRecognizer>(() => TapGestureRecognizer())),
            creationParams: {
              PlayerArguments.AUTO_PLAY: widget.videoPlayerConfig.autoplay,
              PlayerArguments.FILE: widget.videoPlayerConfig.file,
              PlayerArguments.AD_TAGS: jsonAdConfig
            },
            creationParamsCodec: const JSONMessageCodec(),
            onPlatformViewCreated: (viewId) {
              _platformViewId = viewId;
              playerObserver.listen(_onFirstFrame, _onPlayerError);
            },
          ),
        );
      }

      /* iOS */
      else if (Platform.isIOS) {
        return AspectRatio(
          aspectRatio: 16 / 9,
          child: UiKitView(
            viewType: 'tv.mta.jwplayer/JWPlayerView',
            gestureRecognizers: Set()
              ..add(Factory<HorizontalDragGestureRecognizer>(
                  () => HorizontalDragGestureRecognizer()))
              ..add(
                  Factory<TapGestureRecognizer>(() => TapGestureRecognizer())),
            creationParams: {
              PlayerArguments.AUTO_PLAY: widget.videoPlayerConfig.autoplay,
              PlayerArguments.FILE: widget.videoPlayerConfig.file,
              PlayerArguments.AD_TAGS: jsonAdConfig
            },
            creationParamsCodec: const JSONMessageCodec(),
            onPlatformViewCreated: (viewId) {
              _platformViewId = viewId;
              playerObserver.listen(_onFirstFrame, _onPlayerError);
            },
          ),
        );
      }
    } else {
      _disposePlatformView(_platformViewId);
    }

    /* render player */
    return AspectRatio(
      aspectRatio: 16 / 9,
      child: Container(
        color: Colors.black87,
      ),
    );
  }

  */

  void _onFirstFrame() async {
    /* first frame */
  }

  void _onPlayerError() async {
    /* player error */
  }

  void _disposePlatformView(int viewId, {bool isDisposing = false}) {
    if (viewId != null) {
      var methodChannel = MethodChannel("tv.mta.jwplayer/JWPlayerView_$viewId");

      PlayerObserver.instance().onMiniplayerDispose();

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
