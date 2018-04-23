package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Music;

public class DefaultApiImpl implements DefaultApi {

	@Override
	public Music musicGet() {
		// TODO Auto-generated method stub
		Music my =new Music();
		my.setCategory("rock");
		my.setId(555);
		my.setName("My fav implmentation kind");
		my.setRegion("Americas");
		return my;
	}

	@Override
	public Music musicPost(Music body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void musicPut(Music body) {
		// TODO Auto-generated method stub

	}

}
