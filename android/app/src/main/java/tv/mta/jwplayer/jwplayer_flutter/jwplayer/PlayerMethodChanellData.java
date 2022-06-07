package tv.mta.jwplayer.jwplayer_flutter.jwplayer;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayerMethodChanellData {
    @Nullable
    public  String file;
    public  boolean autoPlay;
    public List<VideoAdConfig> adConfigs;

    public PlayerMethodChanellData(@Nullable String file, boolean autoPlay, List<VideoAdConfig> adConfigs) {
        this.file = file;
        this.autoPlay = autoPlay;
        this.adConfigs = adConfigs;
    }

    public static PlayerMethodChanellData createFromMethodChannelObject(Object arguments) {
        JSONObject args = (JSONObject) arguments;
        String file = getFile(args);
        boolean autoPlay = getAutoPlay(args);
        String jsonStringAdConfigs = getJSONStringAdConfigs(args);
        Type listType = new TypeToken<List<VideoAdConfig>>(){}.getType();
        List<VideoAdConfig> videoAdConfigs = new ArrayList<>();
        if(jsonStringAdConfigs != null){
            videoAdConfigs  = new Gson().fromJson(jsonStringAdConfigs,listType);
        }
        return new PlayerMethodChanellData(file,autoPlay,videoAdConfigs);
    }

    @Nullable
    private static String getFile(JSONObject args) {
            if(args == null){
                return null;
            }
            try{
                return args.getString(PlayerArguments.FILE);
            }catch (JSONException e) {
                return null;
            }
    }

    @Nullable
    private static boolean getAutoPlay(JSONObject args) {
        if(args == null){
            return false;
        }
        try{
            return args.getBoolean(PlayerArguments.AUTO_PLAY);
        }catch (JSONException e) {
            return false;
        }
    }

    @Nullable
    private static String getJSONStringAdConfigs(JSONObject args) {
        if(args == null){
            return null;
        }
        try{
            return args.getString(PlayerArguments.AD_TAGS);
        }catch (JSONException e) {
            return null;
        }
    }


}




