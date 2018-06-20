<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="jin.WebCrawler"%>
<%
	WebCrawler WC = new WebCrawler();
	String siteType = request.getParameter("type");
	String searchItem = request.getParameter("name");
	if (siteType.equals("Melon")) {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(WC.getMelon());

		JSONObject object = (JSONObject) obj;

		JSONArray array = (JSONArray) object.get("list");
		PrintWriter writer = response.getWriter();
		writer.println(array);
		writer.close();
	} else if (siteType.equals("NM")) {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(WC.getNM());

		JSONObject object = (JSONObject) obj;

		JSONArray array = (JSONArray) object.get("list");
		System.out.print(array.size());
		PrintWriter writer = response.getWriter();
		writer.println(array);
		writer.close();
	} else if (siteType.equals("Genie")) {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(WC.getGenie());

		JSONObject object = (JSONObject) obj;

		JSONArray array = (JSONArray) object.get("list");
		System.out.print(array.size());
		PrintWriter writer = response.getWriter();
		writer.println(array);
		writer.close();
	} else if (siteType.equals("Search")) {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(WC.searchSong(searchItem));

		JSONObject object = (JSONObject) obj;

		JSONArray array = (JSONArray) object.get("list");
		System.out.print("!!!");
		System.out.print(searchItem+"~~");
		PrintWriter writer = response.getWriter();
		writer.println(array);
		writer.close();
	} else {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(WC.getBugs());

		JSONObject object = (JSONObject) obj;

		JSONArray array = (JSONArray) object.get("list");
		System.out.print(array.size());
		PrintWriter writer = response.getWriter();
		writer.println(array);
		writer.close();
	}
%>