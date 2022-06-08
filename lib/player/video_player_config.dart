import 'package:jwplayer_flutter/player/video_ad_config.dart';

class VideoPlayerConfig {
  final String file;
  final bool autoplay;
  final List<VideoAdConfig> videoAdConfigs;

  const VideoPlayerConfig({
    this.file,
    this.videoAdConfigs,
    this.autoplay,
  });

  Map<String, dynamic> toJson() => {
        "file": file,
        "autoplay": autoplay,
        "videoAdConfigs": videoAdConfigs.map((config) => config.toJson()),
      };

  VideoPlayerConfig fromJson(Map<String, dynamic> json) => VideoPlayerConfig(
        file: json["file"],
        videoAdConfigs: (json["videoAdConfigs"] as List<dynamic>),
        /*        .map(
              (jsonConfig) =>
                  VideoAdConfig.fromJson(jsonConfig as Map<String, dynamic>),
            )
            .toList(), */
        autoplay: json["autoplay"],
      );
}
