class BannerSize {
  final int width;
  final int height;

  static const BannerSize standard = BannerSize(width: 320, height: 50);
  static const BannerSize leaderboard = BannerSize(width: 728, height: 90);
  static const BannerSize iabStandard = BannerSize(width: 468, height: 60);

  const BannerSize({this.width = 320, this.height = 50});
}
