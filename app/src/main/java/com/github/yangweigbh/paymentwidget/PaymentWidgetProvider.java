package com.github.yangweigbh.paymentwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by yangwei on 2017/1/12.
 */

public class PaymentWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_layout);

            views.setOnClickPendingIntent(R.id.item1, getWechatBarcodeIntent(context));

            views.setOnClickPendingIntent(R.id.item2, getAlipayBarcodeIntent(context));

            views.setOnClickPendingIntent(R.id.item3, getAlipayPaycodeIntent(context));

            views.setImageViewResource(R.id.icon1, R.drawable.wx_shoukuan);
            views.setImageViewResource(R.id.icon2, R.drawable.alipay_scan);
            views.setImageViewResource(R.id.icon3, R.drawable.alipay_fukuan);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private PendingIntent getWechatBarcodeIntent(Context context) {
        Uri uri = Uri.parse("weixin://dl/scan");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private PendingIntent getAlipayBarcodeIntent(Context context) {
        Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private PendingIntent getAlipayPaycodeIntent(Context context) {
        Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=20000056");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}
