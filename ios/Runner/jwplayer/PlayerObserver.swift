import Foundation

extension PlayerView: FlutterStreamHandler {
    
    public func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        self.flutterEventSink = events
        self.playerDelegate = PlayerDelegate(eventSink: events)
        self.player?.delegate = self.playerDelegate
        return nil
    }
    
    public func onCancel(withArguments arguments: Any?) -> FlutterError? {
        self.flutterEventSink = nil
        return nil
    }
    
    func setupObserver() {
        
        /* register for Flutter event channel */
        if let m = messenger {
            self.eventChannel = FlutterEventChannel(name: "tv.mta.jwplayer/JWEventHandler", binaryMessenger: m, codec: FlutterJSONMethodCodec.sharedInstance())
            self.eventChannel?.setStreamHandler(self)
        }
    }
}
