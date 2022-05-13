package com.prot.sf.meta.poc;

import com.prot.sf.meta.MetadataLoginUtil;
import com.sforce.soap.metadata.SaveResult;
import com.sforce.soap.metadata.MetadataConnection;

/**
 * @Author: <a href="mailto: pengcheng.zhou@gmail.com">PengCheng Zhou</a>
 * @Created: 2022-05-12T19:40 Thursday
 */
public class CRUDSampleRename {
    private MetadataConnection metadataConnection;

    // one second in milliseconds
    private static final long ONE_SECOND = 1000;

    public CRUDSampleRename() {
    }

    public static void main(String[] args) throws Exception {
        CRUDSampleRename crudSample = new CRUDSampleRename();
        crudSample.runRename();
    }

    /**
     * Create a custom object. This method demonstrates usage of the
     * create() and checkStatus() calls.
     *
     */
    private void renameMetadataSync() throws Exception {
        final String uniqueObjectName1 = "prot__MyCustomObject__c";
        final String uniqueObjectName2 = "prot__YourCustomObject__c";
        try {
            SaveResult result = metadataConnection.renameMetadata("CustomField", "prot__MyCustomObject__c.prot__MyCustomField__c", "prot__MyCustomObject__c.prot__YourCustomField__c");
            System.out.printf("rename-field--result: %s%n", result);
        } catch (Exception ex) {
            System.out.println("ignore renaming field exception: " + ex.getMessage());
        }
        SaveResult result = metadataConnection.renameMetadata("CustomObject", "prot__MyCustomObject__c", "prot__YourCustomObject__c");
        System.out.printf("rename-object-result: %s%n", result);
    }

    private void runRename() throws Exception {
        metadataConnection = MetadataLoginUtil.login();
        // Custom objects and fields must have __c suffix in the full name.
        renameMetadataSync();
    }
}

