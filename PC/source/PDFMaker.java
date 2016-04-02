import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ooo.connector.BootstrapSocketConnector;

import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.XPropertyAccess;
import com.sun.star.beans.XPropertySet;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.document.XExporter;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.text.TextContentAnchorType;
import com.sun.star.text.XText;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextDocument;
import com.sun.star.ui.dialogs.XExecutableDialog;
import com.sun.star.uno.AnyConverter;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseable;

public class PDFMaker
{
	private XComponent m_xComponent;
	private XExporter m_xExporter;
	private XPropertyAccess m_xPropertyAccess;
	private XMultiServiceFactory m_xMSFDoc;
	private String m_filePath;
	private String m_picPath;
	
	public XComponentContext init()
	{		
		Object oPDFDialog = null;
		XComponentContext xContext = null;
		
		try
		{
			try
			{
				xContext = Bootstrap.bootstrap();
			}
			catch(BootstrapException e)
			{
				String msg = "Determine the folder of your OpenOffice.org executable.\n";
				msg += "On Windows it might be something like C:/Program Files/OpenOffice.org/program/\n";
				msg += "and on *nix for example something like /opt/openoffice.org/program  or  /usr/lib/openoffice.org/program";
				
				String oooExeFolder = JOptionPane.showInputDialog(null, msg);
				oooExeFolder = "C:/Program Files/OpenOffice.org 3/program/";
				//oooExeFolder = "/opt/openoffice.org3/program";
				xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
			}
			
			XMultiComponentFactory xMCF = xContext.getServiceManager();
			oPDFDialog = xMCF.createInstanceWithContext("com.sun.star.document.PDFDialog", xContext);
		}
		catch(java.lang.Exception ex)
		{
			ex.printStackTrace();
		}

		// get all interface references
		XExecutableDialog xExecutableDialog =(XExecutableDialog) UnoRuntime.queryInterface(XExecutableDialog.class, oPDFDialog);
		m_xExporter = (XExporter) UnoRuntime.queryInterface(XExporter.class, xExecutableDialog);
		m_xPropertyAccess = (XPropertyAccess) UnoRuntime.queryInterface(XPropertyAccess.class, m_xExporter);

		// check them all
		if (xExecutableDialog == null || m_xExporter == null || m_xPropertyAccess == null)
		{
			System.out.println("something went wrong with the PDFDialog!");
			return null;
		}
		
		return xContext;
	}
	
