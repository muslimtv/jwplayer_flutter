package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import androidx.annotation.Nullable;

public class VideoAdManager {

    @Nullable
    private static VideoAdManager videoAdManager = null;

    public boolean isPlayingAd;

    public PlayerLayout playerLayout = null;

    private VideoAdManager(){}

    static VideoAdManager instance() {
        if(videoAdManager == null){
            videoAdManager = new VideoAdManager();
        }
        return videoAdManager;
    }
}
