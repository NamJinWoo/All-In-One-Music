package jin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class WebCrawler {

	public String getMelon() { // ���� ���� ũ�Ѹ�
		List<String> melon = new ArrayList<String>();
		try {
			Document document = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
			Elements elements = document.select("table tbody .lst50"); // �Ľ��� �κ� ����

			for (Element element : elements) {
				String rank = element.select("td .wrap .rank").text();
				String imgurl = element.select("td .wrap a img").attr("src");
				String name = element.select("td .wrap .wrap_song_info .rank01").text();
				String singer = element.select("td .wrap .wrap_song_info .rank02 > a").text();
				String album = element.select("td .wrap .wrap_song_info .rank03").text();

				String result = rank + "\t" + imgurl + "\t" + name + "\t" + singer + "\t" + album;
				melon.add(result);

			}

			Map<String, Object> result = new HashMap<String, Object>();// ũ�Ѹ��ؼ� ����Ʈ�� ����
			result.put("list", melon);

			JSONObject obj = new JSONObject(result);

			return obj.toJSONString();

		} catch (Exception E) {

		}
		return null;
	}

	public String getNM() { 
		List<String> melon = new ArrayList<String>();
		try {
			Document document = Jsoup.connect("https://music.naver.com/listen/top100.nhn?domain=TOTAL").get();
			Elements elements = document.select("table tbody ._tracklist_move");

			for (Element element : elements) {
				String rank = element.select(".ranking").text();
				String imgurl = element.select(".name > a img").attr("src");
				String name = element.select(".name :nth-child(4)").text();
				String singer = element.select("._artist a").text();

				String result = rank + "\t" + imgurl + "\t" + name + "\t" + singer;
				melon.add(result);

			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("list", melon);

			JSONObject obj = new JSONObject(result);

			return obj.toJSONString();

		} catch (Exception E) {

		}
		return null;
	}

	public String getGenie() {
		List<String> melon = new ArrayList<String>();
		try {
			Document document = Jsoup.connect("http://www.genie.co.kr/chart/top200").get();
			Elements elements = document.select("table tbody .list"); 

			for (Element element : elements) {
				String rank = element.select(".number").text().split(" ")[0];
				String imgurl = element.select("td .cover img").attr("src");
				String name = element.select(".info .title").text();
				String singer = element.select(".info .artist").text();
				String album = element.select(".info .albumtitle").text();

				String result = rank + "\t" + imgurl + "\t" + name + "\t" + singer + "\t" + album;
				melon.add(result);

			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("list", melon);

			JSONObject obj = new JSONObject(result);

			return obj.toJSONString();

		} catch (Exception E) {

		}
		return null;
	}

	public String getBugs() { 
		List<String> melon = new ArrayList<String>();
		try {
			Document document = Jsoup.connect("https://music.bugs.co.kr/chart").get(); 
			Elements elements = document.select("table tbody tr"); 

			for (Element element : elements) {
				String rank = element.select("td .ranking").text().split(" ")[0];
				String imgurl = element.select("td .thumbnail img").attr("src");
				String name = element.select("th .title a").text();
				String singer = element.select(".left .artist a").text();
				String album = element.select(".left .album").text();

				String result = rank + "\t" + imgurl + "\t" + name + "\t" + singer + "\t" + album;
				melon.add(result);

			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("list", melon);

			JSONObject obj = new JSONObject(result);

			return obj.toJSONString();

		} catch (Exception E) {

		}
		return null;
	}

	public String searchSong(String title) { 
		System.out.println("!!!");
		System.setProperty("phantomjs.binary.path",
				"/Java/TP2_201402343/WebContent/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		
		
		WebDriver driver = new PhantomJSDriver();
		System.out.println("???");
		List<String> search = new ArrayList<String>();
		try {
			String url = "https://www.melon.com/index.htm";
			String data;

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			driver.get(url);

			driver.findElement(By.id("top_search")).sendKeys(title); 
			System.out.println("!!!");
			data = driver.getPageSource();

			Document document = Jsoup.parse(data);

			Elements elements = document.select("table");

			

			for (Element e : elements) {
				search.add(e.html());
			}
		} catch (Exception E) {

		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", search);

		JSONObject obj = new JSONObject(result);
		System.out.println(obj.toJSONString());
		return obj.toJSONString();
	}
	//
	// public String getTimeTable(String date, String theater) {
	// System.setProperty("phantomjs.binary.path",
	// "C:\\Users\\������\\Desktop\\3�г�\\�����α׷���\\����
	// ����\\[WP]_02_term_201302434\\phantomjs-2.1.1-windows\\binphantomjs.exe");
	// WebDriver driver = new PhantomJSDriver();
	// String result = "";
	//
	// HashMap<String, String> cgvMap = new HashMap<>(); // cgv�� �� ��ȭ�������� �ڵ� ����
	// cgvMap.put("����", "0007");
	// cgvMap.put("��������", "0154");
	// cgvMap.put("����ź��", "0202");
	// cgvMap.put("�����͹̳�", "0127");
	// cgvMap.put("��������", "0206");
	// cgvMap.put("������õ", "0209");
	// String cgvCode = "";
	// String cgvUrl = "";
	//
	// HashMap<String, String> lotteMap = new HashMap<>();// �Ե��ó׸��� �� ��ȭ�������� �ڵ� ����
	// lotteMap.put("����", "4002");
	// lotteMap.put("�����л�", "4006");
	// String lotteCode = "";
	// String lotteUrl = "";
	//
	// HashMap<String, String> megaMap = new HashMap<>(); // �ް��ڽ��� �� ��ȭ�������� �ڵ� ����
	// megaMap.put("����", "3021");
	// String megaCode = "";
	// String megaUrl = "";
	//
	// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	//
	// String[] t = theater.split(" ");
	// if (t[0].equals("CGV")) {
	// cgvCode = cgvMap.get((String) t[1]);
	// cgvUrl =
	// "http://www.cgv.co.kr/reserve/show-times/?areacode=03%2C205&theaterCode=" +
	// cgvCode + "&date=2017"
	// + date; // cgv���� ũ�Ѹ�
	//
	// driver.get(cgvUrl);
	//
	// String data;
	//
	// driver.switchTo().defaultContent();
	// driver.switchTo().frame("ifrm_movie_time_table");
	//
	// data = driver.getPageSource();
	//
	// Document document = Jsoup.parse(data);// ������ �Ľ�
	//
	// Elements elements = document.select(".sect-showtimes");// �Ľ��� �κ� ����
	//
	// for (Element e : elements) {
	// result = e.html();
	// }
	//
	// driver.quit();
	// } else if (t[0].equals("�Ե��ó׸�")) {
	// lotteCode = lotteMap.get((String) t[1]);
	// lotteUrl =
	// "http://www.lottecinema.co.kr/LCHS/Contents/Cinema/Cinema-Detail.aspx?divisionCode=1&detailDivisionCode=3&cinemaID="
	// + lotteCode; // �Ե��ó׸����� ũ�Ѹ�
	//
	// driver.get(lotteUrl);
	//
	// String data;
	//
	// driver.findElement(By.id(formatting(date))).click();
	//
	// data = driver.getPageSource();
	//
	// Document document = Jsoup.parse(data); // ������ �Ľ�
	// Elements elements = document.select(".time_inner");// �Ľ��� �κ� ����
	//
	// for (Element e : elements) {
	// result = e.html();
	// }
	//
	// driver.quit();
	// } else if (t[0].equals("�ް��ڽ�")) {
	// megaCode = megaMap.get((String) t[1]);
	// megaUrl = "https://www.naver.com"; // ���̹����� �Ľ�
	// String data;
	//
	// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	// driver.get(megaUrl);
	//
	// driver.findElement(By.id("query")).sendKeys("�ް��ڽ�����"); // ���̹����� �˻�
	// driver.findElement(By.id("search_btn")).click();
	//
	// data = driver.getPageSource();
	//
	// Document document = Jsoup.parse(data);
	//
	// Elements elements = document.select(".list_tbl_box"); // �Ľ��� �κ� ����
	//
	// for (Element e : elements) {
	// result = e.html();
	// }
	//
	// driver.quit();
	// }
	//
	// return result;
	// }
	//
	// private String formatting(String date) { // ���� ���� �ٲ� �Ե��ó׸� ���Ŀ� ������
	// String month = date.substring(0, 2);
	// String m;
	// switch (month) {
	// case "01":
	// m = "January";
	// break;
	// case "02":
	// m = "February";
	// break;
	// case "03":
	// m = "March";
	// break;
	// case "04":
	// m = "April";
	// break;
	// case "05":
	// m = "May";
	// break;
	// case "06":
	// m = "June";
	// break;
	// case "07":
	// m = "July";
	// break;
	// case "08":
	// m = "August";
	// break;
	// case "09":
	// m = "September";
	// break;
	// case "10":
	// m = "October";
	// break;
	// case "11":
	// m = "November";
	// break;
	// case "12":
	// m = "December";
	// break;
	//
	// default:
	// m = "";
	// break;
	// }
	//
	// return m + date.substring(2, 4);
	// }
}