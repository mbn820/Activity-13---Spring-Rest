package com.exist.ecc.app;

import java.io.PrintWriter;

public class HtmlTemplate {
	private String title = "";
	private String body = "";

	public HtmlTemplate setTitle(String title) {
		this.title = title;
		return this;
	}

	public HtmlTemplate setBody(String body) {
		this.body = body;
		return this;
	}

	// public HtmlTemplate insertTable(HtmlTable table) {
	// 	body += table.toString();
	// }

	public void print(PrintWriter out) {
		out.println( this.toString() );
	}

	public String toString() {
		return String.format
		( "<html>\n"
		  + "<head><title>" + title + "</title></head>\n"
		  + "<body>\n"
		  +  body + "\n"
		  + "</body>\n"
		  + "</html>\n"
		);
	}
}
