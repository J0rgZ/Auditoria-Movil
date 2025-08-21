package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
/* loaded from: classes.dex */
public final class g0 {
    public TextView a;
    public TextClassifier b;

    public g0(TextView textView) {
        this.a = (TextView) u.i.d(textView);
    }

    public TextClassifier a() {
        Object systemService;
        TextClassifier textClassifier;
        TextClassifier textClassifier2;
        TextClassifier textClassifier3 = this.b;
        if (textClassifier3 == null) {
            systemService = this.a.getContext().getSystemService(c0.a());
            TextClassificationManager a = d0.a(systemService);
            if (a != null) {
                textClassifier2 = a.getTextClassifier();
                return textClassifier2;
            }
            textClassifier = TextClassifier.NO_OP;
            return textClassifier;
        }
        return textClassifier3;
    }

    public void b(TextClassifier textClassifier) {
        this.b = textClassifier;
    }
}
