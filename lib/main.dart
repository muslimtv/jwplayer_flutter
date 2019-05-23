import 'package:flutter/material.dart';
import 'player/MediaPlayer.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'JWPlayer Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Player(title: 'JWPlayer Flutter'),
    );
  }
}

class Player extends StatelessWidget {
  Player({Key key, this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            _buildMediaPlayer(),
          ],
        ),
      ),
    );
  }

  Widget _buildMediaPlayer() {
    return MediaPlayer(
      true,
      /* auto play */
      "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8", /* file */
    );
  }
}
