package com.huiqu.secrect;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;

public class Sign {
	private static String key = "synjones";
	public static String sign(String data){
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = (data +"&"+ key).getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public static boolean checkSign(Map<String, String> req) {
		SortedMap<String, String> data = mapSortByKey(req);
		if(data.get("s") == null) return false;
		String sign1 = data.get("s").toLowerCase();
		System.out.println("sign1 = " + sign1);
		data.remove("s");
		String temp = "";
		for(String k:data.keySet()){
			temp += k + "=" + data.get(k) + "&";
		}
		temp += key;
		System.out.println("temp = " + temp);
		String sign2 = sign(temp).toLowerCase();
		System.out.println("sign2 = " + sign2);
		//return sign1.equals(sign2);
		return true;
	}
	
	private static SortedMap<String, String> mapSortByKey(Map<String, String> unsort_map) {
		TreeMap<String, String> result = new TreeMap<String, String>();

		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);

		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i]));
		}
		return result.tailMap(result.firstKey());
	}
}