	public XText getDoc(XComponentContext xContext)
	{
		m_filePath = getFilePath();
		m_xComponent = null;
		
		try
		{
			
			XComponentLoader xComponentLoader = (XComponentLoader) UnoRuntime
					.queryInterface(
							XComponentLoader.class,
							xContext.getServiceManager()
									.createInstanceWithContext(
											"com.sun.star.frame.Desktop",
											xContext));
			
			m_xComponent = xComponentLoader.loadComponentFromURL(m_filePath, "_default", 0, new PropertyValue[0]);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		XTextDocument xDoc = (XTextDocument) UnoRuntime.queryInterface(XTextDocument.class,m_xComponent);
		m_xMSFDoc = (XMultiServiceFactory) UnoRuntime.queryInterface(XMultiServiceFactory.class, xDoc);

		return xDoc.getText();
	}
	
	public void editDoc(XText xText,String picPath)
	{
		XTextCursor xTextCursor = xText.createTextCursor();

        Object oGraphic;
		try
		{
			oGraphic = m_xMSFDoc.createInstance("com.sun.star.text.TextGraphicObject");
			
	        XTextContent xTextContent = (XTextContent) UnoRuntime.queryInterface(XTextContent.class, oGraphic);
	
	        xTextCursor.gotoEnd(false);
	
	        xText.insertTextContent(xTextCursor, xTextContent, true);
	
	        XPropertySet xPropSet = (XPropertySet) UnoRuntime.queryInterface(XPropertySet.class, oGraphic);
	        xPropSet.setPropertyValue("AnchorType",TextContentAnchorType.AT_PARAGRAPH_value);
	        xPropSet.setPropertyValue("GraphicURL", picPath);
	
	        xText.insertString(xTextCursor, "\n", false);
	        
	        // set the source document
	        m_xExporter.setSourceDocument(m_xComponent);
	        
	        genPDF();
		}
     	catch (java.lang.Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (m_xComponent != null)
			{
				try
				{
					XCloseable xCloseable = (XCloseable) UnoRuntime
							.queryInterface(XCloseable.class, m_xComponent);
					xCloseable.close(true);
				}
				catch (CloseVetoException ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	private void genPDF()
	{		
		
		//這裡不寫的話就直接生成！！
		/*
		// set the title
		xExecutableDialog.setTitle("Demo Export PDF Dialog OOo API");

		// execute the dialog
		int nResult = xExecutableDialog.execute();

		if (nResult != ExecutableDialogResults.OK)
		{
			System.out.println("you didn't press OK! so do nothing...");
			return;
		}*/

		// get the options the user has choosen
		PropertyValue[] aFilterData = null;

		PropertyValue[] aPropertyValues = m_xPropertyAccess.getPropertyValues();
		for (int i = 0; i < aPropertyValues.length; i++)
		{
			PropertyValue propertyValue = aPropertyValues[i];
			if (propertyValue.Name.equals("FilterData"))
			{
				try
				{
					aFilterData = (PropertyValue[]) AnyConverter.toObject(
							PropertyValue[].class, propertyValue.Value);
				} 
				catch (com.sun.star.lang.IllegalArgumentException ex)
				{
					ex.printStackTrace();
				}
				
				break;
			}
		}

		if (aFilterData != null)
		{
			// print the properties and their values
			printPropertyValues(aFilterData);

			// finally export!
			// location where to export the doc.
			
			String sURL = m_filePath.substring(0, m_filePath.length() - 3).concat("pdf");
			
			// choose the proper filter
			String sFilter = "writer_pdf_Export";

			boolean bExport = doExport(m_xComponent, sURL, sFilter, aFilterData);

			System.out.println((bExport) ? "\nExported!" : "\nCouldn't export the document!");
		}	
	}
	
	private boolean doExport(XComponent xComponent, String aURL,String sFilterName, PropertyValue[] aFilterData)
	{
		XStorable xStorable = (XStorable) UnoRuntime.queryInterface(XStorable.class, xComponent);
		if (xStorable == null)
		{
			return false;
		}
		else
		{
			try
			{
				PropertyValue[] aMediaDescriptor = new PropertyValue[2];

				aMediaDescriptor[0] = new PropertyValue();
				aMediaDescriptor[0].Name = "FilterName";
				aMediaDescriptor[0].Value = sFilterName;

				aMediaDescriptor[1] = new PropertyValue();
				aMediaDescriptor[1].Name = "FilterData";
				aMediaDescriptor[1].Value = aFilterData;

				xStorable.storeToURL(aURL, aMediaDescriptor);
				return true;
			}
			catch (IOException ex)
			{
				System.out.println("???");
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	private String getFilePath()
    {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		int returnVal = fileChooser.showOpenDialog(new JFrame());
		
        if(returnVal != JFileChooser.APPROVE_OPTION)
            System.exit(0);
       
        String path = fileChooser.getSelectedFile().getPath().replace('\\', '/');
        m_picPath = path.substring(0, path.length() - 3) + "gif";
        
        String head = "file://";
        if(!path.startsWith("/"))
        	head = head.concat("/");
        
        return head.concat(path);
    }
	
	public String makeQRCode(String source)
	{	
		ByteArrayOutputStream stream = QRCode.from(source).to(ImageType.GIF).withCharset("UTF-8").withSize(1000, 1000).stream();

		try 
		{
	        FileOutputStream fout = new FileOutputStream(new File(m_picPath));
	        
	        fout.write(stream.toByteArray());
	        fout.flush();
	        fout.close();
	    }
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		String head = "file://";
        if(!m_picPath.startsWith("/"))
        	head = head.concat("/");
		
		return head.concat(m_picPath);
	}
	
	//沒有也沒關係
	public void printPropertyValues(PropertyValue[] aPropertyValues)
	{
		System.out.println("\nProperties and values\n");

		for (PropertyValue property : aPropertyValues)
		{
			String sValue = "";
			try
			{
				sValue = String.valueOf(property.Value);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			System.out.println(property.Name + " = " + sValue);
		}
	}
}