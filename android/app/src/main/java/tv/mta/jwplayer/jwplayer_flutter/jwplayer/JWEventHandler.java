package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import android.util.Log;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.AdBreakEndEvent;
import com.longtailvideo.jwplayer.events.AdBreakStartEvent;
import com.longtailvideo.jwplayer.events.AdClickEvent;
import com.longtailvideo.jwplayer.events.AdCompleteEvent;
import com.longtailvideo.jwplayer.events.AdErrorEvent;
import com.longtailvideo.jwplayer.events.AdImpressionEvent;
import com.longtailvideo.jwplayer.events.AdPauseEvent;
import com.longtailvideo.jwplayer.events.AdPlayEvent;
import com.longtailvideo.jwplayer.events.AdScheduleEvent;
import com.longtailvideo.jwplayer.events.AdSkippedEvent;
import com.longtailvideo.jwplayer.events.AdTimeEvent;
import com.longtailvideo.jwplayer.events.AudioTrackChangedEvent;
import com.longtailvideo.jwplayer.events.AudioTracksEvent;
import com.longtailvideo.jwplayer.events.BeforeCompleteEvent;
import com.longtailvideo.jwplayer.events.BeforePlayEvent;
import com.longtailvideo.jwplayer.events.BufferEvent;
import com.longtailvideo.jwplayer.events.CaptionsChangedEvent;
import com.longtailvideo.jwplayer.events.CaptionsListEvent;
import com.longtailvideo.jwplayer.events.CompleteEvent;
import com.longtailvideo.jwplayer.events.ControlBarVisibilityEvent;
import com.longtailvideo.jwplayer.events.ControlsEvent;
import com.longtailvideo.jwplayer.events.DisplayClickEvent;
import com.longtailvideo.jwplayer.events.ErrorEvent;
import com.longtailvideo.jwplayer.events.FirstFrameEvent;
import com.longtailvideo.jwplayer.events.FullscreenEvent;
import com.longtailvideo.jwplayer.events.IdleEvent;
import com.longtailvideo.jwplayer.events.LevelsChangedEvent;
import com.longtailvideo.jwplayer.events.LevelsEvent;
import com.longtailvideo.jwplayer.events.MetaEvent;
import com.longtailvideo.jwplayer.events.MuteEvent;
import com.longtailvideo.jwplayer.events.PauseEvent;
import com.longtailvideo.jwplayer.events.PlayEvent;
import com.longtailvideo.jwplayer.events.PlaylistCompleteEvent;
import com.longtailvideo.jwplayer.events.PlaylistEvent;
import com.longtailvideo.jwplayer.events.PlaylistItemEvent;
import com.longtailvideo.jwplayer.events.ReadyEvent;
import com.longtailvideo.jwplayer.events.SeekEvent;
import com.longtailvideo.jwplayer.events.SeekedEvent;
import com.longtailvideo.jwplayer.events.SetupErrorEvent;
import com.longtailvideo.jwplayer.events.TimeEvent;
import com.longtailvideo.jwplayer.events.VisualQualityEvent;
import com.longtailvideo.jwplayer.events.listeners.AdvertisingEvents;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;

import org.json.JSONObject;

import io.flutter.plugin.common.EventChannel;

public class JWEventHandler implements VideoPlayerEvents.OnSetupErrorListener,
        VideoPlayerEvents.OnPlaylistListener,
        VideoPlayerEvents.OnPlaylistItemListener,
        VideoPlayerEvents.OnPlayListener,
        VideoPlayerEvents.OnPauseListener,
        VideoPlayerEvents.OnBufferListener,
        VideoPlayerEvents.OnIdleListener,
        VideoPlayerEvents.OnErrorListener,
        VideoPlayerEvents.OnSeekListener,
        VideoPlayerEvents.OnTimeListener,
        VideoPlayerEvents.OnFullscreenListener,
        VideoPlayerEvents.OnAudioTracksListener,
        VideoPlayerEvents.OnAudioTrackChangedListener,
        VideoPlayerEvents.OnCaptionsListListener,
        VideoPlayerEvents.OnMetaListener,
        VideoPlayerEvents.OnPlaylistCompleteListener,
        VideoPlayerEvents.OnCompleteListener,
        VideoPlayerEvents.OnLevelsChangedListener,
        VideoPlayerEvents.OnLevelsListener,
        VideoPlayerEvents.OnCaptionsChangedListener,
        VideoPlayerEvents.OnControlsListener,
        VideoPlayerEvents.OnControlBarVisibilityListener,
        VideoPlayerEvents.OnDisplayClickListener,
        VideoPlayerEvents.OnMuteListener,
        VideoPlayerEvents.OnSeekedListener,
        VideoPlayerEvents.OnVisualQualityListener,
        VideoPlayerEvents.OnFirstFrameListener,
        VideoPlayerEvents.OnReadyListener,
        AdvertisingEvents.OnAdClickListener,
        AdvertisingEvents.OnAdCompleteListener,
        AdvertisingEvents.OnAdSkippedListener,
        AdvertisingEvents.OnAdErrorListener,
        AdvertisingEvents.OnAdImpressionListener,
        AdvertisingEvents.OnAdTimeListener,
        AdvertisingEvents.OnAdPauseListener,
        AdvertisingEvents.OnAdPlayListener,
        AdvertisingEvents.OnAdScheduleListener,
        AdvertisingEvents.OnBeforePlayListener,
        AdvertisingEvents.OnBeforeCompleteListener,
        AdvertisingEvents.OnAdBreakStartListener,
