/*
 * MyFirebaseInstanceIDService Created with ♥ by Farhan Yuda Pahlevi on 12/27/17 12:57 PM
 * Copyright (c) 2017. All Rights Reserved.
 *
 * Last Modified 12/27/17 9:30 AM
 */

package id.pahlevikun.easygroupmaker.composer.service

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by farhan on 11/27/17.
 */

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token
    }

}