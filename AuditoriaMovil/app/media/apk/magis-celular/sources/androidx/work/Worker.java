package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import f1.d;
/* loaded from: classes.dex */
public abstract class Worker extends ListenableWorker {
    public d f;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Worker.this.f.o(Worker.this.r());
            } catch (Throwable th) {
                Worker.this.f.p(th);
            }
        }
    }

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture p() {
        this.f = d.s();
        c().execute(new a());
        return this.f;
    }

    public abstract ListenableWorker.a r();
}
