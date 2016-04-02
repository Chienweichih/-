package com.example.test;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		findViewById(R.id.scan).setOnClickListener(
			new Button.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
					integrator.initiateScan();
				}
			}
		);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
		TextView t = (TextView) findViewById(R.id.textView1);
		if (result != null)
		{
			String text = result.getContents();
			
			if (text != null)
				t.setText(text);
			else
				t.setText("Error");
			
			//VerTimeStamp verify = new VerTimeStamp(text,args[1]); //time,data
			//verify.VerifyTimeStamp();	
		}
	}
}