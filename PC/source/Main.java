import com.sun.star.text.XText;
import com.sun.star.uno.XComponentContext;

public class Main
{
	public static void main(String[] args)
	{		
		PDFMaker pdfMaker = new PDFMaker();
		
		XComponentContext xContext = pdfMaker.init();
		XText xText = pdfMaker.getDoc(xContext);
		
		SenRequest request = new SenRequest(xText.getString());
		byte[] tst = request.SendingRequest();
		
		ZipTool zip = new ZipTool();
		String in = new String(tst);//"something want to add in qrcode";		
		
		System.out.println(in.length());
		
		System.out.println(in.getBytes().length);
		System.out.println(zip.compress(in).length);
		
		String picPath = pdfMaker.makeQRCode( new String(zip.compress(in)) );
		
		pdfMaker.editDoc(xText,picPath);
		
		System.exit(0);
	}
}
