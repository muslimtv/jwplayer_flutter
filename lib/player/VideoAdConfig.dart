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
