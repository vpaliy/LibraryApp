package com.vpaliy.data.cache;


import com.google.gson.Gson;

/**
 * Json Serializer/Deserializer.
 */
public class Serializer<T> {

    private final Gson gson=new Gson();

    /**
     *
     * @param object to serialize
     *
     */
    public String serialize(Object object,Class<T> clazz) {
        return gson.toJson(object,clazz);
    }


    /**
     * Deserialize a json representation of an object.
     *
     * @param string A json string to deserialize.
     */
    public T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }
}
