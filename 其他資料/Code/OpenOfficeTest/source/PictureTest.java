import ooo.connector.BootstrapSocketConnector;

import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.XPropertySet;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.text.TextContentAnchorType;
import com.sun.star.text.XText;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextDocument;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class PictureTest {

    private XComponentContext mxRemoteContext = null;
    private XMultiComponentFactory mxRemoteServiceManager = null;

    private XMultiComponentFactory getRemoteServiceManager()
            throws java.lang.Exception {
        String oooExeFolder = "/opt/openoffice.org3/program";
        if (mxRemoteContext == null && mxRemoteServiceManager == null) {
            // get the remote office context. If necessary a new office
            // process is started
            // mxRemoteContext = com.sun.star.comp.helper.Bootstrap.bootstrap();
            mxRemoteContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
            System.out.println("Connected to a running office ...");
            mxRemoteServiceManager = mxRemoteContext.getServiceManager();
        }
        return mxRemoteServiceManager;
    }

    protected XComponent newDocComponent(String docType)
            throws java.lang.Exception {
        String loadUrl = "private:factory/" + docType;

        mxRemoteServiceManager = this.getRemoteServiceManager();
        Object desktop = mxRemoteServiceManager.createInstanceWithContext(
                "com.sun.star.frame.Desktop", mxRemoteContext);

        XComponentLoader xComponentLoader = (XComponentLoader) UnoRuntime
                .queryInterface(XComponentLoader.class, desktop);
        PropertyValue[] loadProps = new PropertyValue[0];

        return xComponentLoader.loadComponentFromURL(loadUrl, "_blank", 0,
                loadProps);
    }

    public void insertPic() throws Exception {

        XComponent xEmptyWriterComponent = newDocComponent("swriter");
        // query its XTextDocument interface to get the text
        XTextDocument xDoc = (XTextDocument) UnoRuntime.queryInterface(XTextDocument.class,
        xEmptyWriterComponent);
        XMultiServiceFactory xMSFDoc = (XMultiServiceFactory) UnoRuntime
                .queryInterface(XMultiServiceFactory.class, xDoc);

        //
        // XTextTable xTextTable = searchTable(xDoc, tableName);
        // XText xTableText = (XText) UnoRuntime.queryInterface(XText.class,
        // xTextTable.getCellByName(CellName));
        XText xText = xDoc.getText();
        XTextCursor xTextCursor = xText.createTextCursor();

        Object oGraphic = xMSFDoc
                .createInstance("com.sun.star.text.TextGraphicObject");
        XTextContent xTextContent = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, oGraphic);

        xTextCursor.gotoEnd(false);

        xText.insertTextContent(xTextCursor, xTextContent, true);

        XPropertySet xPropSet = (XPropertySet) UnoRuntime.queryInterface(
                XPropertySet.class, oGraphic);
        String sUrl = "/home/chienweichih/workspace/created_by_chienweichih.png";
        xPropSet.setPropertyValue("AnchorType",
                TextContentAnchorType.AT_PARAGRAPH_value);
        xPropSet.setPropertyValue("GraphicURL", sUrl);

        xText.insertString(xTextCursor, "\n", false);
    }
    public static void main(String[] args) {
        PictureTest test = new PictureTest();
        try {
            test.insertPic();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}