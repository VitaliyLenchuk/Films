package com.vitaliylenchuk.films;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.List;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {




    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        ComponentName thisWidget = new ComponentName(context, NewAppWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        FilmLab filmLab = FilmLab.get(context);
        List<Film> films = filmLab.getFilms();
        if (films.size()!=0){
        Film film = films.get(new Random().nextInt(films.size()));
        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews =
                    new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            // Set the text
            remoteViews.setTextViewText(R.id.wid_title, film.getTitle());
            remoteViews.setImageViewResource(R.id.wid_poster, film.getPoster());
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}