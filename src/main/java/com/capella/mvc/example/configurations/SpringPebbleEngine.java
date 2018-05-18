package com.capella.mvc.example.configurations;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.spring4.extension.SpringExtension;

import java.util.concurrent.Executors;

/**
 * Copyright 2018 (c) Mastek UK Ltd
 * <p>
 * Created on : 24/03/2018
 *
 * @author Ramesh Rajendran
 */
public class SpringPebbleEngine {
    private Loader loader;
    private SpringExtension extension;

    public SpringPebbleEngine(Loader loader, SpringExtension springExtension) {
        this.loader = loader;
        this.extension = springExtension;
    }

    public PebbleEngine build() {
        return new PebbleEngine.Builder()
                .loader(this.loader)
                .extension(this.extension)
                .executorService(Executors.newFixedThreadPool(1000))
                .build();
    }
}
