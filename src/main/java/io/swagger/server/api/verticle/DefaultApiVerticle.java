package io.swagger.server.api.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import io.swagger.server.api.model.Music;

import java.util.List;
import java.util.Map;

public class DefaultApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(DefaultApiVerticle.class); 
    
    final static String GET_MUSIC_SERVICE_ID = "GET_music";
    final static String POST_MUSIC_SERVICE_ID = "POST_music";
    final static String PUT_MUSIC_SERVICE_ID = "PUT_music";
    
    //TODO : create Implementation
    DefaultApi service = new DefaultApiImpl();

    @Override
    public void start() throws Exception {
        
        //Consumer for GET_music
        vertx.eventBus().<JsonObject> consumer(GET_MUSIC_SERVICE_ID).handler(message -> {
            try {
                
                Music result = service.musicGet();
                message.reply(new JsonObject(Json.encode(result)).encodePrettily());
            } catch (Exception e) {
                //TODO : replace magic number (101)
                message.fail(101, e.getLocalizedMessage());
            }
        });
        
        //Consumer for POST_music
        vertx.eventBus().<JsonObject> consumer(POST_MUSIC_SERVICE_ID).handler(message -> {
            try {
                Music body = Json.mapper.readValue(message.body().getJsonObject("body").encode(), Music.class);
                
                Music result = service.musicPost(body);
                message.reply(new JsonObject(Json.encode(result)).encodePrettily());
            } catch (Exception e) {
                //TODO : replace magic number (101)
                message.fail(101, e.getLocalizedMessage());
            }
        });
        
        //Consumer for PUT_music
        vertx.eventBus().<JsonObject> consumer(PUT_MUSIC_SERVICE_ID).handler(message -> {
            try {
                Music body = Json.mapper.readValue(message.body().getJsonObject("body").encode(), Music.class);
                
                service.musicPut(body);
                message.reply(null);
            } catch (Exception e) {
                //TODO : replace magic number (101)
                message.fail(101, e.getLocalizedMessage());
            }
        });
        
    }
}