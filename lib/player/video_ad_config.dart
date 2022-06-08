import 'package:jwplayer_flutter/player/ad_offset.dart';

/* class VideoAdConfig {
  String tagUrl;
  AdOffset offset;

  VideoAdConfig(
    this.tagUrl,
    this.offset,
  ) : assert(
          tagUrl != null,
          offset != null,
        );

  Map<String, dynamic> toJson() => {
        "tagUrl": tagUrl,
        "offset": offset.toJson(),
      };

  factory VideoAdConfig.fromJson(Map<String, dynamic> json) =>
      VideoAdConfig(json["tagUrl"], json["offset"]);
}
 */

class VideoAdConfig {
  String tagUrl;
  String offset;

  VideoAdConfig(
    this.tagUrl,
    this.offset,
  );

  Map<String, dynamic> toJson() => {
        "tagUrl": tagUrl,
        "offset": offset,
      };
}
