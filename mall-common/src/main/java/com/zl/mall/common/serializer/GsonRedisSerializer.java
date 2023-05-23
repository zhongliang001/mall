package com.zl.mall.common.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.google.gson.Gson;

public class GsonRedisSerializer implements RedisSerializer<Object>{

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		Gson gson = new Gson();
		String json = gson.toJson(t);
		return json.getBytes();
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		String str = new String(bytes);
		Gson gson = new Gson();
		return gson.fromJson(str, Object.class);
	}

}
