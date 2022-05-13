package com.prot.sf.meta.poc;

import com.prot.sf.meta.MetadataLoginUtil;
import com.sforce.soap.metadata.*;

/**
 * @Author: <a href="mailto: pengcheng.zhou@gmail.com">PengCheng Zhou</a>
 * @Created: 2022-05-12T19:40 Thursday
 */
public class CRUDSampleDelete {
    private MetadataConnection metadataConnection;

    // one second in milliseconds
    private static final long ONE_SECOND = 1000;

    public CRUDSampleDelete() {
    }

    public static void main(String[] args) throws Exception {
        CRUDSampleDelete crudSample = new CRUDSampleDelete();
        crudSample.runDelete();
    }

    /**
     * Create a custom object. This method demonstrates usage of the
     * create() and checkStatus() calls.
     *
     * @param uniqueNames Custom object name should be unique.
     */
    private void deleteCustomObjectSync(final String... uniqueNames) throws Exception {
        DeleteResult[] results = metadataConnection.deleteMetadata("CustomObject", uniqueNames);
        int i = 0;
        for (DeleteResult result: results) {
            i++;
            System.out.printf("result-%02d : %s%n", i, result);
        }
    }

    private void runDelete() throws Exception {
        metadataConnection = MetadataLoginUtil.login();
        // Custom objects and fields must have __c suffix in the full name.
        final String uniqueObjectName1 = "prot__MyCustomObject__c";
        final String uniqueObjectName2 = "prot__YourCustomObject__c";
        deleteCustomObjectSync(uniqueObjectName1, uniqueObjectName2);
    }
}

