package com.olderlycare.mobile.olderlycare.service;

import com.olderlycare.mobile.olderlycare.data.Channel;

/**
 * Created by aquila on 27/09/2017.
 */

public interface ServiceCallback {
        void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}