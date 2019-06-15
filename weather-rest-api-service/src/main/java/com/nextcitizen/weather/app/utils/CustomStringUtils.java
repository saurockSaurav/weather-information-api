package com.nextcitizen.weather.app.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

public abstract class CustomStringUtils extends org.springframework.util.StringUtils {
	
	public abstract boolean isValidURL(final String url);
	
	public String reverseString(final String str) {
		StringBuilder sb = new StringBuilder(str);
		return sb.reverse().toString();
	}
	
    
	public abstract boolean isAlphabhate(String value) ;
	
	public static boolean hasSpecialCharacter(String value) {
		return !Pattern.matches("[a-zA-Z]+", value);
	}
	
	public static boolean isBlank(String value) {			
		return isEmpty(trimWhitespace(value));
	}
	
	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isAlpha(String value) {
		return value.matches("[A-Za-z]+");
	}

	public static boolean isNumeric(String value) {
		return value.matches("[0-9]+");
	}

	public static boolean isAlphanumeric(String value) {
		return value.matches("[A-Za-z0-9]+");
	}
	
	public static String safe(String value) {
		return (isNotBlank(value)) ? value : "";
	}

	public static boolean equals(String value1, String value2) {
		return value1.equals(value2);
	}
	
	public static boolean equalsIgnoreCase(String value1, String value2) {
		return equals(value1.toLowerCase(), value2.toLowerCase());
	}
	
	public static String trimLeft(String needle, String haystack) {
		return ((needle != null) && (haystack != null) && (haystack.startsWith(needle))) ? haystack.substring(needle.length()) : haystack;
	}

	public static String joinIfNotBlank(String separator, Object... args) {
		String result = "";
		
		for (Object arg : args) {
			if ((arg == null) || isBlank(arg.toString())) continue;
			if (isNotBlank(result) && isNotEmpty(separator)) result += separator;
			result += arg.toString();			
		}
		
		return result;
	}
	
	public static String wrapIfNotBlank(String header, String footer, Object value) {
		if ((value == null) || isBlank(value.toString()))
			return "";
		
		return joinIfNotBlank("", header, value, footer);
	}

	public static String leftPad(String value, int width, char pad) {
		return String.format(String.format("%%%ds", width), value).replace(' ', pad);
	}
	
	public static String hexFromChar(char value) {
		return String.format("%x", (int) value);
	}
	
	public static final String xmlCdata(String value) {
		return CustomStringUtils.wrapIfNotBlank("<![CDATA[", "]]>", value);
	}
	
	public static String toKebabCase(String value) {
		return (CustomStringUtils.isNotBlank(value)) ? value.replaceAll("’|'|\"", "").replaceAll("[^A-Za-z0-9_]", "-").replaceAll("-+", "-").replaceAll("^-+|-+$", "") : null;
	}
	
	public static String toKebabLowerCase(String value) {
		return (CustomStringUtils.isNotBlank(value)) ? toKebabCase(value.toLowerCase()) : null;
	}
	
	public static String toKebabLowerCase(String value, int maxBites) {
		// Don't bother to do anything if no bites are desired
		if (maxBites < 1)
			return null;
		
		// If there's no kebab don't bother to do anything
		String kebab = toKebabLowerCase(value);
		if (CustomStringUtils.isBlank(kebab))
			return null;
		
		// Get the parts
		List<String> bites = new ArrayList<String>();
		String[] parts = kebab.split("-");
		for (int idx = 0; (idx < maxBites) && (idx < parts.length); idx++) {
			bites.add(parts[idx]);
		}
		
		return CustomStringUtils.joinIfNotBlank("-", bites.toArray());
	}

    public static String toCamelCase(String s,String delim)
    {        
        if(s != null)
        {
            StringBuffer ret = new StringBuffer();
            String[] words = s.split(delim);
            for(int x=0;x<words.length;x++)
            {
                String str = words[x];
                if(x != 0)
                {                    
                    char[] stringArray = str.toCharArray();
                    stringArray[0] = Character.toUpperCase(stringArray[0]);
                    str = new String(stringArray);
                }
                ret.append(str);
            }
            return ret.toString();
        }
        else
            return null;
        
    }
    
    public static String encode(String s) throws UnsupportedEncodingException
    {        
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));        
    }
    
    public static String decode(String s) throws UnsupportedEncodingException
    {
        byte[] asBytes = Base64.getDecoder().decode(s);
        return new String(asBytes, "utf-8");
    }
    
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);
    }
    
	public static String getUrlContentsSingleLine(String url) throws Exception {
		StringBuilder sb = new StringBuilder();
		URL u = new URL(url);
		URLConnection yc = u.openConnection();
		String inputLine;
		while ((inputLine = new BufferedReader(new InputStreamReader(yc.getInputStream())).readLine()) != null) {
			sb.append(inputLine.trim());
		}
		new BufferedReader(new InputStreamReader(yc.getInputStream())).close();
		String ret = sb.toString();
		return ret.replaceAll("\": ", "\":");
	}

}