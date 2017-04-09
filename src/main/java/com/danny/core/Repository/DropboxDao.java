package com.danny.core.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

@Component
public class DropboxDao {

  @Value("${dropbox.appAuthAccessToken}")
  private String appAuthAccessToken;

  DbxClient dbxClient;

  public DropboxDao() {
    try {
      dbxClient = authDropbox();
    } catch (Exception e) {
      dbxClient = new DbxClient(
          new DbxRequestConfig("JavaDropboxTutorial/1.0", Locale.getDefault().toString()), "");
      e.printStackTrace();
    }
  }

  public DbxClient authDropbox() throws IOException, DbxException {
    DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("JavaDropboxTutorial/1.0",
        Locale.getDefault().toString(), AppengineHttpRequestor.Instance);
    /*
     * DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("JavaDropboxTutorial/1.0",
     * Locale.getDefault().toString());
     */

    dbxClient = new DbxClient(dbxRequestConfig, appAuthAccessToken);
    System.out.println(
        "Dropbox Account Name: " + appAuthAccessToken + dbxClient.getAccountInfo().displayName);

    return dbxClient;
  }

  /* returns Dropbox size in GB */
  public long getDropboxSize() throws DbxException {
    long dropboxSize = 0;
    DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
    // in GB
    dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
    return dropboxSize;
  }

  public void uploadToDropbox(String fileName) throws DbxException, IOException {
    File inputFile = new File(fileName);
    FileInputStream fis = new FileInputStream(inputFile);
    try {
      DbxEntry.File uploadedFile =
          dbxClient.uploadFile("/" + fileName, DbxWriteMode.add(), inputFile.length(), fis);
      String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
      System.out.println("Uploaded: " + uploadedFile.toString() + " URL " + sharedUrl);
    } finally {
      fis.close();
    }
  }

  public void createFolder(String folderName) throws DbxException {
    dbxClient.createFolder("/" + folderName);
  }

  public void listDropboxFolders(String folderPath) throws DbxException {
    DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(folderPath);
    System.out.println("Files List:");
    for (DbxEntry child : listing.children) {
      System.out.println("  " + child.name + ": " + child.toString());
    }
  }

  // public void downloadFromDropbox(String fileName) throws DbxException, IOException {
  // FileOutputStream outputStream = new FileOutputStream(fileName);
  // try {
  //
  // DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName, null, outputStream);
  // } finally {
  // outputStream.close();
  // }
  // }

  public String getAccessToken() {
    try {
      return dbxClient.getAccessToken();
    } catch (Exception e) {
      return "Error Happen";
    }
  }

  public DbxAccountInfo getAccountInfo() {
    try {
      return dbxClient.getAccountInfo();
    } catch (Exception e) {
      return new DbxAccountInfo(0, null, null, null, null, null, null, false);
    }
  }

  public String startGetFile(String fileName) throws DbxException, IOException {
    DbxClient.Downloader downloader = dbxClient.startGetFile(fileName, null);
    InputStream is = null;
    BufferedReader bufferedReader = null;
    int intTemp;
    char charTemp;
    StringBuilder result = new StringBuilder();
    try {
      bufferedReader =
          new BufferedReader(new InputStreamReader(downloader.body, StandardCharsets.UTF_8));
      /* is = downloader.body; */

      while ((intTemp = bufferedReader.read()) != -1) {
        /* converts integer to character */
        charTemp = (char) intTemp;
        result.append(charTemp);

      }
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    } finally {
      downloader.close();
      if (is != null) {
        is.close();
      }
    }

    return result.toString();
  }

}
