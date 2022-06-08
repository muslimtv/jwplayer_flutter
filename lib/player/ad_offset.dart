class AdOffset {
  final String offset;

  AdOffset(this.offset) : assert(offset != null);

  factory AdOffset.preroll() => AdOffset("pre");
  factory AdOffset.postroll() => AdOffset("post");
  factory AdOffset.custom({
    int hours,
    int minutes,
    int seconds,
    int miliseconds,
  }) =>
      AdOffset(
        "$hours:$minutes:$seconds:$miliseconds",
      );

  Map<String, dynamic> toJson() => {
        "offset": offset,
      };

  AdOffset fromJson(Map<String, dynamic> json) => AdOffset(
        json["offset"],
      );
}
