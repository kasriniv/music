package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Music;

import java.util.List;
import java.util.Map;

public interface DefaultApi  {
    //GET_music
    public Music musicGet();
    
    //POST_music
    public Music musicPost(Music body);
    
    //PUT_music
    public void musicPut(Music body);
    
}
