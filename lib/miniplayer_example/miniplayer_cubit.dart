import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

part 'miniplayer_state.dart';

class MiniplayerCubit extends Cubit<MiniplayerState> {
  MiniplayerCubit() : super(MiniplayerNotShowed());

  showMiniplayer(Widget player) {
    emit(MiniplayerShowed(player));
  }

  hideMiniplayer() {
    emit(MiniplayerNotShowed());
  }
}
