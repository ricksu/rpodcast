package com.podcast.rick.rpodcast;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Xml;

import com.podcast.rick.database.PodcastProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContentResolver;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Rick on 2017/3/31.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RssReaderTest {



    @Before
    public void setUp() throws Exception {
        PodcastProvider provider = new PodcastProvider();
        provider.onCreate();
        Robolectric.setupContentProvider(PodcastProvider.class);
        ShadowContentResolver.registerProviderInternal("com.rick.podcast", provider);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insertPodcastItem() throws Exception {

    }

    @Test
    public void parseRSS() throws Exception {
        ContentResolver contentResolver = RuntimeEnvironment.application.getContentResolver();
        Cursor cursor = contentResolver.query(PodcastProvider.CONTENT_URI, null, null, null, null);
        assertNotNull(cursor);

        String fileName = "app.iml";
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        File f = new File(resource.getPath());

        FileInputStream fi = new FileInputStream(f);

        XmlPullParser xpp = Xml.newPullParser();
        assertNotNull(xpp);

        RssReader rssReader = new RssReader();
        rssReader.parseRSS(fi);

        assertNotNull(f);
    }
}