AdvertisingEvents.OnAdBreakEndListener,
        EventChannel.StreamHandler {

    private String TAG = LogTags.AD_TAG;

    private EventChannel.EventSink eventSink;

    private JWPlayerView jwPlayerView;

    public JWEventHandler(JWPlayerView jwPlayerView) {

        this.jwPlayerView = jwPlayerView;
        // Subscribe to all JW Player events
        jwPlayerView.addOnReadyListener(this);
        jwPlayerView.addOnFirstFrameListener(this);
        jwPlayerView.addOnSetupErrorListener(this);
        jwPlayerView.addOnPlaylistListener(this);
        jwPlayerView.addOnPlaylistItemListener(this);
        jwPlayerView.addOnPlayListener(this);
        jwPlayerView.addOnPauseListener(this);
        jwPlayerView.addOnBufferListener(this);
        jwPlayerView.addOnIdleListener(this);
        jwPlayerView.addOnErrorListener(this);
        jwPlayerView.addOnSeekListener(this);
        jwPlayerView.addOnTimeListener(this);
        jwPlayerView.addOnFullscreenListener(this);
        jwPlayerView.addOnLevelsChangedListener(this);
        jwPlayerView.addOnLevelsListener(this);
        jwPlayerView.addOnCaptionsListListener(this);
        jwPlayerView.addOnCaptionsChangedListener(this);
        //  jwPlayerView.addOnRelatedCloseListener(this);
        //  jwPlayerView.addOnRelatedOpenListener(this);
        //  jwPlayerView.addOnRelatedPlayListener(this);
        jwPlayerView.addOnControlsListener(this);
        jwPlayerView.addOnControlBarVisibilityListener(this);
        jwPlayerView.addOnDisplayClickListener(this);
        jwPlayerView.addOnMuteListener(this);
        jwPlayerView.addOnVisualQualityListener(this);
        jwPlayerView.addOnSeekedListener(this);
        jwPlayerView.addOnAdClickListener(this);
        jwPlayerView.addOnAdCompleteListener(this);
        jwPlayerView.addOnAdSkippedListener(this);
        jwPlayerView.addOnAdErrorListener(this);
        jwPlayerView.addOnAdImpressionListener(this);
        jwPlayerView.addOnAdTimeListener(this);
        jwPlayerView.addOnAdPauseListener(this);
        jwPlayerView.addOnAdPlayListener(this);
        jwPlayerView.addOnMetaListener(this);
        jwPlayerView.addOnPlaylistCompleteListener(this);
        jwPlayerView.addOnCompleteListener(this);
        jwPlayerView.addOnBeforePlayListener(this);
        jwPlayerView.addOnBeforeCompleteListener(this);
        jwPlayerView.addOnAdScheduleListener(this);
        jwPlayerView.addOnAdBreakStartListener(this);
    }

    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
    }

    @Override
    public void onCancel(Object o) {
        this.eventSink = null;
    }

    @Override
    public void onAdClick(AdClickEvent adClickEvent) {
        Log.d(TAG, "onAdClick");

        //this.jwPlayerView.play();
        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdClick");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdComplete(AdCompleteEvent adCompleteEvent) {
        Log.d(TAG, "onAdComplete");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdComplete");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdError(AdErrorEvent adErrorEvent) {
        Log.d(TAG, "onAdError");

        try {

            JSONObject message = new JSONObject();



            message.put("name", "onAdError " + adErrorEvent.getMessage());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdImpression(AdImpressionEvent adImpressionEvent) {
        Log.d(TAG, "onAdImpression");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdImpression");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdPause(AdPauseEvent adPauseEvent) {
        Log.d(TAG, "onAdPause");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdPause");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdPlay(AdPlayEvent adPlayEvent) {
        Log.d(TAG, "onAdPlay");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdPlay");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdSchedule(AdScheduleEvent adScheduleEvent) {
        Log.d(TAG, "onAdSchedule");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdSchedule");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdSkipped(AdSkippedEvent adSkippedEvent) {
        Log.d(TAG, "onAdSkipped");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdSkipped");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdTime(AdTimeEvent adTimeEvent) {
        //Log.d(TAG, "onAdTime");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdTime");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onBeforeComplete(BeforeCompleteEvent beforeCompleteEvent) {
        Log.d(TAG, "onBeforeComplete");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onBeforeComplete");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onBeforePlay(BeforePlayEvent beforePlayEvent) {
        Log.d(TAG, "onBeforePlay");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onBeforePlay");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAudioTrackChanged(AudioTrackChangedEvent audioTrackChangedEvent) {
        Log.d(TAG, "onAudioTrackChanged");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAudioTrackChanged");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAudioTracks(AudioTracksEvent audioTracksEvent) {
        Log.d(TAG, "onAudioTracks");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAudioTracks");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onBuffer(BufferEvent bufferEvent) {
        Log.d(TAG, "onBuffer");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onBuffer");

            message.put("oldState", bufferEvent.getOldState());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onCaptionsChanged(CaptionsChangedEvent captionsChangedEvent) {
        Log.d(TAG, "onCaptionsChanged");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onCaptionsChanged");

            message.put("currentTrack", captionsChangedEvent.getCurrentTrack());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onCaptionsList(CaptionsListEvent captionsListEvent) {
        Log.d(TAG, "onCaptionsList");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onCaptionsList");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onComplete(CompleteEvent completeEvent) {
        Log.d(TAG, "onComplete");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onComplete");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onControlBarVisibilityChanged(ControlBarVisibilityEvent controlBarVisibilityEvent) {
        Log.d(TAG, "onControlBarVisibilityChanged");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onControlBarVisibilityChanged");

            message.put("controls", controlBarVisibilityEvent.isVisible());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onControls(ControlsEvent controlsEvent) {
        Log.d(TAG, "onControls");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onControls");

            message.put("controls", controlsEvent.getControls());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onDisplayClick(DisplayClickEvent displayClickEvent) {
        Log.d(TAG, "onDisplayClick");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onDisplayClick");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onError(ErrorEvent errorEvent) {
        Log.d(TAG, "onError");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onError");

            message.put("message", errorEvent.getMessage());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onFirstFrame(FirstFrameEvent firstFrameEvent) {
        Log.d(TAG, "onFirstFrame");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onFirstFrame");

            message.put("loadTime", firstFrameEvent.getLoadTime());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onFullscreen(FullscreenEvent fullscreenEvent) {
        Log.d(TAG, "onFullscreen");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onFullscreen");

            message.put("fullscreen", fullscreenEvent.getFullscreen());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onIdle(IdleEvent idleEvent) {
        Log.d(TAG, "onIdle");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onIdle");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onLevelsChanged(LevelsChangedEvent levelsChangedEvent) {
        Log.d(TAG, "onLevelsChanged");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onLevelsChanged");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onLevels(LevelsEvent levelsEvent) {
        Log.d(TAG, "onLevels");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onLevels");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onMeta(MetaEvent metaEvent) {
        Log.d(TAG, "onMeta");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onMeta");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onMute(MuteEvent muteEvent) {
        Log.d(TAG, "onMute");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onMute");

            message.put("mute", muteEvent.getMute());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onPause(PauseEvent pauseEvent) {
        Log.d(TAG, "onPause");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onPause");

            message.put("oldState", pauseEvent.getOldState());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onPlay(PlayEvent playEvent) {
        Log.d(TAG, "onPlay");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onPlay");

            message.put("oldState", playEvent.getOldState());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onPlaylistComplete(PlaylistCompleteEvent playlistCompleteEvent) {
        Log.d(TAG, "onPlaylistComplete");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onPlaylistComplete");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onPlaylistItem(PlaylistItemEvent playlistItemEvent) {
        Log.d(TAG, "onPlaylistItem");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onPlaylistItem");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onPlaylist(PlaylistEvent playlistEvent) {
        Log.d(TAG, "onPlaylist");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onPlaylist");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onReady(ReadyEvent readyEvent) {
        Log.d(TAG, "onReady");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onReady");

            message.put("setupTime", readyEvent.getSetupTime());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onSeek(SeekEvent seekEvent) {
        Log.d(TAG, "onSeek");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onSeek");

            message.put("offset", seekEvent.getOffset());

            message.put("position", seekEvent.getPosition());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onSeeked(SeekedEvent seekedEvent) {
        Log.d(TAG, "onSeeked");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onSeeked");

            message.put("position", seekedEvent.getPosition());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onSetupError(SetupErrorEvent setupErrorEvent) {
        Log.d(TAG, "onSetupError");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onSetupError");

            message.put("message", setupErrorEvent.getMessage());

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onTime(TimeEvent timeEvent) {
        //Log.d(TAG, "onTime");

        try {

            JSONObject message = new JSONObject();

           // message.put("name", "onTime");

           // message.put("duration", timeEvent.getDuration());

            //message.put("position", timeEvent.getPosition());

            //eventSink.success(message);

        } catch (Exception e) { /* ignore */ }


    }

    @Override
    public void onVisualQuality(VisualQualityEvent visualQualityEvent) {
        Log.d(TAG, "onVisualQuality");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onVisualQuality");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdBreakStart(AdBreakStartEvent adBreakStartEvent) {
        Log.d(TAG, "onAdBreakStart");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdBreakStart");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }

    @Override
    public void onAdBreakEnd(AdBreakEndEvent adBreakEndEvent) {
        Log.d(TAG, "onAdBreakEnd");

        try {

            JSONObject message = new JSONObject();

            message.put("name", "onAdBreakEnd");

            eventSink.success(message);

        } catch (Exception e) { /* ignore */ }
    }
}