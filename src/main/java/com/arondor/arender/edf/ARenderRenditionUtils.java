package com.arondor.arender.edf;

import com.arondor.arender.viewer.common.rest.DocumentServiceRestClient;
import com.arondor.viewer.client.api.document.DocumentId;
import com.arondor.viewer.common.document.id.DocumentIdFactory;
import com.arondor.viewer.rendition.api.document.DocumentAccessorSelector;

import java.io.InputStream;

/**
 * Created by The ARender Team on 27/06/2018.
 */
public class ARenderRenditionUtils
{
    /**
     * Method to call to fetch the PDF version of a document
     * @param clientAddress: ARender Rendition Adress (default is : http://localhost:1990/)
     * @param documentToConvert: the document to convert into PDF
     * @param mimeType: optional: the MIME TYPE of the document to convert
     * @param documentTitle: optional: the document title of the document to convert
     * @param contentSize:  optional: the content size of the document to convert
     * @return
     */
    public InputStream getPDFDocument(String clientAddress, byte[] documentToConvert, String mimeType,
            String documentTitle, long contentSize)
    {
        InputStream pdfDocument;
        DocumentId documentId = DocumentIdFactory.getInstance().generate();
        DocumentServiceRestClient documentServiceRestClient = new DocumentServiceRestClient();
        documentServiceRestClient.setAddress(clientAddress);
        documentServiceRestClient.startLoadPartialDocument(documentId, mimeType, documentTitle, contentSize);
        documentServiceRestClient.loadPartialDocument(documentId, documentToConvert, 0, true);
        pdfDocument = documentServiceRestClient
                .getDocumentAccessorContent(documentId, DocumentAccessorSelector.RENDERED);
        return pdfDocument;

    }
}