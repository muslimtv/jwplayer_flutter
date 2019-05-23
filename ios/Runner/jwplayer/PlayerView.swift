import Foundation

public class PlayerView : NSObject, FlutterPlatformView {
    
    /* view specific properties */
    var frame:CGRect
    var viewId:Int64
    
    /* JW Player properties */
    var player: JWPlayerController?
    var playerDelegate:PlayerDelegate?
    var file:String = ""
    var autoPlay:Bool = false
    
    deinit {
        print("[dealloc] JWPLayer")
    }
    
    /* Flutter event streamer propertiews */
    var messenger:FlutterBinaryMessenger?
    var eventChannel:FlutterEventChannel?
    var flutterEventSink:FlutterEventSink?
    var channel: FlutterMethodChannel?
    
    init(frame:CGRect, viewId: Int64, messenger: FlutterBinaryMessenger, args: Any?) {
        
        /* set view properties */
        self.frame = frame
        self.viewId = viewId
        
        super.init()
        
        /* set Flutter messenger */
        self.messenger = messenger
        self.channel = FlutterMethodChannel(name: "tv.mta.jwplayer/JWPlayerView_" + String(viewId), binaryMessenger: messenger)
        self.channel?.setMethodCallHandler(self.handle)
        
        /* data as JSON */
        let parsedData = args as! [String: Any]
        
        /* set incoming player properties */
        self.file = parsedData["file"] as! String
        self.autoPlay = parsedData["autoPlay"] as! Bool
    }
    
    public func view() -> UIView {
        
        let playListItem = JWPlaylistItem()
        playListItem.file = file;
        
        let config = JWConfig()
        config.playlist = [playListItem];
        config.autostart = self.autoPlay
        
        player = JWPlayerController(config:config)
        
        player?.view.frame = self.frame;
        player?.view.autoresizingMask = [
            .flexibleBottomMargin,
            .flexibleHeight,
            .flexibleLeftMargin,
            .flexibleRightMargin,
            .flexibleTopMargin,
            .flexibleWidth];
        
        player?.forceFullScreenOnLandscape = true;
        player?.forceLandscapeOnFullScreen = true;
        player?.displayLockScreenControls = true;
        
        /* start listening for player events */
        setupObserver()
        
        return (player?.view!)!
    }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        switch call.method {
        case "dispose":
            dispose()
            result(true)
            break
        default:
            result(FlutterMethodNotImplemented)
            break
        }
    }
    
    public func dispose() {
        if self.player != nil {
            self.player?.stop()
            self.player = nil
        }
        
        self.flutterEventSink = nil
        self.eventChannel?.setStreamHandler(nil)
        self.channel = nil
        self.messenger = nil
    }
}
