/*
 * Copyright (c) 2020 Proton Technologies AG
 * 
 * This file is part of ProtonMail.
 * 
 * ProtonMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ProtonMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ProtonMail. If not, see https://www.gnu.org/licenses/.
 */
package ch.protonmail.android.tasks

import android.text.TextUtils
import android.util.Pair
import android.webkit.WebView

import org.apache.http.protocol.HTTP

import java.lang.ref.WeakReference

import ch.protonmail.android.events.DownloadEmbeddedImagesEvent

/**
 * Created by kadrikj on 12/28/18.
 */
class EmbeddedImagesThread(private val viewReference: WeakReference<WebView>, event: DownloadEmbeddedImagesEvent, messageContent: String) : AbstractEmbeddedImagesThread(event, messageContent, "") {

    override fun onPostExecute(pair: Pair<String, String>) {
        val content = pair.first
        if (TextUtils.isEmpty(content)) {
            return
        }
        val webView = viewReference.get() ?: return
        webView.loadDataWithBaseURL("", content, "text/html", HTTP.UTF_8, "")
    }
}
