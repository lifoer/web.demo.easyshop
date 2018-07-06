package com.es.start;

/*
 * 测试代码所用类
 */
public class RegexTest {
	public static void main(String[] args) throws Exception {
		String result = "<script>document.forms[0].submit();</script>";
		String result1 = "<input type='submit' value='立即支付' style='display:none' >";
		result = result.replaceAll("<script>document\\.forms\\[0\\]\\.submit\\(\\);</script>", "123");
		result1 = result1.replaceAll("<input type='submit' value='立即支付' style='display:none' >", "123");
		String result2 = "1\n1";
		result2.replaceAll("\n", "");
		System.out.println(result2);
		
	}
}
