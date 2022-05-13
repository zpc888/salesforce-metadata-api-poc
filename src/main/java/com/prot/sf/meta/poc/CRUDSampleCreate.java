package com.prot.sf.meta.poc;

import com.prot.sf.meta.MetadataLoginUtil;
import com.sforce.soap.metadata.*;

/**
 * @Author: <a href="mailto: pengcheng.zhou@gmail.com">PengCheng Zhou</a>
 * @Created: 2022-05-12T19:40 Thursday
 */
public class CRUDSampleCreate {
    private MetadataConnection metadataConnection;

    // one second in milliseconds
    private static final long ONE_SECOND = 1000;

    public CRUDSampleCreate() {
    }

    public static void main(String[] args) throws Exception {
        CRUDSampleCreate crudSample = new CRUDSampleCreate();
        crudSample.runCreate();
    }

    /**
     * Create a custom object. This method demonstrates usage of the
     * create() and checkStatus() calls.
     *
     * @param uniqueName Custom object name should be unique.
     */
    private void createCustomObjectSync(final String uniqueName) throws Exception {
        final String objectLabel = "My Custom Object";
        CustomObject co = new CustomObject();
        co.setFullName(uniqueName);
        co.setDeploymentStatus(DeploymentStatus.Deployed);
        co.setDescription("Created by the Metadata API Sample");
        co.setEnableActivities(true);
        co.setLabel(objectLabel);
        co.setPluralLabel(objectLabel + "s");
        co.setSharingModel(SharingModel.ReadWrite);

        // The name field appears in page layouts, related lists, and elsewhere.

        CustomField nf = new CustomField();
        nf.setType(FieldType.Text);
        nf.setDescription("The custom object identifier on page layouts, related lists etc");
        nf.setLabel(objectLabel);
        nf.setFullName(uniqueName);
        co.setNameField(nf);

        final String fieldUniqueName = "MyCustomField__c";
        final String fieldLabel = "My Custom Field";
        CustomField[] fields = new CustomField[1];
        fields[0] = new CustomField();
        fields[0].setType(FieldType.Text);
        fields[0].setLength(128);
        fields[0].setLabel(fieldLabel);
        fields[0].setFullName(fieldUniqueName);
        co.setFields(fields);

        SaveResult[] results = metadataConnection
                .createMetadata(new Metadata[] { co });

        for (SaveResult r : results) {
            if (r.isSuccess()) {
                System.out.println("Created component: " + r.getFullName());
            } else {
                System.out
                        .println("Errors were encountered while creating "
                                + r.getFullName());
                for (com.sforce.soap.metadata.Error e : r.getErrors()) {
                    System.out.println("Error message: " + e.getMessage());
                    System.out.println("Status code: " + e.getStatusCode());
                }
            }
        }
    }

    private void runCreate() throws Exception {
        metadataConnection = MetadataLoginUtil.login();
        // Custom objects and fields must have __c suffix in the full name.
        final String uniqueObjectName = "MyCustomObject__c";
        createCustomObjectSync(uniqueObjectName);
    }
}

