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

	public String getMelon() { // 현재 상영작 크롤링
		List<String> melon = new ArrayList<String>();
		try {
			Document document = Jsoup.connect("https://www.melon.com/chart/index.htm").get();
			Elements elements = document.select("table tbody .lst50"); // 파싱한 부분 선택

			for (Element element : elements) {
				String rank = element.select("td .wrap .rank").text();
				String imgurl = element.select("td .wrap a img").attr("src");
				String name = element.select("td .wrap .wrap_song_info .rank01").text();
				String singer = element.select("td .wrap .wrap_song_info .rank02 > a").text();
				String album = element.select("td .wrap .wrap_song_info .rank03").text();

				String result = rank + "\t" + imgurl + "\t" + name + "\t" + singer + "\t" + album;
				melon.add(result);

			}

			Map<String, Object> result = new HashMap<String, Object>();// 크롤링해서 리스트에 저장
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
	// "C:\\Users\\남진우\\Desktop\\3학년\\웹프로그래밍\\최종
	// 텀프\\[WP]_02_term_201302434\\phantomjs-2.1.1-windows\\binphantomjs.exe");
	// WebDriver driver = new PhantomJSDriver();
	// String result = "";
	//
	// HashMap<String, String> cgvMap = new HashMap<>(); // cgv의 각 영화관마다의 코드 저장
	// cgvMap.put("대전", "0007");
	// cgvMap.put("대전가오", "0154");
	// cgvMap.put("대전탄방", "0202");
	// cgvMap.put("대전터미널", "0127");
	// cgvMap.put("유성노은", "0206");
	// cgvMap.put("유성온천", "0209");
	// String cgvCode = "";
	// String cgvUrl = "";
	//
	// HashMap<String, String> lotteMap = new HashMap<>();// 롯데시네마의 각 영화관마다의 코드 저장
	// lotteMap.put("대전", "4002");
	// lotteMap.put("대전둔산", "4006");
	// String lotteCode = "";
	// String lotteUrl = "";
	//
	// HashMap<String, String> megaMap = new HashMap<>(); // 메가박스의 각 영화관마다의 코드 저장
	// megaMap.put("대전", "3021");
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
	// + date; // cgv에서 크롤링
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
	// Document document = Jsoup.parse(data);// 데이터 파싱
	//
	// Elements elements = document.select(".sect-showtimes");// 파싱한 부분 선택
	//
	// for (Element e : elements) {
	// result = e.html();
	// }
	//
	// driver.quit();
	// } else if (t[0].equals("롯데시네마")) {
	// lotteCode = lotteMap.get((String) t[1]);
	// lotteUrl =
	// "http://www.lottecinema.co.kr/LCHS/Contents/Cinema/Cinema-Detail.aspx?divisionCode=1&detailDivisionCode=3&cinemaID="
	// + lotteCode; // 롯데시네마에서 크롤링
	//
	// driver.get(lotteUrl);
	//
	// String data;
	//
	// driver.findElement(By.id(formatting(date))).click();
	//
	// data = driver.getPageSource();
	//
	// Document document = Jsoup.parse(data); // 데이터 파싱
	// Elements elements = document.select(".time_inner");// 파싱한 부분 선택
	//
	// for (Element e : elements) {
	// result = e.html();
	// }
	//
	// driver.quit();
	// } else if (t[0].equals("메가박스")) {
	// megaCode = megaMap.get((String) t[1]);
	// megaUrl = "https://www.naver.com"; // 네이버에서 파싱
	// String data;
	//
	// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	// driver.get(megaUrl);
	//
	// driver.findElement(By.id("query")).sendKeys("메가박스대전"); // 네이버에서 검색
	// driver.findElement(By.id("search_btn")).click();
	//
	// data = driver.getPageSource();
	//
	// Document document = Jsoup.parse(data);
	//
	// Elements elements = document.select(".list_tbl_box"); // 파싱한 부분 선택
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
	// private String formatting(String date) { // 수를 월로 바꿔 롯데시네마 형식에 맞춰줌
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