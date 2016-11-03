package com.dhavalsharma.compare;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private int mId = 123456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendTestNotification();
        setupFragments();

    }

    private void setupFragments() {
        //set images in fragments
        HeroFragment roger = (HeroFragment) getFragmentManager().findFragmentById(R.id.fragmentUp);
        roger.setImageDrawable(R.drawable.roger);
        roger.setName(getString(R.string.roger));
        roger.showCup(true);

        HeroFragment nadal = (HeroFragment) getFragmentManager().findFragmentById(R.id.fragmentDown);
        nadal.setImageDrawable(R.drawable.rafael);
        nadal.setName(getString(R.string.rafael));
        nadal.showCup(false);
    }

    private void sendTestNotification() {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.trophy)
                .setContentTitle("Compare")
                .setContentText("Roger wins");

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Roger Federer has new win:");
        // Moves events into the expanded layout
        inboxStyle.addLine("16 wins");
        inboxStyle.addLine("4 losses");
        inboxStyle.addLine("80% win record");
        // Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());
    }

}
