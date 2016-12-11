package net.xshivan.excercise3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

public class AuthorWebsiteNavigator {
    public static void promptToVisit(final AppCompatActivity activity) {
        new AlertDialog.Builder(activity)
                .setMessage("Would you like to visit author's website?")
                .setCancelable(true)
                .setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.michal-cywinski.pl"));
                        activity.startActivity(browserIntent);
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }
}
