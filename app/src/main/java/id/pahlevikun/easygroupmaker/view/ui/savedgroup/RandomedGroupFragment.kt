/*
 * StoryFragment Created with ♥ by Farhan Yuda Pahlevi on 12/27/17 12:57 PM
 * Copyright (c) 2017. All Rights Reserved.
 *
 * Last Modified 12/27/17 11:51 AM
 */

package id.pahlevikun.easygroupmaker.view.ui.savedgroup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.pahlevikun.easygroupmaker.R


class RandomedGroupFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_user_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}