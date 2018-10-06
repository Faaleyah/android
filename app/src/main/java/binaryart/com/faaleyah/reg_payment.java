package binaryart.com.faaleyah;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;


public class reg_payment extends AppCompatActivity {
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)

            .clientId("AbCo3ryKuececWzRgrLMnP7V0n0oyuzVhKyPH-_QroNdRrDVgRdgDHvfSGtYiO18UJdO8L-xZ37dwoy6");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_payment);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.
                    Double am =Double.parseDouble( "50");

                    Toast.makeText(reg_payment.this, "تمت عملية الدفع بنجاح ,ادخل الكود المرسل من قبل الفئة المنظمة ب فعاليات قائمة لتفاصيل الحجز", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    Toast.makeText(reg_payment.this, "an extremely unlikely failure occurred: ", Toast.LENGTH_SHORT).show();

                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(reg_payment.this, "The user canceled.", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(reg_payment.this, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onDestroy() {
        this.stopService(new Intent(reg_payment.this, PayPalService.class));
        super.onDestroy();
    }


    public void paying(View view) {
        PayPalPayment payment = new PayPalPayment(new BigDecimal("50"), "USD", "SaudiPayCoins",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(reg_payment.this, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, 0);
    }

    public void gotomypoints(View view) {
        Intent intent = new Intent(this, mypoints.class);
        startActivity(intent);
    }
}
