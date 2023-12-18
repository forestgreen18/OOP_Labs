package edu.labs.lab6.mainapp.utils;

public class ClipboardManager {

  public void copyToClipboard(String data) {
    java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(
        data);
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit()
        .getSystemClipboard();
    clipboard.setContents(stringSelection, null);
  }
}
