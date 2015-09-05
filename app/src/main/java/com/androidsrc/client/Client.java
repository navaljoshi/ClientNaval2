package com.androidsrc.client;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, Void> {

	String dstAddress;
	int dstPort;
	String response = "";
	TextView textResponse;

	Client(String addr, int port,TextView textResponse) {
		dstAddress = addr;
		dstPort = port;
		this.textResponse=textResponse;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		Log.d("naval", "1");

		Socket socket = null;
		OutputStream outputStream;

		try {
			socket = new Socket(dstAddress, dstPort);
			Log.d("naval", "2");

			/* send some text*/
			outputStream = socket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print("naval");
			Log.d("naval", "3");
			printStream.close();
			Log.d("naval", "4");

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
					1024);
			byte[] buffer = new byte[1024];
			Log.d("naval", "5");


		} catch (UnknownHostException e) {
			Log.d("naval", "8");
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "UnknownHostException: " + e.toString();
		} catch (IOException e) {
			Log.d("naval", "9");
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "IOException: " + e.toString();
		} finally {
			Log.d("naval", "10");
			if (socket != null) {
				try {
					Log.d("naval", "11");
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		textResponse.setText(response);
		super.onPostExecute(result);
	}

}
