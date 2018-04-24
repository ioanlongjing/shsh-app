package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzre;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzrv;
import com.google.android.gms.internal.zzrw;
import com.google.android.gms.internal.zzry;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsv;
import com.google.android.gms.internal.zztf;
import com.google.android.gms.internal.zztg;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Tracker extends zzru {
    private final Map<String, String> zzFs = new HashMap();
    private boolean zzabr;
    private final Map<String, String> zzabs = new HashMap();
    private final zzsv zzabt;
    private final zza zzabu;
    private ExceptionReporter zzabv;
    private zztf zzabw;

    private class zza extends zzru implements zza {
        final /* synthetic */ Tracker zzabE;
        private boolean zzabF;
        private int zzabG;
        private long zzabH = -1;
        private boolean zzabI;
        private long zzabJ;

        protected zza(Tracker tracker, zzrw com_google_android_gms_internal_zzrw) {
            this.zzabE = tracker;
            super(com_google_android_gms_internal_zzrw);
        }

        private void zzmv() {
            if (this.zzabH >= 0 || this.zzabF) {
                zzlT().zza(this.zzabE.zzabu);
            } else {
                zzlT().zzb(this.zzabE.zzabu);
            }
        }

        public void enableAutoActivityTracking(boolean z) {
            this.zzabF = z;
            zzmv();
        }

        public void setSessionTimeout(long j) {
            this.zzabH = j;
            zzmv();
        }

        protected void zzmr() {
        }

        public synchronized boolean zzmu() {
            boolean z;
            z = this.zzabI;
            this.zzabI = false;
            return z;
        }

        boolean zzmw() {
            return zznq().elapsedRealtime() >= this.zzabJ + Math.max(1000, this.zzabH);
        }

        public void zzo(Activity activity) {
            if (this.zzabG == 0 && zzmw()) {
                this.zzabI = true;
            }
            this.zzabG++;
            if (this.zzabF) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    this.zzabE.setCampaignParamsOnNextHit(intent.getData());
                }
                Map hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                this.zzabE.set("&cd", this.zzabE.zzabw != null ? this.zzabE.zzabw.zzr(activity) : activity.getClass().getCanonicalName());
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    CharSequence zzq = Tracker.zzq(activity);
                    if (!TextUtils.isEmpty(zzq)) {
                        hashMap.put("&dr", zzq);
                    }
                }
                this.zzabE.send(hashMap);
            }
        }

        public void zzp(Activity activity) {
            this.zzabG--;
            this.zzabG = Math.max(0, this.zzabG);
            if (this.zzabG == 0) {
                this.zzabJ = zznq().elapsedRealtime();
            }
        }
    }

    Tracker(zzrw com_google_android_gms_internal_zzrw, String str, zzsv com_google_android_gms_internal_zzsv) {
        super(com_google_android_gms_internal_zzrw);
        if (str != null) {
            this.zzFs.put("&tid", str);
        }
        this.zzFs.put("useSecure", "1");
        this.zzFs.put("&a", Integer.toString(new Random().nextInt(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) + 1));
        if (com_google_android_gms_internal_zzsv == null) {
            this.zzabt = new zzsv("tracking", zznq());
        } else {
            this.zzabt = com_google_android_gms_internal_zzsv;
        }
        this.zzabu = new zza(this, com_google_android_gms_internal_zzrw);
    }

    private static boolean zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        entry.getValue();
        return str.startsWith("&") && str.length() >= 2;
    }

    private static String zzb(Entry<String, String> entry) {
        return !zza((Entry) entry) ? null : ((String) entry.getKey()).substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzac.zzw(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (zzb != null) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzac.zzw(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (!(zzb == null || map2.containsKey(zzb))) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    private boolean zzms() {
        return this.zzabv != null;
    }

    static String zzq(Activity activity) {
        zzac.zzw(activity);
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        CharSequence stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return !TextUtils.isEmpty(stringExtra) ? stringExtra : null;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzabr = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzabu.enableAutoActivityTracking(z);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.zzms();	 Catch:{ all -> 0x0026 }
        if (r0 != r4) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
    L_0x0008:
        return;
    L_0x0009:
        if (r4 == 0) goto L_0x0029;
    L_0x000b:
        r0 = r3.getContext();	 Catch:{ all -> 0x0026 }
        r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ all -> 0x0026 }
        r2 = new com.google.android.gms.analytics.ExceptionReporter;	 Catch:{ all -> 0x0026 }
        r2.<init>(r3, r1, r0);	 Catch:{ all -> 0x0026 }
        r3.zzabv = r2;	 Catch:{ all -> 0x0026 }
        r0 = r3.zzabv;	 Catch:{ all -> 0x0026 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x0026 }
        r0 = "Uncaught exceptions will be reported to Google Analytics";
        r3.zzbO(r0);	 Catch:{ all -> 0x0026 }
    L_0x0024:
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        goto L_0x0008;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        throw r0;
    L_0x0029:
        r0 = r3.zzabv;	 Catch:{ all -> 0x0026 }
        r0 = r0.zzlU();	 Catch:{ all -> 0x0026 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x0026 }
        r0 = "Uncaught exceptions will not be reported to Google Analytics";
        r3.zzbO(r0);	 Catch:{ all -> 0x0026 }
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public String get(String str) {
        zznA();
        return TextUtils.isEmpty(str) ? null : this.zzFs.containsKey(str) ? (String) this.zzFs.get(str) : str.equals("&ul") ? zztg.zza(Locale.getDefault()) : str.equals("&cid") ? zznw().zzop() : str.equals("&sr") ? zznz().zzpc() : str.equals("&aid") ? zzny().zznX().zzjI() : str.equals("&an") ? zzny().zznX().zzmx() : str.equals("&av") ? zzny().zznX().zzmy() : str.equals("&aiid") ? zzny().zznX().zzmz() : null;
    }

    public void send(Map<String, String> map) {
        final long currentTimeMillis = zznq().currentTimeMillis();
        if (zzlT().getAppOptOut()) {
            zzbP("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        final boolean isDryRunEnabled = zzlT().isDryRunEnabled();
        final Map hashMap = new HashMap();
        zzb(this.zzFs, hashMap);
        zzb(map, hashMap);
        final boolean zzg = zztg.zzg((String) this.zzFs.get("useSecure"), true);
        zzc(this.zzabs, hashMap);
        this.zzabs.clear();
        final String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zznr().zzg(hashMap, "Missing hit type parameter");
            return;
        }
        final String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zznr().zzg(hashMap, "Missing tracking id parameter");
            return;
        }
        final boolean zzmt = zzmt();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzFs.get("&a")) + 1;
                if (parseInt >= ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) {
                    parseInt = 1;
                }
                this.zzFs.put("&a", Integer.toString(parseInt));
            }
        }
        zznt().zzg(new Runnable(this) {
            final /* synthetic */ Tracker zzabE;

            public void run() {
                boolean z = true;
                if (this.zzabE.zzabu.zzmu()) {
                    hashMap.put("sc", "start");
                }
                zztg.zzd(hashMap, "cid", this.zzabE.zzlT().zzlX());
                String str = (String) hashMap.get("sf");
                if (str != null) {
                    double zza = zztg.zza(str, 100.0d);
                    if (zztg.zza(zza, (String) hashMap.get("cid"))) {
                        this.zzabE.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                        return;
                    }
                }
                zzrr zzb = this.zzabE.zznx();
                if (zzmt) {
                    zztg.zzb(hashMap, "ate", zzb.zzmV());
                    zztg.zzc(hashMap, "adid", zzb.zznf());
                } else {
                    hashMap.remove("ate");
                    hashMap.remove("adid");
                }
                zzre zznX = this.zzabE.zzny().zznX();
                zztg.zzc(hashMap, "an", zznX.zzmx());
                zztg.zzc(hashMap, "av", zznX.zzmy());
                zztg.zzc(hashMap, "aid", zznX.zzjI());
                zztg.zzc(hashMap, "aiid", zznX.zzmz());
                hashMap.put("v", "1");
                hashMap.put("_v", zzrv.zzacP);
                zztg.zzc(hashMap, "ul", this.zzabE.zznz().zzpb().getLanguage());
                zztg.zzc(hashMap, "sr", this.zzabE.zznz().zzpc());
                boolean z2 = str.equals("transaction") || str.equals("item");
                if (z2 || this.zzabE.zzabt.zzpv()) {
                    long zzce = zztg.zzce((String) hashMap.get("ht"));
                    if (zzce == 0) {
                        zzce = currentTimeMillis;
                    }
                    if (isDryRunEnabled) {
                        this.zzabE.zznr().zzc("Dry run enabled. Would have sent hit", new zzst(this.zzabE, hashMap, zzce, zzg));
                        return;
                    }
                    String str2 = (String) hashMap.get("cid");
                    Map hashMap = new HashMap();
                    zztg.zza(hashMap, "uid", hashMap);
                    zztg.zza(hashMap, "an", hashMap);
                    zztg.zza(hashMap, "aid", hashMap);
                    zztg.zza(hashMap, "av", hashMap);
                    zztg.zza(hashMap, "aiid", hashMap);
                    String str3 = str2;
                    if (TextUtils.isEmpty((CharSequence) hashMap.get("adid"))) {
                        z = false;
                    }
                    hashMap.put("_s", String.valueOf(this.zzabE.zzlZ().zza(new zzry(0, str2, str3, z, 0, hashMap))));
                    this.zzabE.zzlZ().zza(new zzst(this.zzabE, hashMap, zzce, zzg));
                    return;
                }
                this.zzabE.zznr().zzg(hashMap, "Too many hits sent too quickly, rate limiting invoked");
            }
        });
    }

    public void set(String str, String str2) {
        zzac.zzb((Object) str, (Object) "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzFs.put(str, str2);
        }
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zztg.zzW(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            CharSequence queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                str = parse.getQueryParameter("utm_id");
                if (str != null) {
                    this.zzabs.put("&ci", str);
                }
                str = parse.getQueryParameter("anid");
                if (str != null) {
                    this.zzabs.put("&anid", str);
                }
                str = parse.getQueryParameter("utm_campaign");
                if (str != null) {
                    this.zzabs.put("&cn", str);
                }
                str = parse.getQueryParameter("utm_content");
                if (str != null) {
                    this.zzabs.put("&cc", str);
                }
                str = parse.getQueryParameter("utm_medium");
                if (str != null) {
                    this.zzabs.put("&cm", str);
                }
                str = parse.getQueryParameter("utm_source");
                if (str != null) {
                    this.zzabs.put("&cs", str);
                }
                str = parse.getQueryParameter("utm_term");
                if (str != null) {
                    this.zzabs.put("&ck", str);
                }
                str = parse.getQueryParameter("dclid");
                if (str != null) {
                    this.zzabs.put("&dclid", str);
                }
                str = parse.getQueryParameter("gclid");
                if (str != null) {
                    this.zzabs.put("&gclid", str);
                }
                valueOf = parse.getQueryParameter("aclid");
                if (valueOf != null) {
                    this.zzabs.put("&aclid", valueOf);
                }
            }
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            set("&sr", i + "x" + i2);
        } else {
            zzbR("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long j) {
        this.zzabu.setSessionTimeout(1000 * j);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zztg.zzW(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    void zza(zztf com_google_android_gms_internal_zztf) {
        zzbO("Loading Tracker config values");
        this.zzabw = com_google_android_gms_internal_zztf;
        if (this.zzabw.zzpS()) {
            String trackingId = this.zzabw.getTrackingId();
            set("&tid", trackingId);
            zza("trackingId loaded", trackingId);
        }
        if (this.zzabw.zzpT()) {
            trackingId = Double.toString(this.zzabw.zzpU());
            set("&sf", trackingId);
            zza("Sample frequency loaded", trackingId);
        }
        if (this.zzabw.zzpV()) {
            int sessionTimeout = this.zzabw.getSessionTimeout();
            setSessionTimeout((long) sessionTimeout);
            zza("Session timeout loaded", Integer.valueOf(sessionTimeout));
        }
        if (this.zzabw.zzpW()) {
            boolean zzpX = this.zzabw.zzpX();
            enableAutoActivityTracking(zzpX);
            zza("Auto activity tracking loaded", Boolean.valueOf(zzpX));
        }
        if (this.zzabw.zzpY()) {
            zzpX = this.zzabw.zzpZ();
            if (zzpX) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(zzpX));
        }
        enableExceptionReporting(this.zzabw.zzqa());
    }

    protected void zzmr() {
        this.zzabu.initialize();
        String zzmx = zzma().zzmx();
        if (zzmx != null) {
            set("&an", zzmx);
        }
        zzmx = zzma().zzmy();
        if (zzmx != null) {
            set("&av", zzmx);
        }
    }

    boolean zzmt() {
        return this.zzabr;
    }
}
