package com.example.weekend6amazonbookstry2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;

import com.example.weekend6amazonbookstry2.adapters.RecyclerViewAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MainActivityTest
{
    private MainActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MainActivity.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveRecycler() throws Exception
    {
        assertNotNull( activity.findViewById(R.id.rvRecyclerView) );
    }

    @Test
    public void checkViewModelCall() throws Exception
    {
        assertNotNull(activity.activityMainBinding.getBookViewModel());
    }


//    @Test
//    public void checkClick(){
////        activity..performClick();
////        activity.activityMainBinding.rvRecyclerView.getAdapter();
//
//
//
//        activity.activityMainBinding.rvRecyclerView.measure(0, 0);
//        activity.activityMainBinding.rvRecyclerView.layout(0, 0, 100, 10000);
//        activity.activityMainBinding.rvRecyclerView.getChildAt(0).performClick();
//        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
//        assertEquals(Main2Activity.class.getCanonicalName(),
//                intent.getComponent().getClassName());
//    }
}
