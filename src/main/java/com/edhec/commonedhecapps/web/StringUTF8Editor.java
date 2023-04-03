package com.edhec.commonedhecapps.web;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * A custom property editor for Strings that
 * handles badly encoded UTF-8 characters.
 * <p>
 * Badly encoded characters are when the
 * multiple bytes that make up a UTF-8
 * character are each stored as indiviual
 * characters in a String.
 * <p>
 * For example, the following is a
 * badly encoded left curly quote:
 * <code><pre>
 * String bad = new String(new char[] {
 *    (char)0xE2, (char)0x80, (char)0x9C
 * });
 * </pre></code>
 * while the following is a
 * well encoded left curly quote:
 * <code><pre>
 * String good = new String(new byte[] {
 *    (byte)0xE2, (byte)0x80, (byte)0x9C
 * }, "UTF-8");
 * <pre><code>
 * 
 */
public class StringUTF8Editor extends StringTrimmerEditor {

    public StringUTF8Editor(boolean emptyAsNull) {
        super(emptyAsNull);
    }
    
    public StringUTF8Editor(String charsToDelete, boolean emptyAsNull) {
        super(charsToDelete, emptyAsNull);
    }
    
    
    @Override
    public void setAsText(String text) {
        if(text != null) {
            text = fixUnicode(text);
        }
        super.setAsText(text);
    }
    
    
    /**
     * Check if this character is possibly
     * the first byte of a multi-byte UTF-8
     * encoded Unicode character.
     * 
     * @param c Character to check.
     * @return 0 if not UTF8, or number of expected follow on bytes.
     */
    private static int possibleLeadUTF8(char c) {
        int numBytes = 0;
        
        if((c & 0x00F8) == 0x00F0) { // starts with 11110xxx
            numBytes = 4;
        }
        else if((c & 0x00F0) == 0x00E0) { // starts with 1110xxxx
            numBytes = 3;
        }
        else if((c & 0x00E0) == 0x00C0) { // starts with 110xxxxx
            numBytes = 2;
        }
        
        return numBytes;
    }
    
    /**
     * Check if this character is a byte that could
     * possibly follow a lead byte of a UTF-8
     * encoded Unicode character.
     * 
     * @param c Character to check.
     * @return True if this is a valid encoded byte.
     */
    private static boolean possibleFollowUTF8(char c) {
        return ((c & 0x00C0) == 0x0080); //starts with 10xxxxxx
    }
    
    /**
     * Checks the input string for badly encoded
     * UTF-8 characters.
     * 
     * @param text Value to search for badly encode characters.
     * @return The fixed value.
     */
    public static String fixUnicode(String text) {
        if(text == null || text.length() == 0) {
            return text;
        }
        
        StringBuilder sb = new StringBuilder(text.length());
        char[] chars = text.toCharArray();
        
        for(int i=0; i < chars.length; ++i) {
            boolean isUnicode = false;
            int numBytes = possibleLeadUTF8(chars[i]);
            if(numBytes > 0  && numBytes <= (chars.length - i)) {
                isUnicode = true;
                for(int j=i+1; j < (i+numBytes); ++j) {
                    if(!possibleFollowUTF8(chars[j])) {
                        isUnicode = false;
                        break;
                    }
                }
            }
            
            if(isUnicode) {
                byte[] unicodeBytes = new byte[numBytes];
                for(int j=0; j < numBytes; ++j) {
                    unicodeBytes[j] = (byte)(0x00FF & chars[i+j]);
                }
                try {
                    sb.append(new String(unicodeBytes,"UTF-8"));
                } catch (UnsupportedEncodingException e) {}
                i += (numBytes - 1);
            }
            else {
                sb.append(chars[i]);
            }
        }
        
        return sb.toString();
    }
    
}
