import Foundation

class PlayerDelegate: NSObject, JWPlayerDelegate {
    
    var flutterEventSink:FlutterEventSink?
    
    init(eventSink:@escaping FlutterEventSink) {
        self.flutterEventSink = eventSink
    }
    
    public func onReady(_ event: (JWEvent & JWReadyEvent)!) {
        self.flutterEventSink?(["name":"onReady", "setupTime":event.setupTime])
    }
    
    public func onAudioTracks(_ event: (JWEvent & JWLevelsEvent)!) {
        self.flutterEventSink?(["name":"onAudioTracks"])
    }
    
    public func onBeforeComplete() {
        self.flutterEventSink?(["name":"onBeforeComplete"])
    }
    
    public func onBeforePlay() {
        self.flutterEventSink?(["name":"onBeforePlay"])
    }
    
    public func onBuffer(_ event: (JWEvent & JWBufferEvent)!) {
        self.flutterEventSink?(["name":"onBuffer"])
    }
    
    public func onCaptionsList(_ event: (JWEvent & JWCaptionsListEvent)!) {
        self.flutterEventSink?(["name":"onCaptionsList"])
    }
    
    public func onComplete() {
        self.flutterEventSink?(["name":"onComplete"])
    }
    
    public func onFullscreen(_ event: (JWEvent & JWFullscreenEvent)!) {
        self.flutterEventSink?(["name":"onFullscreen", "fullscreen":event.fullscreen])
    }
    
    public func onIdle(_ event: (JWEvent & JWStateChangeEvent)!) {
        self.flutterEventSink?(["name":"onIdle", "oldState":event.oldState])
    }
    
    public func onMeta(_ event: (JWEvent & JWMetaEvent)!) {
        self.flutterEventSink?(["name":"onMeta"])
    }
    
    public func onPause(_ event: (JWEvent & JWStateChangeEvent)!) {
        self.flutterEventSink?(["name":"onPause"])
    }
    
    public func onPlay(_ event: (JWEvent & JWStateChangeEvent)!) {
        self.flutterEventSink?(["name":"onPlay", "oldState":event.oldState])
    }
    
    public func onPlaylistComplete() {
        self.flutterEventSink?(["name":"onPlaylistComplete"])
    }
    
    public func onPlaylistItem(_ event: (JWEvent & JWPlaylistItemEvent)!) {
        self.flutterEventSink?(["name":"onPlaylistItem", "index":event.index])
    }
    
    public func onPlaylist(_ event: (JWEvent & JWPlaylistEvent)!) {
        self.flutterEventSink?(["name":"onPlaylist"])
    }
    
    public func onSeek(_ event: (JWEvent & JWSeekEvent)!) {
        self.flutterEventSink?(["name":"onSeek", "offset":event.offset, "position":event.position])
    }
    
    public func onSetupError(_ event: (JWEvent & JWErrorEvent)!) {
        self.flutterEventSink?(["name":"onSetupError"])
    }
    
    public func onAdError(_ event: (JWAdEvent & JWErrorEvent)!) {
        self.flutterEventSink?(["name":"onAdError"])
    }
    
    public func onError(_ event: (JWEvent & JWErrorEvent)!) {
        self.flutterEventSink?(["name":"onError"])
    }
    
    public func onLevelsChanged(_ event: (JWEvent & JWLevelsChangedEvent)!) {
        self.flutterEventSink?(["name":"onLevelsChanged"])
    }
    
    public func onLevels(_ event: (JWEvent & JWLevelsEvent)!) {
        self.flutterEventSink?(["name":"onLevels"])
    }
    
    public func onAudioTrackChanged(_ event: (JWEvent & JWTrackChangedEvent)!) {
        self.flutterEventSink?(["name":"onAudioTrackChanged"])
    }
    
    public func onCaptionsChanged(_ event: (JWEvent & JWTrackChangedEvent)!) {
        self.flutterEventSink?(["name":"onCaptionsChanged"])
    }
    
    public func onAdClick(_ event: (JWAdEvent & JWAdDetailEvent)!) {
        self.flutterEventSink?(["name":"onAdClick"])
    }
    
    public func onAdComplete(_ event: (JWAdEvent & JWAdDetailEvent)!) {
        self.flutterEventSink?(["name":"onAdComplete"])
    }
    
    public func onAdSkipped(_ event: (JWAdEvent & JWAdDetailEvent)!) {
        self.flutterEventSink?(["name":"onAdSkipped"])
    }
    
    public func onAdImpression(_ event: (JWAdEvent & JWAdImpressionEvent)!) {
        self.flutterEventSink?(["name":"onAdImpression"])
    }
    
    public func onAdPause(_ event: (JWAdEvent & JWAdStateChangeEvent)!) {
        self.flutterEventSink?(["name":"onAdPause"])
    }
    
    public func onAdPlay(_ event: (JWAdEvent & JWAdStateChangeEvent)!) {
        self.flutterEventSink?(["name":"onAdPlay"])
    }
    
    public func onSeeked() {
        self.flutterEventSink?(["name":"onSeeked"])
    }
    
    public func onControls(_ event: (JWEvent & JWControlsEvent)!) {
        self.flutterEventSink?(["name":"onControls", "controls":event.controls])
    }
    
    public func onControlBarVisible(_ event: (JWEvent & JWControlsEvent)!) {
        self.flutterEventSink?(["name":"onControlBarVisible", "controls":event.controls])
    }
    
    public func onDisplayClick() {
        self.flutterEventSink?(["name":"onDisplayClick"])
    }
    
    public func onFirstFrame(_ event: (JWEvent & JWFirstFrameEvent)!) {
        self.flutterEventSink?(["name":"onFirstFrame", "loadTime":event.loadTime])
    }
    
    public func onAdSchedule(_ event: (JWAdEvent & JWAdScheduleEvent)!) {
        self.flutterEventSink?(["name":"onAdSchedule"])
    }
    
    public func onTime(_ event: (JWEvent & JWTimeEvent)!) {
        self.flutterEventSink?(["name":"onTime", "duration":event.duration, "position":event.position])
    }
}
