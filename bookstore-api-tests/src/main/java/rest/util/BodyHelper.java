package rest.util;

import java.time.Instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.InstantTypeAdapter;

public class BodyHelper {
	public static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
        .create();
}
