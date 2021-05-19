/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.行走的建筑学院;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<VideoView> mimage;
    private WeakReference<TextView> mtitle;

    public FetchBook(VideoView image, TextView title) {
        this.mimage = new WeakReference<>(image);
        this.mtitle = new WeakReference<>(title);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            int i = 0;
            String title = null;
            String authors = null;
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                try {
                    title = book.getString("image");
                    authors = book.getString("title");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if (title != null && authors != null) {
               mimage.get().setVideoURI(Uri.parse(title));
               mtitle.get().setText(authors);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
