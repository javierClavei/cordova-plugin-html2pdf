package at.modalog.cordova.plugin.html2pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import android.R.bool;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;



import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintAttributes.MediaSize;
//import android.print.PrintDocumentInfo;
//import android.print.PrintDocumentInfo.Builder;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.os.CancellationSignal;
import android.os.Bundle;
import android.print.PageRange;
import android.os.ParcelFileDescriptor;
import android.print.pdf.PrintedPdfDocument;
import android.util.SparseIntArray;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

import android.print.PrintAttributes.Resolution;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;





import android.printservice.PrintJob;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.util.Log;

import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Html2pdf extends CordovaPlugin
{
   /**
    * Constructor.
    */
    public Html2pdf() {
	
    }
    
    
    @Override
    public boolean execute (String action, JSONArray args, CallbackContext callbackContext) throws JSONException
    {
	
	try{     
		
		String html = "<html><head></head><body>" + args.getString(0) + "</body></html>";        
	
		Document doc = new Document();
	
		InputStream in = new ByteArrayInputStream(html.getBytes());
		
		File sdCard = Environment.getExternalStorageDirectory();
		
		PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(sdCard.getAbsolutePath() + "/out.pdf")); 
	
		doc.open();
	
		XMLWorkerHelper.getInstance().parseXHtml(pdf, doc,in);
	
		doc.close();
		in.close();
		
		// send success result to cordova
		PluginResult result = new PluginResult(PluginResult.Status.OK);
		result.setKeepCallback(false); 
		callbackContext.sendPluginResult(result);
		
		return true;
	}
	catch(Exception e){     
		System.out.println(e.getMessage()); 
		
		return false;
	}
	
	return false;
    }
	
}
