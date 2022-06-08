part of 'miniplayer_cubit.dart';

@immutable
abstract class MiniplayerState {}

class MiniplayerNotShowed extends MiniplayerState {}

class MiniplayerShowed extends MiniplayerState {
  final Widget player;

  MiniplayerShowed(this.player);
}
